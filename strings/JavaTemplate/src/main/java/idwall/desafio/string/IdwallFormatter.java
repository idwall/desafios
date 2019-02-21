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
//        throw new UnsupportedOperationException();
        
        String[] words = text.split(" ");
		
		List<String> output = new ArrayList<String>();
		List<String> currentLine = new ArrayList<String>();
		int currentSize = 0;

		for (String word : words) {
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
		return output;
    }
    
}
