package idwall.desafio;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import idwall.desafio.string.IdwallFormatter;
import idwall.desafio.string.StringFormatter;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class Main {

	private static final Integer DEFAULT_LIMIT = 40;
	private static Scanner input;

	public static void main(String[] args) {
		try {
			// Recebe os parâmetros do console
			input = new Scanner(System.in);
			int maxSize;
			try {
				System.out.print("Digite a quantidade máxima de caracteres: ");
				maxSize = input.nextInt();
			} catch (InputMismatchException e) {
				// Caso não seja enviada uma quantadide válida, a default será utilizada.
				System.out.println("Quantidade inválida. Utilizando o valor: " + DEFAULT_LIMIT);
				maxSize = DEFAULT_LIMIT; 
			}
			// Consumindo linha em vazia devido ao Enter do primeiro input
			input.nextLine();

			// Recebendo a frase
			System.out.print("Digite a frase: ");
			String phrase = input.nextLine();

			if (phrase == null || phrase.equals("")) {
				return;
			}

			// Chama formatação
			StringFormatter formatter = new IdwallFormatter();
			List<String> output = formatter.format(phrase, maxSize);

			// Printa o resultado
			System.out.println("---");
			System.out.println("Output:");
			System.out.println("---");
			for (String s : output){
				System.out.println(s);
			}
			System.out.println("---");
		} catch (Exception e) {
			System.out.println("Erro ao formatar frase.");
		} finally {
			input.close();
		}
	}
}
