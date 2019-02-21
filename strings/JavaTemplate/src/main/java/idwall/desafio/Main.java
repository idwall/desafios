package idwall.desafio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class Main {

	private static final Integer DEFAULT_LIMIT = 40;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		try {

			int maxSize;
			try {
				System.out.print("Digite a quantidade máxima de caracteres: ");
				maxSize = input.nextInt();
			} catch (InputMismatchException e) {
				maxSize = DEFAULT_LIMIT; 
			}

			System.out.print("Digite a frase: ");
			input.nextLine();

			List<String> output = new ArrayList<String>();
			List<String> currentLine = new ArrayList<String>();
			int currentSize = 0;

			while(input.hasNext()) {
				String word = input.next();
				if ( (currentSize + word.length() -1) >= maxSize ) {
					int sizeLeft = maxSize - (currentSize -1);
					int spacesPhrase = currentLine.size() - 1;
					int spaceLenght = spacesPhrase==0?0: sizeLeft / spacesPhrase;
					int spaceLeft = spacesPhrase==0?0: sizeLeft % spacesPhrase;

					StringBuilder line = new StringBuilder();
					for (String s : currentLine) {
						if (line.length()!=0) {
							line.append(StringUtils.leftPad("", (spaceLenght + (spaceLeft--<=0?0:1) + 1), ""));
						}
						line.append(s);
					}
					output.add(line.toString());
					currentSize = 0;
					currentLine.clear();
					currentLine.add(word);
					currentSize += word.length() + 1;

				} else {
					currentLine.add(word);
					currentSize += word.length() + 1;
				}

			}

			StringBuilder line = new StringBuilder();
			for (String s : currentLine) {
				if (line.length()!=0) {
					line.append(" ");
				}
				line.append(s);
			}
			output.add(line.toString());

			for (String s : output){
				System.out.println(s);
			}
		} finally {
			input.close();
		}
	}
}
