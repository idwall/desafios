package formatter;

public class Main {
	public static void main(String[] args) {
		String unFormatedtext = 
				"In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n" + 
				"\n" + 
				"And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
		
		Formatter format = new Formatter(unFormatedtext, 40);
		
		System.out.println("================================================");
		System.out.println("output_parte1.txt:\n");
		System.out.println(format.lineLimit());
		System.out.println("================================================\n");
		
		System.out.println("================================================");
		System.out.println("output_parte2.txt:\n");
		System.out.println(format.lineLimitBlock());
		System.out.println("================================================\n");
	}

}
