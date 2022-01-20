import java.util.Random;//import all class in java.util.Random package
/**
 * class CoinTossSimulatorTester
 *
 * This calss is a testing class, which can thoroughly test the CoinTossSimulator class.
 * It contains main method in the compile command
 *
 *
 */
public class CoinTossSimulatorTester2 {

    /**
     Show the cumulative results of a random simulation of repeatedly tossing two coins
     We also will testing the invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()

     @param expNumber  the expected total number of trials
     @param numOfTrials  the actual total number of trials
     @param twoHeads  the actual total number of the tossing of two heads
     @param oneHeadOneTail  the actual total number of the tossing of one head and one tail
     @param twoTails  the actual total number of the tossing of two tails
     */
    private static void testResults(int expNumber, int numOfTrials, int twoHeads, int oneHeadOneTail, int twoTails){
        String answer;
        if ((expNumber == numOfTrials) && (numOfTrials == twoHeads + oneHeadOneTail + twoTails)) {
            answer = "true";
        }
        else {
            answer = "false";
        }
        System.out.println("Number of trails [exp:" + expNumber + "]: " + numOfTrials);
        System.out.println("Two-head tosses: " + twoHeads);
        System.out.println("Two-tail tosses: " + oneHeadOneTail);
        System.out.println("One-head one-tail tosses: " + twoTails);
        System.out.println("Tosses add up correctly?" + " " + answer);
        System.out.println();
    }

    public static void main(String[] args){
        //set a initial value of the expected total number of tails to be 0
        int expNumber = 0;
        int testing;
        // Creat a object of CoinTossSimulator class
        CoinTossSimulator coinToss = new CoinTossSimulator();
        // Generates a random number (0 or 1)
        Random numOfGenerator = new Random();

        //Show the actual result after constructor and before random tossing
        System.out.println("After constructor:");
        testResults(expNumber,coinToss.getNumTrials(),coinToss.getTwoHeads(),coinToss.getHeadTails(),coinToss.getTwoTails());

        //add the 1st simulation of random tossing
        testing = 1;

        //Cumulate the total total number of tails of tossing
        expNumber += testing;
        coinToss.run(testing);
        System.out.println("After run(" + testing +"):");
        testResults(expNumber,coinToss.getNumTrials(),coinToss.getTwoHeads(),coinToss.getHeadTails(),coinToss.getTwoTails());

        //add the 2nd simulation of random tossing
        testing = 10;

        //Cumulate the total total number of tails of tossing
        expNumber += testing;
        coinToss.run(testing);
        System.out.println("After run(" + testing +"):");
        testResults(expNumber,coinToss.getNumTrials(),coinToss.getTwoHeads(),coinToss.getHeadTails(),coinToss.getTwoTails());


        //add the 3rd simulation of random tossing
        testing = 2000;

        //Cumulate the total total number of tails of tossing
        expNumber += testing;
        coinToss.run(testing);
        System.out.println("After run(" + testing +"):");
        testResults(expNumber,coinToss.getNumTrials(),coinToss.getTwoHeads(),coinToss.getHeadTails(),coinToss.getTwoTails());

        //reset
        coinToss.reset();

        //reset the total total number of tails of tossing to be 0
        expNumber = 0;
        System.out.println("After reset:");
        testResults(expNumber,coinToss.getNumTrials(),coinToss.getTwoHeads(),coinToss.getHeadTails(),coinToss.getTwoTails());

        //add the 1st simulation of random tossing again
        testing = 100;

        //Cumulate the total total number of tails of tossing
        expNumber += testing;
        coinToss.run(testing);
        System.out.println("After run(" + testing +"):");
        testResults(expNumber,coinToss.getNumTrials(),coinToss.getTwoHeads(),coinToss.getHeadTails(),coinToss.getTwoTails());

    }
}



