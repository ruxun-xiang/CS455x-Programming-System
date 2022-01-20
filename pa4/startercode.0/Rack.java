// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CS 455 PA4
// Fall 2020

import java.io.PrintStream;
import java.util.*;

/**
   A Rack of Scrabble tiles
 */

public class Rack {
   private Map<Character, Integer> characterMap; // used to store the unique letters of a word and
   // the number of occurrences of each letter
   private Map<String, Integer> rackMap; // used to store the rack word and its score



   public Rack(String rackWord){
      // initialize the rack map variable, we don't need to consider the storing order,
      // so hash map is a better choice
      rackMap = new HashMap<>();
      // initialize the character map variable, we want the unique letters to be ordered alphabetically,
      // so we use tree map
      characterMap = new TreeMap<>();

      // initialize the character map, put all the unique letters and their number of occurrences inside
      for (int i = 0; i < rackWord.length(); i++){
         // get the current number of occurrence of the letter
         Integer oldValue = characterMap.get(rackWord.charAt(i));
         // if there's no valid value for the letter, then set its number of occurrence to 1
         if (oldValue == null){
            characterMap.put(rackWord.charAt(i),1);
         }
         else{ // else, update the number of occurrence by current value + 1
            characterMap.put(rackWord.charAt(i), oldValue+1);
         }
      }
   }

   /**
    * get all the possible anagrams constructed by all the letters in the character map and their subsets,
    * and get the corresponding score for each word, and store them into the rack map
    * @param anagramDict the initialized anagram dictionary object
    * @return the number of words stored in the rack map
    */
   public int constructRackMap(AnagramDictionary anagramDict){
      ScoreTable scoreTable = new ScoreTable(); // create a score table object
      // local variables created for allSubsets method
      StringBuilder unique = new StringBuilder();
      int[] mult = new int[characterMap.size()];

      // initialize the "word" with unique letter using the letters in the character map
      for (char c : characterMap.keySet()){
         unique.append(c);
      }
      // initialize the array with the number of occurrence for each unique letter
      for(int i = 0; i < unique.length(); i++){
         mult[i] = characterMap.get(unique.charAt(i));
      }

      // get all subsets using the allSubsets method, with the two variables set above, k = 0 for recursion
      ArrayList<String> allSubsets = allSubsets(unique.toString(),mult,0);

      // for every subset of letters in the subsets, get the anagrams of it
      for (String subset : allSubsets){
         ArrayList<String> anagramList = anagramDict.getAnagramsOf(subset);
         // if the return value is not null, then there are valid words for this subset of letters
         // we then get all the anagrams for this subset of letters and then get the score of each anagram
         // using the score table, and store them into the rack map
         if(anagramList != null){
            for (String anagram : anagramList){
               rackMap.put(anagram, scoreTable.getWordScore(anagram));
            }
         }
      }
      return rackMap.size(); // return the number of the words stored in the rack map
   }

   /**
    * print the items rack map in the decreasing order of the values, if there are same values, then sort the items
    * alphabetically
    * @param out print stream object
    */
   public void printSorted(PrintStream out){
      // we can't directly sort the hash map, so we extract the items of the rack map into an array list, then we
      // can sort the array list by our method
      ArrayList<Map.Entry<String, Integer>> rackArrayList = new ArrayList<>(rackMap.entrySet());
      rackArrayList.sort(new rackComparator()); // sort the array list using our own comparator interface

      // print out each item
      for (Map.Entry<String, Integer> stringIntegerEntry : rackArrayList) {
         out.println(stringIntegerEntry.getValue() + ": " + stringIntegerEntry.getKey());
      }
   }

   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }
}

/**
 * to sort the array list by our own method, we have to implement the comparator interface, so construct a new rack
 * comparator class that implements the comparator interface and override the compare method
 */
class rackComparator implements Comparator<Map.Entry<String, Integer>>{

   /**
    * compare two items of the rack map by the decreasing order of the value, and when they share the same value,
    * sort them alphabetically
    */
   @Override
   public int compare(Map.Entry<String, Integer> item1, Map.Entry<String, Integer> item2) {
      // get the values of these two items
      int value1 = item1.getValue();
      int value2 = item2.getValue();
      int keyDiff = item1.getKey().compareTo(item2.getKey()); // needed when the two items have the same value
      // if an item has greater value, then put it in front the other
      if (value1 > value2){
         return -1;
      }
      else if(value1 < value2){
         return 1;
      }
      else {
         return keyDiff; // when they have the same values, then their orders are decided by their key
         // in alphabetic order
      }
   }
}
