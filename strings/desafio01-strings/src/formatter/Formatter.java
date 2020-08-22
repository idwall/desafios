package formatter;

import java.util.ArrayList;

public class Formatter {

	String inputText;
	Integer limit;
	
	
	public Formatter() {

	}
	
	public Formatter(String inputText, Integer limit) {
		super();
		this.inputText = inputText;
		this.limit = limit;
	}
	
	public String lineLimit(String inputText, Integer limit) {
		this.inputText = inputText;
		this.limit = limit;
		return lineLimit();
	}

	public String lineLimit() {

		char[] textArray = new char[inputText.length()];
		
		// Convert String to char array
		for(int i = 0; i < inputText.length() ; i++)
			textArray[i] = inputText.charAt(i);

		int lastWordSize = 0;
		int currentLineSize = 0;
		
		for(int i = 0; i < inputText.length(); i++) {
			if(textArray[i] == '\n') {
				i++;
				lastWordSize = 0;
				currentLineSize = 0;
			}
			else
				currentLineSize++;
			
			if(textArray[i] != ' ') {
				lastWordSize++;
				if (currentLineSize > limit) {
					textArray[i-lastWordSize] = '\n';
					currentLineSize = 0 + lastWordSize;
				}
			} 
			else
				lastWordSize = 0;
		}
		
		return new String(textArray);
	}
	
	public String lineLimitBlock(String inputText, Integer limit) {
		this.inputText = inputText;
		this.limit = limit;
		return lineLimitBlock();
	}
	
	public String lineLimitBlock() {
		String formatedText = this.lineLimit();
		
		int firstCharLinePosition = 0;
		int currentLineSize = 0;
		for(int i = 0 ; i < formatedText.length(); i++) {
			
			// Count number of letters for the current line
			currentLineSize++;
			
			// End of line
			if (formatedText.charAt(i) == '\n' && formatedText.charAt(i+1) != '\n') {
				int lastCharLinePosition = firstCharLinePosition+currentLineSize-1;
				
				// If true, it means that there's no new line
				if (lastCharLinePosition > formatedText.length())
					break;
				
				// Save space positions (and size)
				ArrayList<Integer> spacePositions = new ArrayList<>();
				for(int n = firstCharLinePosition ; n < lastCharLinePosition; n++) {
					if (formatedText.charAt(n) == ' ')
						spacePositions.add(n);
				}
				
				// Calculate how many spaces should be alloc'd to reach the line limit
				int spaceAlloc = limit+1-currentLineSize;
				
				if(spaceAlloc < limit+1) {
					int j = 0;
					int n = 0;
					int spaceIndex = 0;
					while(n < spaceAlloc) {
						
						if(j == spacePositions.size())
							j = 0;
						
						spaceIndex = spacePositions.get(j)+n;
						formatedText = this.addSpace(formatedText, spaceIndex);
						
						j++;
						n++;
						i++;
					}

				}
				currentLineSize = 0;
				firstCharLinePosition = i+1;
			}
		}
		return formatedText;	
	}

	private String addSpace(String str, int position) {
	    StringBuilder sb = new StringBuilder(str);
	    sb.insert(position, " ");
	    return sb.toString();
	}
}