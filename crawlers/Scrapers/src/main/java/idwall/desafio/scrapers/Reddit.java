package idwall.desafio.scrapers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Classe para leitura das páginas do Reddit
 */
public class Reddit {

	public static final String USER_AGENT = "Mozilla";
	public static final String URL = "https://old.reddit.com/r/";

	public List<String> getTrends(String subreddit) throws IOException {
		// Faz a conexão e recupera o html
		Document doc = Jsoup.connect(formatURL(subreddit)).userAgent(USER_AGENT).timeout(2000).get();
		
		// Trabalhando apenas com as trends
		Elements elements = doc.select("div.thing");

		// Para cada trend, recupera-se os dados necessários
		// As mensagens enviadas pelo bot serão armazenadas num array
		List<String> output = new ArrayList<String>();
		output.add("Buscando por: " + subreddit);
		boolean found = false;
		for (Element e : elements) {
			String score = e.select("div.score.unvoted").text();
			long points = 0L;
			try {
				points = Long.parseLong(score);
			} catch (NumberFormatException n) {
				n.printStackTrace();
			}

			// Apenas trends com mais de 5k pontos são enviadas
			if ((score!=null && score.contains("k")) || points > 5000L) {
				StringBuilder builder = new StringBuilder();
				builder.append(e.select("div.score.likes").text() + " likes - " + e.select("a.title").text()); // Likes e título
				builder.append("\n");
				builder.append("Comentários: " + e.select("a.bylink.comments").attr("href")); // Link dos comentários
				builder.append("\n");
				builder.append(e.select("a.title").attr("href")); // Link da trend
				output.add(builder.toString());
				found = true;
			}
		}
		if (!found) {
			output.add("Desculpe, nada encontrado.");
		}
		return output;
	}

	public String[] getListaSubreddit(String subreddits) {
		if (subreddits != null && !subreddits.equals("")) {
			String[] listaSubreddits = subreddits.split(";");
			return listaSubreddits;
		} else {
			return new String[0];
		}
	}

	private String formatURL(String sub) {
		return URL + sub;
	}

}
