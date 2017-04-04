
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
	 * Constructor for anagram that only takes in a string Uses 3 as the minimum
	 * length and words.txt as the dictionary file
	 * 
	 * @param a
	 *            word to find anagrams of
	 */
	public Anagram(String a) {
		anag = new Word(a);
		minimumLength = 3;
		dictionary = new WordList("words.txt");
		candidate = new Word[WordList.MAXWORDS];
		assert wellFormed();
	}

	/**
	 * Constructor for anagram that only takes in a string and minLength Uses words.txt as the dictionary file
	 * 
	 * @param a
	 *            word to find anagrams of
	 * @param minLen
	 *            minimum length of an anagram
	 */
	public Anagram(String a, int minLen) {
		anag = new Word(a);
		minimumLength = minLen;
		dictionary = new WordList("words.txt");
		candidate = new Word[WordList.MAXWORDS];
		assert wellFormed();
	}

	/**
	 * Constructor for anagram that takes in a string, minLength, and dictionary
	 * file
	 * 
	 * @param a
	 *            word to find anagrams of
	 * @param minLen
	 *            minimum length of an anagram
	 * @param file
	 *            dictionary file
	 */
	public Anagram(String a, int minLen, String file) {
		anag = new Word(a);
		minimumLength = minLen;
		dictionary = new WordList(file);
		candidate = new Word[WordList.MAXWORDS];
		assert wellFormed();
	}

	/**
	 * DoAnagram method finds matching word in the dictionary that is the
	 * anagram of the input string. print out all the matching words that are
	 * the reordering forms of the input string
	 * 
	 * @param anag
	 *            the input string
	 */
	public void DoAnagrams() {
		generateCandidates();
		printCandidate();

		int RootIndexEnd = sortCandidates();

		System.out.println("Anagrams of " + anag.getString() + ":");
		//FindAnagram(anag.getTotalCount(), new String[50], 0, 0, RootIndexEnd);
		FindAnagram(anag, RootIndexEnd);
		System.out.println("----" + anag.getString() + "----");
	}

	/**
	 * Generate anagram candidates of a word and save in candidate[]
	 */
	public void generateCandidates() {
		totCandidates = 0;
		for (int i = 0; i < dictionary.getTotalWords(); i++) {
			if (isCandidate(dictionary.getWord(i))) {
				candidate[totCandidates] = dictionary.getWord(i);
				totCandidates++;
			}
		}
		assert wellFormed();

	}

	/**
	 * Check if Word w is a candidate anagram for Word anag A candidate word is
	 * not necessarily a valid candidate word
	 * 
	 * @param w
	 *            a word in the dictionary
	 * @return true if Word w is a candidate anagram, return false otherwise
	 */
	private boolean isCandidate(Word w) {
		return isWord(w) && validLength(w) && (fewerOfEachLetter(w));
	}

	/**
	 * Check if the Word w in the Dictionary has the valid length that enable it
	 * to form an anagram
	 * 
	 * @param target
	 *            the word that need to find an anagram
	 * @param w
	 *            the word parsed from the file
	 * @return true if the Word w has a valid length such that it's a candidate
	 *         of the anagram of target.
	 */
	private boolean validLength(Word w) {
		return w.getLength() + minimumLength <= anag.getLength() || w.getLength() == anag.getLength();
	}

	/**
	 * Check if Word w is a word greater than the minLength
	 * 
	 * @param w
	 *            the word parsed from the file
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
	 * @return true if Word w has fewer of each letter than the target Word
	 *         target
	 */
	private boolean fewerOfEachLetter(Word w) {
		for (int i = 25; i >= 0; i--) {
			if (w.getCount(i) > anag.getCount(i))
				return false;
		}
		return true;
	}

	/**
	 * Print all the candidates of the input word
	 */
	private void printCandidate() {
		System.out.println("Candiate words:");
		for (int i = 0; i < totCandidates; i++) {
			System.out.print(candidate[i].getString() + ", " + ((i % 4 == 3) ? "\n" : " "));
		}
		System.out.println("");
		System.out.println();
	}
//
//	/**
//	 * find the anagram of the input word
//	 * 
//	 * @param anagram
//	 * @param WordArray
//	 * @param Level
//	 * @param StartAt
//	 * @param EndAt
//	 */
//	private void FindAnagram(Word anagram, String[] WordArray, int Level, int StartAt, int EndAt) {
//
//		Word WordToPass = new Word("");
//		for (int i = StartAt; i < EndAt; i++) {
//			Word word = candidate[i];
//			boolean valid = validCandidate(anagram, word);
//			if (valid) {
//				WordArray[Level] = word.getString();
//				for (int j = 0; j < 26; j++) {
//					WordToPass.setCount(j, (byte) (anagram.getCount(j) - word.getCount(j)));
//				}
//				if (WordToPass.getLength() == 0) {
//					/* Found a series of words! */
//					for (int j = 0; j <= Level; j++) {
//						System.out.print(WordArray[j] + " ");
//					}
//					System.out.println();
//				} else if (WordToPass.getLength() >= minimumLength) {
//					FindAnagram(WordToPass, WordArray, Level + 1, i, totCandidates);
//				}
//			}
//		}
//	}

	/**
	 * FindAnagram calls the recursive helper method
	 * @param anag the anagram word
	 * @param RootEndIndex the index of the candidate word that contains the leastCommonLetter
	 */
	private void FindAnagram(Word anag, int RootEndIndex){
		FindAnagram(anag.getTotalCount(), new String[50], 0, 0, RootEndIndex);
	}
	
	/**
	 * Find the anagram by recursive search
	 * @param count count of the number of letter from A-Z
	 * @param WordArray WordArray stores the valid group of candidates for the anagram
	 * @param Level level keeps track of the search
	 * @param StartAt start index in the candidate array
	 * @param EndAt end index in the candidate array
	 */
	private void FindAnagram(int[] count, String[] WordArray, int Level, int StartAt, int EndAt) {
		int[] WordCountToPass = new int[count.length];
		for (int i = StartAt; i < EndAt; i++) {
			Word word = candidate[i];
			boolean valid = validCandidate(count, word);
			if (valid) {
				WordArray[Level] = word.getString();
				int wordToPathLen = 0;
				for (int j = 0; j < 26; j++) {
					WordCountToPass[j] = (byte) (count[j] - word.getCount(j));
					wordToPathLen += WordCountToPass[j];
				}
				if (wordToPathLen == 0) {
					/* Found a series of words! */
					for (int j = 0; j <= Level; j++) {
						System.out.print(WordArray[j] + " ");
					}
					System.out.println();
				} else if (wordToPathLen >= minimumLength) {
					FindAnagram(WordCountToPass, WordArray, Level + 1, i, totCandidates);
				}
			}
		}
	}
	/**
	 * Check if Word candidate is a valid anagram candidate for word anag
	 * 
	 * @param anagram
	 *            the anagram
	 * @param candidate
	 *            the candidate word from the parsed file
	 * @return true if the word candidate is a valid candidate of word anag
	 */
	private boolean validCandidate(int[] anagCount, Word candidate) {

		for (int j = 25; j >= 0; j--) {
			if (anagCount[j] < candidate.getCount(j)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Sort the candidates word
	 * 
	 * @param anag
	 *            the anagram
	 * @return RootIndex after the candidates are sorted
	 */
	private int sortCandidates() {
		int leastCommonLetter = getLeastCommonLetter();
		quickSort(0, totCandidates - 1, leastCommonLetter);
		assert wellFormed();
		return getRootIndex(leastCommonLetter);
	}

	/**
	 * Get the least common letter in the MasterCount array that stores the
	 * number of each letter
	 * 
	 * @return the least common letter in the candidates
	 */
	private int getLeastCommonLetter() {
		int[] letterCount = totalLetterCount();
		// 30 maximum length of words, total number of words
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
	 * 
	 * @return an array that stores the count of all the letter the candidates
	 *         have
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
	 * @param leastCommonLetter
	 *            the least common letter among the candidates
	 * @return the index of the first candidate that contains LeastCommonLetter
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
	 * @param lo
	 *            the left index
	 * @param hi
	 *            the right index
	 * @param LeastCommonLetter
	 *            the least common letter among candidates
	 */
	private void quickSort(int lo, int hi, int LeastCommonLetter) {
		// standard quicksort from any algorithm book
		if (lo >= hi)
			return;
		int p = partition(lo, hi, LeastCommonLetter);
		quickSort(lo, p - 1, LeastCommonLetter);
		quickSort(p + 1, hi, LeastCommonLetter);
		assert wellFormed();
	}

	/**
	 * The partition function is a helper method for quickSort
	 * 
	 * @param hi
	 *            the high index
	 * @param lo
	 *            the low index
	 * @param LeastCommonLetter
	 *            the least common letter among candidates
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
		assert wellFormed();
		return last;
		
	}

	/**
	 * Swap the position of two words at index d1 and d2 in the candidates array
	 * 
	 * @param d1
	 *            index d1
	 * @param d2
	 *            index d2
	 */
	private void swap(int d1, int d2) {
		Word tmp = candidate[d1];
		candidate[d1] = candidate[d2];
		candidate[d2] = tmp;
	}

	private boolean wellFormed() {
		// TODO: fill in
		// check that dictionary and candidate are not null
		if (dictionary == null) {
			return false;
		}
		if (candidate == null) {
			return false;
		}
		// check that all indices less than totCandidates in candidate has a
		// value
		if (totCandidates == 0) {
			// don't need to do anything
		} else {
			for (int i = 0; i < totCandidates; i++) {
				if (candidate[i] == null) {
					return false;
				}
			}
		}
		// TODO: check that all indices greater than totCandidates are null

		return true;
	}

}