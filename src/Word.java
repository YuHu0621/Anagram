/**
 * Word class stores information for every word
 * including the number of each letter in a word, the number of letters in a word, and the word itself 
 * @author yuhu
 *
 */
public class Word  {
	 int count[] = new int[26];  // count of each letter in the word
	 int wordLen;  // number of letters in the word
	 String aword;  // the word

	public Word(String s) { // construct an entry from a string
		int ch;
		aword = s;
		wordLen = 0;
		s = s.toLowerCase();
		
		for (int i = s.length()-1; i >= 0; i--) {
			ch = s.charAt(i) - 'a';
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
}

