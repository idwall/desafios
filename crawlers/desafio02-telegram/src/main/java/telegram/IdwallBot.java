package telegram;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IdwallBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
            switch (update.getMessage().getText().split(" ")[0].toUpperCase()) {
                case "/NADAPRAFAZER":
                    if (update.getMessage().getText().split(" ").length > 1)
                        NadaPraFazer(update, update.getMessage().getText().split(" ")[1]);
                    else
                        NadaPraFazer(update, null);
                    break;
                case "/ABOUTME":
                    aboutMessage(update);
                    break;
                default:
                    usageMessage(update);
            }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    public void NadaPraFazer(Update update, String argsSub) {
        final String REDDIT_URL = "https://old.reddit.com/";
        try {
            Document doc = Jsoup.connect(REDDIT_URL).get();
            Elements score = doc.select("div.score.likes");
            Elements subreddit = doc.select("a.subreddit.hover.may-blank");
            Elements title = doc.select("a.title.may-blank");
            Elements comments = doc.select("a.bylink.comments");

            if(argsSub != null) {
                List<String> subs = new ArrayList<>();
                for(int i = 0; i < argsSub.split(";").length ; i++)
                    subs.add(argsSub.split(";")[i].toLowerCase());

                for (int i = 0; i < subreddit.size(); i++) {
                    if (subs.contains(subreddit.get(i).text().split("/")[1].toLowerCase())) {
                        String msg = score.get(i).text() + " | " + subreddit.get(i).text() + " | " + title.get(i).text() + "\n";
                        if (title.get(i).attr("href").charAt(0) == '/')
                            msg = msg + "https://old.reddit.com" + title.get(i).attr("href") + "\n";
                        else
                            msg = msg + title.get(i).attr("href") + "\n";
                        msg = msg + comments.get(i).attr("href");

                        this.sendMessage(update.getMessage().getChatId(), msg);
                    }
                }
            } else {
                for (int i = 0; i < subreddit.size(); i++) {
                    String msg = score.get(i).text() + " | " + subreddit.get(i).text() + " | " + title.get(i).text() + "\n";
                    if (title.get(i).attr("href").charAt(0) == '/')
                        msg = msg + "https://old.reddit.com" + title.get(i).attr("href") + "\n";
                    else
                        msg = msg + title.get(i).attr("href") + "\n";
                    msg = msg + comments.get(i).attr("href");

                    this.sendMessage(update.getMessage().getChatId(), msg);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void usageMessage(Update update) {
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Comandos disponívels:\n\n/NadaPraFazer\n/NadaPraFazer sub\n/NadaPraFazer sub1;sub2\n/AboutMe");
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void aboutMessage(Update update) {
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("https://www.linkedin.com/in/renata89abreu/\nhttps://github.com/rabreu/");
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Long chatId, String msg) {
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(msg);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}