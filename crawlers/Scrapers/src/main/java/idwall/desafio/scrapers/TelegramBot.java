package idwall.desafio.scrapers;

import java.util.List;
import java.util.regex.PatternSyntaxException;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

	private static final String command = "/NadaParaFazer";

	public void onUpdateReceived(Update update) {
		try {
			// O ID fica aqui em cima para ser utilizado em todos os envios de mensagem
			long chat_id = update.getMessage().getChatId();
			if (update.hasMessage() 
					&& update.getMessage().hasText() 
					&& update.getMessage().getText().startsWith(command)) {
				try {
					// As subreddits ficam após o comando
					String[] subreddits = update.getMessage().getText().split(" ");

					Reddit reddit = new Reddit();

					String[] listaSubreddits = reddit.getListaSubreddit(subreddits[1]);

					// Para cada subreddit o método de busca é chamado
					for (String subreddit : listaSubreddits) {
						try {
							List<String> texts = reddit.getTrends(subreddit);
							for (String text : texts) {
								sendMessage(chat_id, text);
							}
						} catch (Exception e) {
							sendMessage(chat_id, "Erro ao buscar trend: " + subreddit);
						}
					}
				} catch (PatternSyntaxException p) {
					sendMessage(chat_id, "Desculpe, comando inválido.");
				} catch (ArrayIndexOutOfBoundsException a) {
					sendMessage(chat_id, "Desculpe, comando inválido.");
				}
			} else {
				sendMessage(chat_id, "Desculpe, não entendi.");
			}
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public String getBotUsername() {
		return "julie_2019_bot";
	}

	@Override
	public String getBotToken() {
		// Obs: para fins de teste somente. Um token não deve ser exposto dessa forma.
		return "785538663:AAFoYQxmmIXwEaJyfqMJ6rCNr5SzS427q-c";
	}

	private void sendMessage(long chat_id, String message_text) throws TelegramApiException {
		// Devolve a mensagem para o user
		SendMessage message = new SendMessage()
				.setChatId(chat_id)
				.setText(message_text);
		execute(message);
	}

}
