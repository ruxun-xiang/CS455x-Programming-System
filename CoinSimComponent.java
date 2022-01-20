import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;


/**
 This component draws three bar shapes.
 */
public class CoinSimComponent extends JComponent{
    private int twoHeads;
    private int headTales;
    private int twoTails;
    private int numTrials;

    /**
     * Receive the simulating results from CoinTossSimulator by creating this constructor.
     * @param twoHeads the number of times to get two heads.
     * @param headTails the number of times to get a head and a tail.
     * @param twoTails the number of times to get two tails.
     * @param numTrials the total number of trials.
     */
    public CoinSimComponent(int twoHeads, int headTails, int twoTails, int numTrials) {
        this.twoHeads = twoHeads;
        this.headTales = headTails;
        this.twoTails = twoTails;
        this.numTrials = numTrials;
    }

    /**
     * Define parameters that relate to the layout and shape of these three bars.
     * @param g the graphics context.
     */
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        final double PERCENT = 100.0;

        // express the toss results as percentage (units: %)
        int twoHeadsInPercent = (int)Math.round(twoHeads * PERCENT / (double)numTrials);
        int headTailsInPercent = (int)Math.round(headTales * PERCENT / (double)numTrials);
        int twoTailsInPercent = (int)Math.round(twoTails * PERCENT / (double)numTrials);

        // build label strings
        String labelTwoHeads = "Two Heads:" + twoHeads + "(" + twoHeadsInPercent + "%)";
        String labelHeadTails = "A Head and a Tale:" + headTales + "(" + headTailsInPercent + "%)";
        String labelTwoTails = "Two Tales:" + twoTails + "(" + twoTailsInPercent + "%)";

        // get the height of a label for the layout
        Font font = g2.getFont();
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D labelBounds = font.getStringBounds(labelTwoHeads, context);
        int heightOfLabel = (int) labelBounds.getHeight();

        // define constant values that relate to the shape and color of these three bars
        final int WINDOW_WIDTH = getWidth(); // width of the window
        final int WINDOW_HEIGHT = getHeight(); // height of the window
        final int X = WINDOW_WIDTH / 4;
        final int VERTICAL_BUFFER = 20;
        final int BOTTOM = WINDOW_HEIGHT - VERTICAL_BUFFER;
        final int BAR_WIDTH = 60;
        final Color TWO_HEADS_COLOR = Color.red;
        final Color HEAD_TAILS_COLOR = Color.green;
        final Color TWO_TAILS_COLOR = Color.blue;

        // define the height of the possible tallest bar that can occupy the maximum window size vertically
        final int TALLEST_BAR = WINDOW_HEIGHT - 2 * VERTICAL_BUFFER - heightOfLabel;
        final double SCALE = TALLEST_BAR / PERCENT; //define the application unit

        // construct three bars
        Bar twoHeadsBar = new Bar(BOTTOM, X - BAR_WIDTH / 2, BAR_WIDTH, twoHeadsInPercent, SCALE,
                TWO_HEADS_COLOR, labelTwoHeads);
        Bar headTailsBar = new Bar(BOTTOM, 2 * X - BAR_WIDTH / 2, BAR_WIDTH, headTailsInPercent, SCALE,
                HEAD_TAILS_COLOR, labelHeadTails);
        Bar twoTalesBar = new Bar(BOTTOM, 3 * X - BAR_WIDTH / 2, BAR_WIDTH, twoTailsInPercent, SCALE,
                TWO_TAILS_COLOR, labelTwoTails);
        twoHeadsBar.draw(g2);
        headTailsBar.draw(g2);
        twoTalesBar.draw(g2);
    }
}
