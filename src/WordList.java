import java.io.*;

/**
 * WordList class is a fileparser class that read a txt file and stores all the word into a Dictionary array
 * @author yuhu and kat
 *
 */
public class WordList {
	public static final int MAXWORDS = 100000;
	public static final int MAXWORDLEN = 30;
	public static final int EOF = -1;
	public static Word[] dictionary = new Word[MAXWORDS];
	public static int totalWords = 0;

	/**
	 * constructor
	 * @param s filename
	 */
	public WordList(String s){
		ReadDict(s);
	}
	
	/**
	 * get word at index i
	 * @param i index i
	 * @return word at index i
	 */
	public Word getWord(int i){
		return dictionary[i];
	}
	
	/**
	 * parse file to turn them into word
	 * @param f file name
	 */
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
	
	/**
	 * get the total number of word
	 * @return return the total number of word
	 */
	public int getTotalWords()
	{
		return totalWords;
	}
}
