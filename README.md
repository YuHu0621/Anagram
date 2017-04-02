# Anagram
Refactoring

Surrounded body of for loop and if statement in fewerOfEachLetter with curly braces

Yu-edit on March 30
Delete interface UsefulConstants. 

WordList contains static variable MAXWORDS and MAXWORDLEN

No change in Word class

anagram class: 
getCandidate(Word w): In the original method, the if statement checks three conditions. We extract a boolean method isCandidate(w, Dictionary[i]) to determine if the word in the dictionary can be a candidate of the anagram. If isCandidate() return true, the word is added to the array. The three conditions are extracted into three methods: isWord(word w), validLength (Word target, Word w) and fewerOfEachLetter(Word target, Word w)

sortCandidate (Word anag): 
In the original method, there’s a redundant for loop that put 0 in every index of MasterCount array. The for loop is deleted.
We delete int i, j in the original method and put it into each for loop. 
The 2nd for loop in the original method counts the total number of each letter of all the candidates. So we extract totalLetterCount() that returns an int[]. 
In the original method, the 3rd for loop get the leastCommonLetter from the MasterCount array obtained from the 2nd for loop. We extract a getLeastCommonLetter() method.
The last for loop in the original method get the root index of the least common letter. So we extract getRootIndex(int i).

quickSort(int lo, int hi, int leastCommonLetter)
we change variable name left and right to lo and hi. Because quickSort method do two steps: partition and recursion. we extract partition(int lo, int hi, int A) out. 
In the partition method, we extract a boolean variable lessThanPivot out of the if statement
