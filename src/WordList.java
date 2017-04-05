import java.io.*;

/**
 * WordList class is a fileparser class that read a txt file and stores all the word into a Dictionary array
 * @author yuhu
 *
 */
public class WordList {
	public static final int MAXWORDS = 100000;
	public static final int MAXWORDLEN = 30;
	public static final int EOF = -1;
	public static Word[] dictionary = new Word[MAXWORDS];
	public static int totalWords = 0;

	public WordList(String s){
		ReadDict(s);
	}
	
	public Word getWord(int i){
		return dictionary[i];
	}
	
	@SuppressWarnings("resource")
	public static void ReadDict (String f) {

		FileInputStream fis;
		try {
			fis = new FileInputStream (f);
		}
		catch (FileNotFoundException fnfe) {
			System.err.println("Cannot open the file of words '" + f + "'");
			throw new RuntimeException();
		}
		System.err.println ("reading dictionary...");
		
		char word[] = new char[MAXWORDLEN];
		String s;
		int r = 0;
		while (r!=EOF) {
			int wordLen = 0;
			try {
				// read a word in from the word file
				while ((r=fis.read()) != EOF ) {
					if ( r == '\n' ) 
						break;
					word[wordLen++] = (char) r;
					
				}
			} catch (IOException ioe) {
				System.err.println("Cannot read the file of words ");
				throw new RuntimeException();
			}
			s = new String(word,0,wordLen);
			dictionary[totalWords] = new Word(s);
			totalWords++;
		}
		
		System.err.println("main dictionary has " + totalWords + " entries.");
	}
	
	public int getTotalWords()
	{
		return totalWords;
	}
}
