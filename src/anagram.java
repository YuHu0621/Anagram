/*
 * Usage: java Anagram string [[min-len] wordfile] Java Anagram program, Peter
 * van der Linden Jan 7, 1996. Feel free to pass this program around, as long
 * as this header stays intact.
 * 
 * Anagram is a subclass of a WordList. It parses through a text file of words to find anagram of an input string
 */

public class anagram {

	public static WordList Dictionary;
	public static Word[] Candidate;
	private int MinimumLength;
	private static int totCandidates = 0;
	public Word anag;

	/**
	 * Constructor for anagram that only takes in a string
	 * Uses 3 as the minimum length and words.txt as the dictionary file
	 * @param a word to find anagrams of
	 */
	public anagram(String a) {
		anag = new Word(a);
		MinimumLength = 3;
		Dictionary = new WordList("words.txt");
		Candidate = new Word[WordList.MAXWORDS];
	}

	/**
	 * Constructor for anagram that only takes in a string and minLength
	 * Uses words.txt as the dictionary file
	 * @param a word to find anagrams of
	 * @param minLen minimum length of an anagram
	 */
	public anagram(String a, int minLen) {
		anag = new Word(a);
		MinimumLength = minLen;
		Dictionary = new WordList("words.txt");
		Candidate = new Word[WordList.MAXWORDS];
	}

	/**
	 * Constructor for anagram that takes in a string, minLength,
	 * and dictionary file
	 * @param a word to find anagrams of
	 * @param minLen minimum length of an anagram
	 * @param file dictionary file
	 */
	public anagram(String a, int minLen, String file) {
		anag = new Word(a);
		MinimumLength = minLen;
		Dictionary = new WordList(file);
		Candidate = new Word[WordList.MAXWORDS];
	}

	/**
	 * Main method which takes in an argument and creates an object of anagram
	 * using the appropriate constructor
	 * @param argv
	 */
	public static void main(String[] argv) {
		if (argv.length < 1 || argv.length > 3) {
			System.err.println("Usage: java anagram  string-to-anagram " + "[min-len [word-file]]");
			return;
		}

		//default valid minimum word length
		int minLen = 3;
		if (argv.length >= 2)
			minLen = Integer.parseInt(argv[1]);

		anagram myAnagram;
		if (argv.length == 1) {
			myAnagram = new anagram(argv[0]);

		} else if (argv.length == 2) {
			myAnagram = new anagram(argv[0], minLen);
		} else {
			myAnagram = new anagram(argv[0], minLen, argv[2]);
		}

		myAnagram.DoAnagrams();
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
		getCandidates(anag);
		PrintCandidate();

		int RootIndexEnd = sortCandidates(anag);

		System.out.println("Anagrams of " + anag.aword + ":");
		FindAnagram(anag, new String[50], 0, 0, RootIndexEnd);

		System.out.println("----" + anag.aword + "----");
	}

	/**
	 * Get anagram candidates of a word
	 * 
	 * @param w
	 *            input word
	 */
	public void getCandidates(Word w) {
		totCandidates = 0;
		for (int i = 0; i < Dictionary.getTotalWords(); i++)
			if (isCandidate(w, Dictionary.getWord(i)))
				Candidate[totCandidates++] = Dictionary.getWord(i);
	}

	/**
	 * Check if Word w is a candidate anagram for Word anag A candidate word is
	 * not necessarily a valid candidate word
	 * 
	 * @param w
	 *            a word in the dictionary
	 * @param target
	 *            the input word
	 * @return Return true if Word w is a candidate anagram, return false
	 *         otherwise
	 */
	private boolean isCandidate(Word target, Word w) {
		return isWord(w) && validLength(target, w) && (fewerOfEachLetter(target, w));
	}

	/**
	 * Check if the Word w in the Dictionary has the valid length that enable it
	 * to form an anagram
	 * 
	 * @param target
	 *            the word that need to find an anagram
	 * @param w
	 *            the word parsed from the file
	 * @return return true if the Word w has a valid length such that it's a
	 *         candidate of the anagram of target.
	 */
	private boolean validLength(Word target, Word w) {
		return w.wordLen + MinimumLength <= target.wordLen || w.wordLen == target.wordLen;
	}

	/**
	 * Check if Word w is a word
	 * 
	 * @param w
	 *            the word parsed from the file
	 * @return return true if Word w is a word
	 */
	private boolean isWord(Word w) {
		return w.wordLen >= MinimumLength;
	}

	/**
	 * Check if the Word w has fewer number of each letter than Word target
	 * 
	 * @param target
	 *            the word that needs to find an anagram
	 * @param w
	 *            the word parsed from the file
	 * @return return true if Word w has fewer of each letter than the target
	 *         Word target
	 */
	private boolean fewerOfEachLetter(Word target, Word w) {
		for (int i = 25; i >= 0; i--) {
			if (w.count[i] > target.count[i])
				return false;
		}
		return true;
	}

	/**
	 * Print all the candidates of the input word
	 */
	private void PrintCandidate() {
		System.out.println("Candiate words:");
		for (int i = 0; i < totCandidates; i++)
			System.out.print(Candidate[i].aword + ", " + ((i % 4 == 3) ? "\n" : " "));
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
			boolean valid = validCandidate(anag, Candidate[i]);
			if (valid) {
				WordArray[Level] = Candidate[i].aword;

				for (int j = 25; j >= 0; j--) {
					WordToPass.count[j] = (byte) (anag.count[j] - Candidate[i].count[j]);
					if (WordToPass.count[j] != 0) {
						WordToPass.wordLen += WordToPass.count[j];
					}
				}
				if (WordToPass.wordLen == 0) {
					/* Found a series of words! */
					for (int j = 0; j <= Level; j++)
						System.out.print(WordArray[j] + " ");
					System.out.println();
				} else if (WordToPass.wordLen < MinimumLength) {
					; /* Don't call again */
				} else {
					FindAnagram(WordToPass, WordArray, Level + 1, i, totCandidates);
				}
			}
		}
	}

	/**
	 * Check if Word candidate is a valid anagram candidate for word anag
	 * 
	 * @param anag
	 *            the anagram
	 * @param candidate
	 *            the candidate word from the parsed file
	 * @return return true if the word candidate is a valid candidate of word
	 *         anag
	 */
	private boolean validCandidate(Word anag, Word candidate) {

		for (int j = 25; j >= 0; j--) {
			if (anag.count[j] < candidate.count[j]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Sort the Candidates word
	 * 
	 * @param anag
	 *            the anagram
	 * @return return RootIndex after the Candidates are sorted
	 */
	private int sortCandidates(Word anag) {
		int leastCommonLetter = getLeastCommonLetter();
		quickSort(0, totCandidates - 1, leastCommonLetter);
		return getRootIndex(leastCommonLetter);
	}

	/**
	 * Get the least common letter in the MasterCount array that stores the
	 * number of each letter
	 * 
	 * @param MasterCount
	 *            the number of each letter in all the candidates
	 * @return return the least common letter in the Candidates
	 */
	private int getLeastCommonLetter() {
		int[] MasterCount = totalLetterCount();
		int min = WordList.MAXWORDS * 5;
		int leastCommonLetter = 25;
		for (int l = 25; l >= 0; l--) {
			if (MasterCount[l] != 0 && MasterCount[l] < min) {
				// update leastCommonLetter
				min = MasterCount[l];
				leastCommonLetter = l;
			}
		}
		return leastCommonLetter;
	}

	/**
	 * Get the count of all the letter of the Candidates
	 * 
	 * @return return an array that stores the count of all the letter the
	 *         candidates have
	 */
	private int[] totalLetterCount() {
		int[] MasterCount = new int[26];
		for (int ind = totCandidates - 1; ind >= 0; ind--) {
			for (int l = 25; l >= 0; l--) {
				MasterCount[l] = Candidate[ind].count[l];
			}
		}
		return MasterCount;
	}

	/**
	 * Get the index of the first candidate in the Candidates array that
	 * contains the leastCommonLetter
	 * 
	 * @param LeastCommonLetter
	 *            the least common letter among the candidates
	 * @return return the index of the first candidate that contains
	 *         LeastCommonLetter
	 */
	private int getRootIndex(int LeastCommonLetter) {
		int rootIndexEnd = 0;
		while (rootIndexEnd < totCandidates) {
			if (Candidate[rootIndexEnd++].containsLetter(LeastCommonLetter)) {
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
	 *            the least common letter among Candidates
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
	 * @param hi
	 *            the high index
	 * @param lo
	 *            the low index
	 * @param LeastCommonLetter
	 *            the least common letter among Candidates
	 * @return return the index of the the new partition number
	 */
	private int partition(int hi, int lo, int LeastCommonLetter) {
		int last = hi;
		Word pivot = Candidate[hi];
		for (int i = hi + 1; i <= lo; i++) { /* partition */
			boolean lessThanPivot = Candidate[i].MultiFieldCompare(pivot, LeastCommonLetter) == -1;
			if (lessThanPivot) {
				last++;
				swap(last, i);
			}
		}
		swap(last, hi);
		return last;
	}

	/**
	 * Swap the position of two words at index d1 and d2 in the Candidates array
	 * 
	 * @param d1
	 *            index d1
	 * @param d2
	 *            index d2
	 */
	private void swap(int d1, int d2) {
		Word tmp = Candidate[d1];
		Candidate[d1] = Candidate[d2];
		Candidate[d2] = tmp;
	}

}