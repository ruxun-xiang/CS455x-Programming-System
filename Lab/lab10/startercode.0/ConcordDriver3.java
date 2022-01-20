import java.util.Scanner;
import java.util.Map;

/**
 * Finds frequency of each word in a file.  
 * (Version for Exercise 3.)
 *
 * Optional command line argument is the threshold for the number of
 * letters a word must have to be printed with its number of occurrences.
 *
 * If argument isn't given, prints all words and number of occurrences.
 *
 */

public class ConcordDriver3 {


   public static void main(String[] args) {

      int threshold = 0;

      if (args.length > 0) {
         threshold = Integer.parseInt(args[0]);
      }
      System.out.println(threshold);
      Concord concord = new Concord();
		
      Scanner in = new Scanner(System.in);
		
      concord.addData(in);		
		

      // add code here to print out the selected entries

      Predicate testLarge = new LargeWordPred(threshold);

      concord.printSatisfying(System.out, testLarge);
		
   }

}


// add new class here
class LargeWordPred implements Predicate{
   private int threshold;

   public LargeWordPred(int threshold){
      this.threshold = threshold;
   }


   @Override
   public boolean predicate(Map.Entry<String, Integer> item) {
      return item.getKey().length() >= threshold;
   }
}