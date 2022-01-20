// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CS 455 PA1
// Fall 2020

import java.util.Random;

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator {

    private int twoHeads;
    private int headTails;
    private int twoTails;
    public static final int TWO_SIDES = 2; // number of sides of a coin

    // use the number 0 to represent the head of a coin and the number 1 for a tail
    // if the result is two heads in a toss of two coins, then we have two 0 to represent the result, adding up to 0
    public static final int TWO_HEADS = 0;
    // if we get a head and a tail in a toss of two coins, then we have an 1 and a 0 to represent the result,
    // adding up to 1
    public static final int HEAD_TAILS = 1;
    // if we get two tails in a toss of two coins, then we have two 1 to represent the result, adding up to 2
    public static final int TWO_TAILS = 2;

   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
      twoHeads = 0;
      headTails = 0;
      twoTails = 0;
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      int firstCoin;
      int secondCoin;
      Random generator = new Random(); // create and initial a Random object
      for (int i = 0; i < numTrials; i++) {
         firstCoin = generator.nextInt(TWO_SIDES); // generate a random integer from 0 and 1
         secondCoin = generator.nextInt(TWO_SIDES);
         if ((firstCoin + secondCoin) == TWO_HEADS) {
            twoHeads += 1;
         } else if ((firstCoin + secondCoin) == HEAD_TAILS) {
            headTails += 1;
         } else if ((firstCoin + secondCoin) == TWO_TAILS) {
            twoTails += 1;
         }
      }

   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return getTwoHeads() + getTwoTails() + getHeadTails();
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return twoHeads;
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return twoTails;
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return headTails;
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
      twoHeads = 0;
      twoTails = 0;
      headTails = 0;
   }

}
