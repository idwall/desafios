import scrapy

from redditscraper.items import RedditTopThreadsScraperItem


class RedditTopThreadsScraper(scrapy.Spider):
    """
    Class responsible for scraping top threads (score +5k) from a list of subreddits. 
    """
    custom_settings = {
        "FEEDS": {
            "results/items.json": {"format": "json", "overwrite": True},
        },
    }

    name = "reddit_top_threads_scraper"
    filter = "top"
    base_url = "https://old.reddit.com/r"

    # def __init__(self, subreddits=None):
    #     super(RedditTopThreadsScraper, self).__init__(subreddits)
    #     self.subreddits = subreddits

    def start_requests(self):
        subreddits = getattr(self, 'subreddits', None)
        if subreddits:
            subreddits_list = subreddits.split(";")
            for subreddit in subreddits_list:
                url = f"{self.base_url}/{subreddit}/{self.filter}"
                yield scrapy.Request(
                    url=url,
                    callback=self.parse,
                    cb_kwargs={"subreddit_title": subreddit}
                )
        else:
            raise Exception("É necessário enviar pelo menos um subreddit.")

    def parse(self, response, subreddit_title):
        threads = (
            response
            .css("div[id=siteTable] div[data-context=listing][data-promoted=false]")
        )
        reddit_domain = "https://reddit.com"
        all_items = list()

        for thread in threads:
            score = thread.css("div::attr(data-score)").get()
            subreddit = thread.css("div::attr(data-subreddit)").get()
            title = thread.css("a.title::text").get()
            comments_url = thread.css("div::attr(data-permalink)").get()
            source_url = thread.css("div::attr(data-url)").get()
    
            if int(score) >= 5000:
                reddit_top_threads_item = RedditTopThreadsScraperItem()
                reddit_top_threads_item["score"] = score
                reddit_top_threads_item["subreddit"] = subreddit
                reddit_top_threads_item["title"] = title
                reddit_top_threads_item["comments_url"] = f"{reddit_domain}{comments_url}"
                reddit_top_threads_item["source_url"] = source_url
                all_items.append(reddit_top_threads_item)
        
        yield dict(subreddit_title=subreddit_title, items=all_items)
