import sys
import json
import os

from scrapy.crawler import CrawlerProcess

from redditscraper.spiders.reddit_top_threads_scraper import RedditTopThreadsScraper


def run_spider(subreddits: str):
    process = CrawlerProcess({
        "FEEDS": {
            "results/items.json": {"format": "json", "overwrite": True},
        },
    })

    process.crawl(RedditTopThreadsScraper, subreddits=subreddits)
    return process.start()


if __name__ == "__main__":
    try:
        subreddits = str(sys.argv[1]).split("=")[1]
    except IndexError:
        raise IndexError("""
        É necessário enviar pelo menos um subreddit
        da seguinte forma: `docker-compose run --subreddit="cats;sports"`
        """)
    run_spider(subreddits)
    results_json_path = "./results/items.json"
    if os.path.isfile(results_json_path) and os.path.getsize(results_json_path) > 0:
        with open(results_json_path) as file:
            data = json.load(file)
            for item in data:
                pretty_json = json.dumps(item, indent=4)
                print(pretty_json)
