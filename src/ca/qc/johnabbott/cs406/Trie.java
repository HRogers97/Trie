package ca.qc.johnabbott.cs406;

import java.util.ArrayList;
import java.util.List;

public class Trie implements Lexicon{

    public class Node {
        public Character element;
        public List<Node> children;
        public boolean isEndOfWord; //Flag for nodes that contain the ending of a word

        public Node(Character element) {
            this.element = element;
        }
    }

    private Node root;

    public Trie (){
        root = new Node(' ');
    }

    @Override
    public void add(String word) {
        add(word, root);
    }

    private void add(String word, Node current){
        if(word.length() == 0){
            // Entirety of the word has been added
            // Flag the end of the word and exit
            current.isEndOfWord = true;

            return;
        }

        // Current letter of the word being processed
        char letter = word.toCharArray()[0];

        if(current.children == null){
            // Reached a leaf node, add the rest of the nodes necessary to complete the word
            Node newChild = new Node(letter);
            current.children = new ArrayList<>();
            current.children.add(newChild);

            add(word.substring(1), newChild);
        }
        else {
            // Check if any child node contains the current letter
            for (Node child : current.children) {
                if (child.element == letter) {
                    add(word.substring(1), child);
                    return;
                }
            }

            // No child nodes contain the letter
            // Add a child node with the letter
            Node newChild = new Node(letter);
            current.children.add(newChild);
            add(word.substring(1), newChild);
        }
    }

    @Override
    public boolean contains(String word) {
        return contains(word, root);
    }

    private boolean contains(String word, Node current){
        // Entire word has been found
        if(word.length() == 0){
            return current.children == null;
        }

        // Leaf node reached without finding entire word
        // Word is not in the Trie
        if(current.children == null) {return false;}

        // Current letter of the word being processed
        char letter = word.toCharArray()[0];

        // Search children for the current letter
        for(Node child : current.children){
            if(child.element == letter){
                return contains(word.substring(1), child);
            }
        }

        // No child contains the current letter
        // Word is not in the Trie
        return false;
    }

    @Override
    public List<String> complete(String prefix, int limit) {
        // Get to the node containing the last letter of the prefix
        Node start = getToPrefix(prefix);

        // Prefix is not in the Trie
        if(start == null){return new ArrayList<>();}

        return complete(start, prefix.substring(0, prefix.length() - 1), limit, new ArrayList<>());
    }

    // Takes in a string and returns the node containing the end of the string
    private Node getToPrefix(String prefix){
        char[] prefChars = prefix.toCharArray();
        int currChar = 0; // Entirety of the word has been added
        Node current = root; // Start looking at the root of the Trie
        boolean nextLetterFound;

        while(currChar < prefChars.length){
            nextLetterFound = false;

            // Reached leaf node without finding the prefix
            // Prefix is not in the Trie
            if(current.children == null){return null;}

            // Check child nodes for the next letter
            for(Node child : current.children){
                if(child.element == prefChars[currChar]){ //Letter has been found
                    //Stop searching the children and start searching from the next node
                    current = child;
                    currChar++;
                    nextLetterFound = true;
                    break;
                }
            }

            // Letter was not found in the child nodes
            // Prefix is not in the Trie
            if(!nextLetterFound){return null;}
        }

        return current;
    }

    private List<String> complete(Node current, String currWord, int limit, List<String> words){
        // Piece word together while looping through nodes
        currWord += current.element;

        // Reached the end of a word, add the word
        if(current.isEndOfWord){
            words.add(currWord);

            // Stop if word limit has been reached
            if(words.size() >= limit)
                return words;
        }

        // If the node has children, check the children for more words
        if(current.children != null){
            for(Node child : current.children){
                complete(child, currWord, limit, words);

                // Stop if word limit has been reached
                if(words.size() >= limit)
                    return words;
            }
        }

        return words;
    }
}
