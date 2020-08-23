/*
Gerar e imprimir uma lista contendo a pontuação, subreddit, título da thread, link para os comentários da thread e link da thread. Essa parte pode ser um CLI simples, desde que a formatação da impressão fique legível.
*/

package crawlers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        final String URL = "https://old.reddit.com/";
        try {
            Document doc = Jsoup.connect(URL).get();

            Elements score = doc.select("div.score.likes");
            Elements subreddit = doc.select("a.subreddit.hover.may-blank");
            Elements title = doc.select("a.title.may-blank");
            Elements comments = doc.select("a.bylink.comments"); // .attr("href");

            for (int i = 0; i < subreddit.size(); i++) {
                System.out.println("=============================================================");
                System.out.println(score.get(i).text() + " | " + subreddit.get(i).text() + " | " + title.get(i).text());
                if (title.get(i).attr("href").charAt(0) == '/')
                    System.out.println("https://old.reddit.com" + title.get(i).attr("href"));
                else
                    System.out.println(title.get(i).attr("href"));
                System.out.println(comments.get(i).attr("href"));
                System.out.println("=============================================================\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
