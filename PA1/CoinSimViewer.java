import javax.swing.JFrame;
import java.util.Scanner;

/**
 * A simulator viewer.
 * This is the entrance of this whole program.
 * With error checking function, clients input the number of trials in the console for the simulator,
 * and can view the simulating results representing in histogram.
 */
public class CoinSimViewer {

    // define the initial width and height of the window
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 500;

    /**
     * main function
     */
    public static void main(String[] args) {

        boolean flag = true; // uses in the error checking process
        int runTimes = 0; // number of simulate
        int twoHeads = 0; // the number of times to get two heads
        int twoTails = 0; // the number of times to get two tails
        int headTales = 0; // the number of times to get a head and a tail
        int trials = 0; // the number of trials

        CoinTossSimulator simulator = new CoinTossSimulator();
        Scanner in = new Scanner(System.in);

        // error checking to prevent negative values
        while (flag) {
            System.out.print("Enter num of trials:");
            runTimes = in.nextInt(); // read input from console
            if (runTimes <= 0) {
                System.out.println("ERROR: Number entered must be greater than 0.");
            } else {
                flag = false;
            }
        }

        simulator.run(runTimes); // calls run function with parameter runTimes
        //get the results of simulating
        twoHeads = simulator.getTwoHeads();
        headTales = simulator.getHeadTails();
        twoTails = simulator.getTwoTails();
        trials = simulator.getNumTrials();

        JFrame frame = new JFrame(); // create and initialize an object of JFrame
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); //set the size of the frame
        frame.setTitle("CoinSim"); // set the title of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the default close operation

        // create and initialize an object of CoinSimComponent and deliver results of simulating
        // to class CoinSimComponent
        CoinSimComponent component = new CoinSimComponent(twoHeads, headTales, twoTails, trials);

        frame.add(component); // add the created component onto this frame
        frame.setVisible(true); // set frame visible
    }

}
