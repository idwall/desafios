package idwall.desafio.scrapers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Reddit {
	
	public static final String USER_AGENT = "Mozilla";
	
	public List<String> getTrends(String subreddits) throws IOException {
		Document doc = Jsoup.connect("https://old.reddit.com/r/kittens").userAgent(USER_AGENT).timeout(2000).get();
		Elements elements = doc.select("div.thing");

		List<String> output = new ArrayList<String>();
		for (Element e : elements) {
			StringBuilder builder = new StringBuilder();
			builder.append("--- Subreddit: " + "/r/kittens");
			builder.append("Likes: " + e.select("div.score.likes").text() + "Título: " + e.select("a.title").text());
			builder.append("Comentários: " + e.select("a.bylink.comments").attr("href"));
			builder.append("Link: " + e.select("a.title").attr("href"));
			builder.append("-------------------");
			output.add(builder.toString());
		}
		
		return output;
	}

}
