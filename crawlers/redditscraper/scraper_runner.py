import json
import os
import sys

from scrapy.crawler import CrawlerRunner
from scrapy.utils.log import configure_logging
from twisted.internet import reactor

from redditscraper.spiders.reddit_top_threads_scraper import RedditTopThreadsScraper


configure_logging()

print_results = False

runner = CrawlerRunner({
        "FEEDS": {
            "results/items.json": {"format": "json", "overwrite": True},
        },
    })

def run_scraper(subreddits: str) -> None:
    d = runner.crawl(RedditTopThreadsScraper, subreddits=subreddits)
    d.addBoth(lambda _: reactor.stop())
    reactor.run()


def load_scraping_results() -> dict:
    results_json_path = "./results/items.json"
    if os.path.isfile(results_json_path) and os.path.getsize(results_json_path) > 0:
        with open(results_json_path) as file:
            results_json = json.load(file)
            if print_results is False:
                return results_json
            else:
                print(json.dumps(results_json, indent=2))


if __name__ == "__main__":
    print_results = True
    try:
        subreddits = str(sys.argv[1]).split("=")[1]
    except IndexError:
        raise IndexError("""
        É necessário enviar pelo menos um subreddit
        da seguinte forma: `--subreddit="cats;sports"`
        """)
    run_scraper(subreddits)
    load_scraping_results()
