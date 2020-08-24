/*
Gerar e imprimir uma lista contendo a pontuação, subreddit, título da thread, link para os comentários da thread e link da thread. Essa parte pode ser um CLI simples, desde que a formatação da impressão fique legível.
*/

package crawlers;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RedditCrawler redditCrawler = new RedditCrawler();
        List<String> subs = redditCrawler.craw("news;pics");

        for (String sub : subs) {
            System.out.println(sub);
        }
    }
}
