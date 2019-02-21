package idwall.desafio.scrapers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
	
	private static final String command = "/NadaParaFazer";

	public void onUpdateReceived(Update update) {
		if (update.hasMessage() 
				&& update.getMessage().hasText() 
				&& update.getMessage().getText().startsWith(command)) {
			List<String> texts = new ArrayList<String>();
			Reddit reddit = new Reddit();
			try {
				texts = reddit.getTrends();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			long chat_id = update.getMessage().getChatId();

			try {
				for (String message_text : texts) {
					SendMessage message = new SendMessage() // Create a message object object
							.setChatId(chat_id)
							.setText(message_text);
					execute(message); // Sending our message object to user
				}
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	public String getBotUsername() {
		return "julie_2019_bot";
	}

	@Override
	public String getBotToken() {
		return "785538663:AAFoYQxmmIXwEaJyfqMJ6rCNr5SzS427q-c";
	}

}
