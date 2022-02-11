from asyncio import subprocess
import json
import os

from scrapy.crawler import CrawlerRunner
from scrapy.utils.log import configure_logging
from telegram import Update
from telegram.ext import Updater, CommandHandler, CallbackContext
from twisted.internet import reactor, defer

from redditscraper.spiders.reddit_top_threads_scraper import RedditTopThreadsScraper


configure_logging()

runner = CrawlerRunner({
        "FEEDS": {
            "results/items.json": {"format": "json", "overwrite": True},
        },
    })

@defer.inlineCallbacks
def crawl(subreddits):
    yield runner.crawl(RedditTopThreadsScraper, subreddits=subreddits)
    reactor.stop()


def run_scraper(subreddits: str) -> None:
    d = runner.crawl(RedditTopThreadsScraper, subreddits=subreddits)
    d.addBoth(lambda _: reactor.stop())
    reactor.run()


def load_scraping_results() -> dict:
    results_json_path = "./results/items.json"
    if os.path.isfile(results_json_path) and os.path.getsize(results_json_path) > 0:
        with open(results_json_path) as file:
            return json.load(file)


def send_reddit_top_threads(update: Update, context: CallbackContext) -> None:
    try:
        subreddits = context.args[0]
    except IndexError:
        raise IndexError("Escreva pelo menos um subreddit.")
    # run_scraper(subreddits)
    crawl(subreddits)
    reactor.run()
    results = load_scraping_results()
    print(results)
    for result in results:
        subreddit = result["subreddit_title"]
        for item in result["items"]:
            update.message.reply_text(f"""
            Subreddit: {subreddit}
            Pontuação: {item["score"]}
            Título: {item["title"]}
            Link da fonte: {item["source_url"]}
            Link para os comentários: {item["comments_url"]}
            """)
    # bot.send_results(chat_id=chat_id, results=results)


def main():
    updater = Updater('5167354451:AAFeL-wda_XwqnXO_hgTR8UQUQrhDEH02QQ')
    dp = updater.dispatcher
    dp.add_handler(CommandHandler('top', send_reddit_top_threads))
    updater.start_polling()
    updater.idle()



if __name__ == "__main__":
    # try:
    #     subreddits = str(sys.argv[1]).split("=")[1]
    # except IndexError:
    #     raise IndexError("""
    #     É necessário enviar pelo menos um subreddit
    #     da seguinte forma: `--subreddit="cats;sports"`
    #     """)
    # run_scraper(subreddits)

    main()
