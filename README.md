# Trie

This is a tree structure used for storing and searching for words. The top node is blank, and every child node contains a letter forming a word as it goes down.

## Usage
Tries have three main functions

#### Add(word)
Adds a word to the trie

#### Contains(word)
Checks if the word is in the trie

#### Complete(word)
Takes in a prefix string and checks for words in the trie that start with the prefix  
For example, trie.complete("ant") will return both "ant" and "anteater"

## Example
![Trie example](https://i.imgur.com/Zdr3lPw.png)

Red dots represent the end of a word