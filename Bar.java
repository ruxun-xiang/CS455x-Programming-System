// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CS 455 PA1
// Fall 2020

// we included the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {

   private int bBottom;
   private int bLeft;
   private int bWidth;
   private int bHeight;
   private double bScale;
   private Color bColor;
   private String bLabel;

   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter scale). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar (in pixels)
      @param barHeight  height of the bar in application units
      @param scale  how many pixels per application unit
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
   public Bar(int bottom, int left, int width, int barHeight,
              double scale, Color color, String label) {

      bBottom = bottom;
      bLeft = left;
      bWidth = width;
      bHeight = barHeight;
      bScale = scale;
      bColor = color;
      bLabel = label;
   }
   
   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {

      // define the label color
      final Color LABEL_COLOR = Color.BLACK;

      // get the width and height of a label
      Font font = g2.getFont();
      FontRenderContext context = g2.getFontRenderContext();
      Rectangle2D labelBounds = font.getStringBounds(bLabel, context);
      int widthOfLabel = (int) labelBounds.getWidth();
      int heightOfLabel = (int) labelBounds.getHeight();

      int height =(int)(bHeight * bScale); // define the height of the bar by application units

      // if the toss number equals 0, then the height of this corresponding bar should becomes 0,
      // but actually it does shows in a little bit height, so I added the conditional statement "if" to deal with it
      // if the height equals to 0, then we do not need to draw the bar
      if (height != 0)
      {
         // if the height of the label doesn't equal to 0, draw the bar
         Rectangle aBar = new Rectangle(bLeft, bBottom -  heightOfLabel - height, bWidth, height);
         g2.setColor(bColor);
         g2.fill(aBar);
         g2.draw(aBar);
      }

      // draw the label under the corresponding bar even if the height is 0, and make sure it is center aligned
      g2.setColor(LABEL_COLOR);
      g2.drawString(bLabel, bLeft - (widthOfLabel - bWidth) / 2, bBottom);

   }
}
