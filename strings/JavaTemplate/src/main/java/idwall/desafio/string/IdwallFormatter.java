package idwall.desafio.string;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public List<String> format(String text, int maxSize) {
    	// Trabalhando palavra por palavra
        String[] words = text.split(" ");
		
		List<String> output = new ArrayList<String>();
		
		// Juntando todas as palavras que caibam no tamanho máximo da linha
		List<String> currentLine = new ArrayList<String>();
		int currentSize = 0;

		for (String word : words) {
			// Se a inclusão de uma nova palavra vai ultrapassar o tamanho
			// máximo, isso significa que essa linha da frase está pronta
			// para ser formatada
			if ( (currentSize + word.length() -1) >= maxSize ) {
				
				int sizeLeft = maxSize - (currentSize -1); // Espaços faltantes para completar o tamanho máximo
				int spacesPhrase = currentLine.size() - 1; // Espaços entre palavras na linha
				int spaceLenght = spacesPhrase==0?0: sizeLeft / spacesPhrase; // Quantidade de espaços fixos para ser adicionado
				int spaceLeft = spacesPhrase==0?0: sizeLeft % spacesPhrase; // Quantidade de espaços restantes para serem distribuídos

				// Cria a linha adicionando os espaços para o padrão justificado
				StringBuilder line = new StringBuilder();
				for (String s : currentLine) {
					if (line.length()!=0) {
						line.append(StringUtils.leftPad("", (spaceLenght + (spaceLeft--<=0?0:1) + 1), ""));
					}
					line.append(s);
				}
				output.add(line.toString());
				
				// Limpa os dados para a próxima linha
				currentSize = 0;
				currentLine.clear();
				currentLine.add(word);
				currentSize += word.length() + 1;

			} else {
				// Senão a palavra é inserida na linha
				currentLine.add(word);
				currentSize += word.length() + 1;
			}

		}
		// A última linha não precisa estar justificada.
		// Então são adicionados apenas os espaços
		// entre palavras.
		StringBuilder line = new StringBuilder();
		for (String s : currentLine) {
			if (line.length()!=0) {
				line.append(" ");
			}
			line.append(s);
		}
		output.add(line.toString());
		return output;
    }
    
}
