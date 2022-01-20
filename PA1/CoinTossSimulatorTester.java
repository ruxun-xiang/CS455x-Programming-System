/**
 * This class test the CoinTossSimulator class, including a test of the class constructor, run function with different
 * parameters and reset function.
 */
public class CoinTossSimulatorTester {

    public static void main(String[] args) {
        int twoHeads = 0;
        int twoTails = 0;
        int headTails = 0;
        int numTrials = 0;

        // test the constructor and initialization of the instance values
        CoinTossSimulator simulator = new CoinTossSimulator();
        twoHeads = simulator.getTwoHeads();
        twoTails = simulator.getTwoTails();
        headTails = simulator.getHeadTails();
        numTrials = simulator.getNumTrials();
        System.out.println("After constructor:");
        System.out.println("Number of trials [exp:0]: "+ numTrials);
        System.out.println("Two-head tosses:" + twoHeads);
        System.out.println("Two-tail tosses:" + twoTails);
        System.out.println("One-head one-tail tosses:" + headTails);
        System.out.print("Tosses add up correctly? ");
        System.out.println(twoHeads + twoTails + headTails == 0);
        System.out.println();

        // test run function with parameter 1
        simulator.run(1);
        twoHeads = simulator.getTwoHeads();
        twoTails = simulator.getTwoTails();
        headTails = simulator.getHeadTails();
        numTrials = simulator.getNumTrials();
        System.out.println("After run(1)");
        System.out.println("Number of trials [exp:1]: "+ numTrials);
        System.out.println("Two-head tosses:" + twoHeads);
        System.out.println("Two-tail tosses:" + twoTails);
        System.out.println("One-head one-tail tosses:" + headTails);
        System.out.print("Tosses add up correctly? ");
        System.out.println(twoHeads + twoTails + headTails == 1);
        System.out.println();

        // test run function with parameter 10
        simulator.run(10);
        twoHeads = simulator.getTwoHeads();
        twoTails = simulator.getTwoTails();
        headTails = simulator.getHeadTails();
        numTrials = simulator.getNumTrials();
        System.out.println("After run(10):");
        System.out.println("Number of trials [exp:11]: "+ numTrials);
        System.out.println("Two-head tosses:" + twoHeads);
        System.out.println("Two-tail tosses:" + twoTails);
        System.out.println("One-head one-tail tosses:" + headTails);
        System.out.print("Tosses add up correctly? ");
        System.out.println(twoHeads + twoTails + headTails == 11);
        System.out.println();

        // test run function with parameter 100
        simulator.run(100);
        twoHeads = simulator.getTwoHeads();
        twoTails = simulator.getTwoTails();
        headTails = simulator.getHeadTails();
        numTrials = simulator.getNumTrials();
        System.out.println("After run(100):");
        System.out.println("Number of trials [exp:111]: "+ numTrials);
        System.out.println("Two-head tosses:" + twoHeads);
        System.out.println("Two-tail tosses:" + twoTails);
        System.out.println("One-head one-tail tosses:" + headTails);
        System.out.print("Tosses add up correctly? ");
        System.out.println(twoHeads + twoTails + headTails == 111);
        System.out.println();

        // test reset function
        simulator.reset();
        System.out.println("After reset:");
        twoHeads = simulator.getTwoHeads();
        twoTails = simulator.getTwoTails();
        headTails = simulator.getHeadTails();
        numTrials = simulator.getNumTrials();
        System.out.println("Number of trials [exp:0]: "+ numTrials);
        System.out.println("Two-head tosses:" + twoHeads);
        System.out.println("Two-tail tosses:" + twoTails);
        System.out.println("One-head one-tail tosses:" + headTails);
        System.out.print("Tosses add up correctly? ");
        System.out.println(twoHeads + twoTails + headTails == 0);
        System.out.println();

        // test run function with parameter 1000
        simulator.run(1000);
        twoHeads = simulator.getTwoHeads();
        twoTails = simulator.getTwoTails();
        headTails = simulator.getHeadTails();
        numTrials = simulator.getNumTrials();
        System.out.println("After run(1000):");
        System.out.println("Number of trials [exp:1000]: "+ numTrials);
        System.out.println("Two-head tosses:" + twoHeads);
        System.out.println("Two-tail tosses:" + twoTails);
        System.out.println("One-head one-tail tosses:" + headTails);
        System.out.print("Tosses add up correctly? ");
        System.out.println(twoHeads + twoTails + headTails == 1000);
        System.out.println();
    }
}
