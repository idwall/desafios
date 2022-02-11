from decouple import config
from scrapy.utils.log import configure_logging
from telegram import Update, ParseMode
from telegram.ext import Updater, CommandHandler, CallbackContext

import scraper_runner

TELEGRAM_BOT_TOKEN = config("TELEGRAM_BOT_TOKEN")

configure_logging()

def send_reddit_top_threads(update: Update, context: CallbackContext) -> None:
    try:
        subreddits = context.args[0]
    except IndexError:
        raise IndexError("Escreva pelo menos um subreddit.")
    scraper_runner.run_scraper(subreddits)
    results = scraper_runner.load_scraping_results()
    message_text = "TOP REDDITS\n"
    for result in results:
        subreddit = result["subreddit_title"]
        message_text += "\n".join([
            "====================",
            f"<b>{subreddit.capitalize()}</b>\n",
        ])
        items = result["items"]
        if not items:
            message_text += "Não foi encontrado nenhum reddit com +5k de pontuação :(\n"
        else:
            for item in items:
                message_text += "\n".join([
                    "- Título",
                    f"<pre>{item['title']}</pre>",
                    "- Pontuação",
                    f"<pre>{item['score']}</pre>",
                    "- Link da fonte",
                    f"{item['source_url']}",
                    "- Link para os comentários",
                    f"{item['comments_url']}\n\n",
                ])
    update.message.reply_text(text=message_text, parse_mode=ParseMode.HTML)


def main():
    updater = Updater(TELEGRAM_BOT_TOKEN)
    dp = updater.dispatcher
    dp.add_handler(CommandHandler('top', send_reddit_top_threads))
    updater.start_polling()
    updater.idle()



if __name__ == "__main__":
    main()
