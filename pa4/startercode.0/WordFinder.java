// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CS 455 PA4
// Fall 2020

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * the entrance of the program with main method in it
 * this program finds all the anagrams based on a given dictionary for a given set of letters and its subsets,
 * and list them in the order of their score
 */
public class WordFinder {
    public static final String DEFAULT_DICTIONARY = "startercode.0/sowpods.txt";

    public static void main(String[] args) {
        String fileName; // used to store the name of the dictionary
        Scanner in = new Scanner(System.in);
        String rackWord;
        // if receive a string from console, the use it as the dictionary file
        if (args.length != 0) {
            fileName = args[0];
        } else { // else use the default dictionary
            fileName = DEFAULT_DICTIONARY;
        }
        System.out.println("Type . to quit.");
        try {
            AnagramDictionary anaDict = new AnagramDictionary(fileName); // initialize the anagram dictionary object
            while (true) { // keep accepting new rack word until it gets a "."
                System.out.print("Rack? ");
                rackWord = in.next(); // get the rack word
                // if it equals to ".", then quit the program
                if (rackWord.equals(".")) {
                    break;
                }
                Rack newRack = new Rack(rackWord); // initialize the rack object
                int rackSize = newRack.constructRackMap(anaDict); // get all the anagrams and return the number
                System.out.println("We can make "+ rackSize +" words from \""+ rackWord +"\"");
                if (rackSize != 0) {
                    System.out.println("All of the words with their scores (sorted by score):");
                }
                newRack.printSorted(System.out); // print the anagrams and their scores
            }
        } catch (FileNotFoundException e) { // deal with FileNotFoundException
            System.out.println("ERROR: Dictionary file \"" + fileName + "\" does not exist.");
            System.out.println("Exiting program.");
        } catch (IllegalDictionaryException e) { // deal with IllegalDictionaryException thrown from anagram dictionary
            System.out.println("ERROR: Illegal dictionary: dictionary file has a duplicate word: "
                    + e.getMessage());
            System.out.println("Exiting program.");
        }
    }
}
