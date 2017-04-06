Word - Renamed total to wordLen
UsefulConstants - Deleted class
WordList - 
	Moved MAXWORDS, MAXWORDLEN, and EOF from UsefulConstants
	Deleted implementation of UsefulConstants
	ReadDict() - 
		Renamed buffer to word
		Renamed i in while loop to wordLen
Anagram - 
	Deleted implementation of UsefulConstants
	Added javadoc comments for each method
	Renamed class from anagram to Anagram
	getCandidates() - 
		Extracted isCandidate(Word target, Word w) from code checking if the word is a candidate
		Extracted isWord() to check if the word is over the minimum length
		Extracted validLength() to check if the word plus minimum length is less than or equal to the wordLen of the target or if the length of the word itself is equal to the length of the target
		Extracted fewerOfEachLetter()
	FindAnagrams() - 
		Deleted the first for loop
		Moved variables to first use i, j.
		Extracted totalLetterCount(): int[] to replace the second for loop.
		Extracted a getLeastCommonLetter() method to replace the third for loop.
		Extract getRootIndex(int i)to replace the last for loop.
	quickSort() - 
		Changed variable name left and right to lo and hi. 
		Extracted partition(int lo, int hi, int A). 
		In the partition method, extracted a local variable lessThanPivot.
	Changed subclass relation to WordList to a composition relation. Now contains a WordList object named dictionary.
	Added three constructors for Anagram so that minimumLength and file name can be optional input for the user. The default minimumLength is 3 and the default file name is words.txt.
	Changed all methods and variables in Anagram from static to non-static except for dictionary
	Removed Word anagram from parameter of methods (DoAnagrams(), fewerOfEachLetter(), findAnagram())
	Added access types to all variables
	Made first letter of all variables lowercase
WordList - 
	Added a constructor that passes in the name of the file
	Renamed getIndex() (which takes in an index and returns a Word) to getWord()
	Added access types to all variables.
Word - 
	Added access types to all variables
	Made first letter of all variables lowercase
	Made aword and WordLen private
	Added getter/setter methods for count[index], wordLen, and aword
	Word() - moved ch inside the for loop
Anagram - 
	getCandidates() - changed to return candidate[]
	totalLetterCount() - Fixed an error adding up all counts of all letters instead of setting masterCount to equal each count and overwriting it.
	Changed what min in getLeastCommonLetter is to a lower value (30 times the number of total candidates --> the most times a letter could possibly appear) and changed comment to be more accurate.
	Changed for loops to go from low to high instead of high to low
	main() - deleted
Game - added a game class which has a composition relationship with Anagram and moved the main method there
Word - 
	Update the wordLen when the count is changed
	wellFormed() - added one
Anagram - 
	FindAnagram() - 
		Deleted the if statement that did the above task before
		Changed the parameter Word anagram to int[] count
		Extracted recursive method
		Extracted base case method with no parameters
		Moved RootEndIndex calculation from DoAnagram() to this method
	DoAnagram() - moved RootEndIndex calculation to FindAnagram()
	getCandidates() - renamed to generateCandidates that returns nothing and has no parameters
	isCandidate() - removed Word target parameter
	validLength() - removed Word target parameter
	fewerOfEachLetter() - removed Word target parameter
	sortCandidates() - removed Word anag parameter
	wellFormed() - added one
	validCandidate() - replaced Word anagram parameter with an int[] of anagCount
	sortedWellFormed() - added a method to check that sorted candidate arrays are well formed







