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
			input = new Scanner(System.in);
			int maxSize;
			try {
				System.out.print("Digite a quantidade máxima de caracteres: ");
				maxSize = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Quantidade inválida. Utilizando o valor: " + DEFAULT_LIMIT);
				maxSize = DEFAULT_LIMIT; 
			}
			input.nextLine();

			System.out.print("Digite a frase: ");
			String phrase = input.nextLine();
			
			if (phrase == null || phrase.equals("")) {
				return;
			}
			
			StringFormatter formatter = new IdwallFormatter();
			List<String> output = formatter.format(phrase, maxSize);

			System.out.println("---");
			System.out.println("Output:");
			System.out.println("---");
			for (String s : output){
				System.out.println(s);
			}
			System.out.println("---");
		} finally {
			input.close();
		}
	}
}
