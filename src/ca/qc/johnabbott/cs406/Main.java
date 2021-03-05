package ca.qc.johnabbott.cs406;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Trie test =  new Trie();

        // Add some words to Trie
        test.add("aardvark");
        test.add("aardwolf");
        test.add("ant");
        test.add("anteater");
        test.add("armadillo");
        test.add("ayeaye");
        test.add("capybara");
        test.add("opposum");
        test.add("squirrel");

        // Test contains() function
        System.out.println("Contains squirrel: " + test.contains("squirrel"));
        System.out.println("Contains ants: " + test.contains("ants") + "\n");

        List<String> list; // Stores test results

        // Test complete() function
        list = test.complete("aard", 99);
        System.out.println("complete aard:");
        System.out.println(list + "\n");

        list = test.complete("aard", 1);
        System.out.println("complete aard (limit 1):");
        System.out.println(list + "\n");

        list = test.complete("ant", 99);
        System.out.println("complete ant:");
        System.out.println(list + "\n");

        list = test.complete("a", 99);
        System.out.println("complete a (no limit):");
        System.out.println(list + "\n");

        list = test.complete("a", 3);
        System.out.println("complete a (limit 3):");
        System.out.println(list + "\n");

        list = test.complete("all", 99);
        System.out.println("complete all:");
        System.out.println(list);
    }
}
