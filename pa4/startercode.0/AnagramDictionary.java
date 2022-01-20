// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CS 455 PA4
// Fall 2020

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   // store the dictionary in a map, The key is the canonical form of a word, corresponding value is
   // anagrams of the key (stored in an array list)
   private Map<String, ArrayList<String>> anagramMap;


   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {
      File dictFile = new File(fileName); // create a file object
      anagramMap = new HashMap<>(); // use hash table as representation
      try(Scanner in = new Scanner(dictFile)){
         // read in every word of the dictionary file
         while(in.hasNext()){
            String aWord = in.next();
            // get the canonical form of a word
            char[] toSort = aWord.toCharArray();
            Arrays.sort(toSort);
            String canonicalForm = String.valueOf(toSort);

            // put the canonical-form word into the map as key, if it doesn't exist, then create a new array list
            // object as the value.
            if (anagramMap.get(canonicalForm) == null){
               anagramMap.put(canonicalForm, new ArrayList<>());
            }
            // if the canonical-form word exists in the map, then before inserting the original word into the
            // array list, we have to check whether it is already stored inside.
            // if yes, then throw an IllegalDictionaryException.
            ArrayList<String> current = anagramMap.get(canonicalForm);
            if (current.contains(aWord)){
               throw new IllegalDictionaryException(aWord);
            }
            // After checking, we can safely put the original word into the array list and update the value
            current.add(aWord);
            anagramMap.put(canonicalForm, current);
         }
      }
   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
      // get the canonical form of the word s
      char[] toSort = s.toCharArray();
      Arrays.sort(toSort);
      String canonicalForm = String.valueOf(toSort);

      // find the words of the same canonical form as s (anagrams), and return this list of words, and we'll handle
      // null return value in the caller method
      return anagramMap.get(canonicalForm);
   }
   
   
}
