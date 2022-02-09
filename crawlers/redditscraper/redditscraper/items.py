# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class RedditTopThreadsScraperItem(scrapy.Item):
    score = scrapy.Field()
    subreddit = scrapy.Field()
    title = scrapy.Field()
    image_url = scrapy.Field()
    comments_url = scrapy.Field()
