
public class Game {

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

		Anagram myAnagram;
		if (argv.length == 1) {
			myAnagram = new Anagram(argv[0]);

		} else if (argv.length == 2) {
			myAnagram = new Anagram(argv[0], minLen);
		} else {
			myAnagram = new Anagram(argv[0], minLen, argv[2]);
		}

		myAnagram.DoAnagrams();
	}

}
