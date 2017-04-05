# Anagram
Refactoring

Surrounded body of all for loops and if statements with curly braces

Delete interface UsefulConstants. 

WordList contains static variable MAXWORDS, MAXWORDLEN and EOF

anagram class: 
getCandidate(Word w): extracted a boolean method isCandidate() out of the if condition. 
isCandidate() is further broke down into 3 methods: isWord(), validLength () and fewerOfEachLetter()

sortCandidate (): 
Deleted the first for loop.

Moved variables to first use: i, j.
 
Extracted totalLetterCount(): int[] to replace the second for loop. 

Extracted a getLeastCommonLetter() method to replace the third for loop

Extract getRootIndex(int i)to replace the last for loop.

quickSort(int lo, int hi, int leastCommonLetter)
Changed variable name left and right to lo and hi. 

Extracted partition(int lo, int hi, int A). 

In the partition method, extracted a local variable lessThanPivot.

Changed the subclass relation to composition relation. Anagram class contains a WordList object named Dictionary. 

Added a global variable dictionary in the Anagram class.

Added three constructors for anagram so that minimumLength and file name can be optional input for the user. The default minimumLength is 3 and the default file name is words.txt

Changed all methods in Anagram from static to non-static. 

Removed Word anagram from parameter of methods (DoAnagrams, fewerOfEachLetter,findAnagram)in Anagram class. 

Added a constructor in WordList that passes in the name of the file.

Renamed getIndex() in WordList (which takes in an index and returns a word) to getWord()

Added access types to variables in anagrams, Word, and WordList

Replaced references to totalWords in WordList with a getter method in WordList.

Renamed anagram class to Anagram

Made first letter of variables in Anagram lowercase

Made wordLen and aword in Word class private and added getter/setter methods where necessary.

Fixed an error in totalLetterCount, adding up all counts of all letters instead of setting masterCount to equal each count and overwriting it.

Changed what min in getLeastCommonLetter is to a lower value (30 times the number of total candidates) and changed comment to be more accurate

Renamed PrintCandidates() to printCandidates()

Changed for loops to start from the bottom and go to the end where applicable

Moved ch inside for loop in Word() constructor

Automatically update word length when the count of any letter is changed, deleted if statement in FindAnagram() that did this after

Changed parameter Word anagram to int[]count in FindAnagram method. 

Added FindAnagram() 

Moved int RootEndIndex = sortCandidate() in DoAngram() to inside FindAnagram.

Changed method from FindAngram(anagram,new String[50], 0, 0, RootIndexEnd) to FindAngarm()
