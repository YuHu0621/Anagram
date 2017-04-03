/*
 * Usage: java Anagram string [[min-len] wordfile] Java Anagram program, Peter
 * van der Linden Jan 7, 1996. Feel free to pass this program around, as long
 * as this header stays intact.
 * 
 * Anagram is a subclass of a WordList. It parses through a text file of words to find anagram of an input string
 */

//TODO: make candidate an ArrayList instead of an array
public class Anagram {

	public static WordList dictionary;
	private Word[] candidate;
	private int minimumLength;
	private int totCandidates = 0;
	private Word anag;

	/**
	 * Constructor for anagram that only takes in a string
	 * Uses 3 as the minimum length and words.txt as the dictionary file
	 * @param a word to find anagrams of
	 */
	public Anagram(String a) {
		anag = new Word(a);
		minimumLength = 3;
		dictionary = new WordList("words.txt");
		candidate = new Word[WordList.MAXWORDS];
	}

	/**
	 * Constructor for anagram that only takes in a string and minLength
	 * Uses words.txt as the dictionary file
	 * @param a word to find anagrams of
	 * @param minLen minimum length of an anagram
	 */
	public Anagram(String a, int minLen) {
		anag = new Word(a);
		minimumLength = minLen;
		dictionary = new WordList("words.txt");
		candidate = new Word[WordList.MAXWORDS];
	}

	/**
	 * Constructor for anagram that takes in a string, minLength,
	 * and dictionary file
	 * @param a word to find anagrams of
	 * @param minLen minimum length of an anagram
	 * @param file dictionary file
	 */
	public Anagram(String a, int minLen, String file) {
		anag = new Word(a);
		minimumLength = minLen;
		dictionary = new WordList(file);
		candidate = new Word[WordList.MAXWORDS];
	}


	/**
	 * DoAnagram method finds matching word in the dictionary that is the
	 * anagram of the input string. print out all the matching words that are
	 * the reordering forms of the input string
	 * 
	 * @param anag the input string
	 */
	public void DoAnagrams() {
		getCandidates(anag);
		printCandidate();

		int RootIndexEnd = sortCandidates(anag);

		System.out.println("Anagrams of " + anag.getString() + ":");
		FindAnagram(anag, new String[50], 0, 0, RootIndexEnd);

		System.out.println("----" + anag.getString() + "----");
	}

	/**
	 * Generate anagram candidates of a word and save in candidate[]
	 * @param w input word
	 */
	public Word[] getCandidates(Word w) {
		totCandidates = 0;
		for (int i = 0; i < dictionary.getTotalWords(); i++)
			if (isCandidate(w, dictionary.getWord(i)))
				candidate[totCandidates++] = dictionary.getWord(i);
		return candidate;
	}

	/**
	 * Check if Word w is a candidate anagram for Word anag A candidate word is
	 * not necessarily a valid candidate word
	 * 
	 * @param w a word in the dictionary
	 * @param target the input word
	 * @return true if Word w is a candidate anagram, return false otherwise
	 */
	private boolean isCandidate(Word target, Word w) {
		return isWord(w) && validLength(target, w) && (fewerOfEachLetter(target, w));
	}

	/**
	 * Check if the Word w in the Dictionary has the valid length that enable it
	 * to form an anagram
	 * 
	 * @param target the word that need to find an anagram
	 * @param w the word parsed from the file
	 * @return true if the Word w has a valid length such that it's a
	 *         candidate of the anagram of target.
	 */
	private boolean validLength(Word target, Word w) {
		return w.getLength() + minimumLength <= target.getLength() || w.getLength() == target.getLength();
	}

	/**
	 * Check if Word w is a word greater than the minLength
	 * @param w the word parsed from the file
	 * @return true if Word w is a word
	 */
	private boolean isWord(Word w) {
		return w.getLength() >= minimumLength;
	}

	/**
	 * Check if the Word w has fewer number of each letter than Word target
	 * 
	 * @param target
	 *            the word that needs to find an anagram
	 * @param w
	 *            the word parsed from the file
	 * @return true if Word w has fewer of each letter than the target
	 *         Word target
	 */
	private boolean fewerOfEachLetter(Word target, Word w) {
		for (int i = 25; i >= 0; i--) {
			if (w.getCount(i) > target.getCount(i))
				return false;
		}
		return true;
	}

	/**
	 * Print all the candidates of the input word
	 */
	private void printCandidate() {
		System.out.println("Candiate words:");
		for (int i = 0; i < totCandidates; i++){
			System.out.print(candidate[i].getString() + ", " + ((i % 4 == 3) ? "\n" : " "));
		}
		System.out.println("");
		System.out.println();
	}

	/**
	 * find the anagram of the input word
	 * 
	 * @param anag
	 * @param WordArray
	 * @param Level
	 * @param StartAt
	 * @param EndAt
	 */
	private void FindAnagram(Word anag, String WordArray[], int Level, int StartAt, int EndAt) {

		Word WordToPass = new Word("");
		for (int i = StartAt; i < EndAt; i++) {
			boolean valid = validCandidate(anag, candidate[i]);
			if (valid) {
				WordArray[Level] = candidate[i].getString();
				for (int j = 25; j >= 0; j--) {
					WordToPass.setCount(j, (byte) (anag.getCount(j) - candidate[i].getCount(j)));
				}
				if (WordToPass.getLength() == 0) {
					/* Found a series of words! */
					for (int j = 0; j <= Level; j++){
						System.out.print(WordArray[j] + " ");
					}
					System.out.println();
				} else if (WordToPass.getLength() >= minimumLength) {
					FindAnagram(WordToPass, WordArray, Level + 1, i, totCandidates);
				}
			}
		}
	}

	/**
	 * Check if Word candidate is a valid anagram candidate for word anag
	 * @param anag the anagram
	 * @param candidate the candidate word from the parsed file
	 * @return true if the word candidate is a valid candidate of word anag
	 */
	private boolean validCandidate(Word anag, Word candidate) {

		for (int j = 25; j >= 0; j--) {
			if (anag.getCount(j) < candidate.getCount(j)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Sort the candidates word
	 * @param anag the anagram
	 * @return RootIndex after the candidates are sorted
	 */
	private int sortCandidates(Word anag) {
		int leastCommonLetter = getLeastCommonLetter();
		quickSort(0, totCandidates - 1, leastCommonLetter);
		return getRootIndex(leastCommonLetter);
	}

	/**
	 * Get the least common letter in the MasterCount array that stores the
	 * number of each letter
	 * @return the least common letter in the candidates
	 */
	private int getLeastCommonLetter() {
		int[] letterCount = totalLetterCount();
		//30 maximum length of words, total number of words
		int min = totCandidates * 30;
		int leastCommonLetter = 25;
		for (int l = 25; l >= 0; l--) {
			if (letterCount[l] != 0 && letterCount[l] < min) {
				// update leastCommonLetter
				min = letterCount[l];
				leastCommonLetter = l;
			}
		}
		return leastCommonLetter;
	}

	/**
	 * Get the count of all the letter of the candidates
	 * @return an array that stores the count of all the letter the
	 *         candidates have
	 */
	private int[] totalLetterCount() {
		int[] masterCount = new int[26];
		for (int i = totCandidates - 1; i >= 0; i--) {
			for (int l = 25; l >= 0; l--) {
				masterCount[l] += candidate[i].getCount(l);
			}
		}
		return masterCount;
	}

	/**
	 * Get the index of the first candidate in the candidates array that
	 * contains the leastCommonLetter
	 * 
	 * @param leastCommonLetter the least common letter among the candidates
	 * @return the index of the first candidate that contains
	 *         LeastCommonLetter
	 */
	private int getRootIndex(int leastCommonLetter) {
		int rootIndexEnd = 0;
		while (rootIndexEnd < totCandidates) {
			if (candidate[rootIndexEnd++].containsLetter(leastCommonLetter)) {
				break;
			}
		}
		return rootIndexEnd;
	}

	/**
	 * Sort the candidates based on the LeastCommonLetter
	 * 
	 * @param lo the left index
	 * @param hi the right index
	 * @param LeastCommonLetter the least common letter among candidates
	 */
	private void quickSort(int lo, int hi, int LeastCommonLetter) {
		// standard quicksort from any algorithm book
		if (lo >= hi)
			return;
		int p = partition(lo, hi, LeastCommonLetter);
		quickSort(lo, p - 1, LeastCommonLetter);
		quickSort(p + 1, hi, LeastCommonLetter);
	}

	/**
	 * The partition function is a helper method for quickSort
	 * 
	 * @param hi the high index
	 * @param lo the low index
	 * @param LeastCommonLetter the least common letter among candidates
	 * @return the index of the the new partition number
	 */
	private int partition(int hi, int lo, int LeastCommonLetter) {
		int last = hi;
		Word pivot = candidate[hi];
		for (int i = hi + 1; i <= lo; i++) { /* partition */
			boolean lessThanPivot = candidate[i].MultiFieldCompare(pivot, LeastCommonLetter) == -1;
			if (lessThanPivot) {
				last++;
				swap(last, i);
			}
		}
		swap(last, hi);
		return last;
	}

	/**
	 * Swap the position of two words at index d1 and d2 in the candidates array
	 * 
	 * @param d1 index d1
	 * @param d2 index d2
	 */
	private void swap(int d1, int d2) {
		Word tmp = candidate[d1];
		candidate[d1] = candidate[d2];
		candidate[d2] = tmp;
	}
	
	
	
	private boolean wellFormed(Anagram testAnagram) 
	{
		//TODO: fill in
		//check that all indices less than totCandidates in candidate has a value
		if(totCandidates == 0) {
			//don't need to do anything
		}
		else {
			for(int i = 0; i < totCandidates; i++) {
				if(candidate[i] == null) {
					return false;
				}
			}
		}
		//TODO: check that all indices greater than totCandidates are null
		
		
		return true;
	}

}