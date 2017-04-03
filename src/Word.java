/**
 * Word class stores information for every word
 * including the number of each letter in a word, the number of letters in a word, and the word itself 
 * @author yuhu
 *
 */
public class Word  {
	 public int count[] = new int[26];  // count of each letter in the word
	 private int wordLen;  // number of letters in the word
	 private String aword;  // the word

	 /**
	  * Constructor that takes in a string
	  * @param s
	  */
	 public Word(String s) { // construct an entry from a string
		 String str = s.toLowerCase();
		 aword = str;
		 wordLen = 0;
		
		 for (int i = 0; i < str.length(); i++) {
			 int ch = str.charAt(i) - 'a';
			 if (ch >= 0 && ch < 26) {
				 wordLen++;
				 count[ch]++;
			 }
		}
	}
	
	/**
	 * Method to determine if a word contains a specific letter
	 * @param j letter index
	 * @return true if this letter is present in the word
	 */
	public boolean containsLetter(int j){
		return count[j] != 0;
	}
	
	/**
	 * Getter to return the count of a letter at a certain index
	 * @param index of letter
	 * @return count
	 */
	public int getCount(int index) 
	{
		return count[index];
	}
	
	/**
	 * Setter to set the count of a letter to a new value
	 * @param index of letter
	 * @param newValue of letter
	 */
	public void setCount(int index, int newValue)
	{
		int diff = newValue - count[index];
		count[index] = newValue;
		wordLen += diff;
		//assertWellformed
	}

	/**
	 * Getter method to return the length of a word
	 * @return
	 */
	public int getLength()
	{
		return wordLen;
	}

	
	/**
	 * Getter method to return a string representation of the word
	 * @return
	 */
	public String getString()
	{
		return aword;
	}
	
	public int MultiFieldCompare(Word t, int LeastCommonIndex)
	{
		if ( (containsLetter(LeastCommonIndex) ) &&  !(t.containsLetter(LeastCommonIndex)) )
			return 1;
		
		if ( !(containsLetter(LeastCommonIndex) ) &&  (t.containsLetter(LeastCommonIndex)) )
			return -1;
		
		if ( t.wordLen != wordLen )
			return (t.wordLen - wordLen);
		
		return (aword).compareTo(t.aword);
	}
	
	
	public boolean wellFormed()
	{
		//make sure sum of counts is equal to the wordLength
		int sumCounts;
		return false;
	}
	
}

