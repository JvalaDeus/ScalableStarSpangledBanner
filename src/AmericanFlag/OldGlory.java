package AmericanFlag;

import java.awt.*;
import javax.swing.JFrame;

/**
 * I got help creating the star from this website:
 * http://johnbhall.com/2012/03/normal-illustrator-star-american-flag/
 **/
public class OldGlory extends JFrame {
	// list of all the variables
	private static final long serialVersionUID = 1L;
	int initialWidth = 295;
	int initialLength = 570;
	// width & length of the flag
	double flagWidth = 1.0;
	double flagLength = 1.9;
	// Offset
	static int xOffset = 8;
	static int yOffset = 30;
	// width and length of union
	double UnionWidth = .5385;
	double UnionLength = 0.76;
	int unionYoffset = -5;
	int unionXoffset = 2;
	int specialUnionXOffset = -3;
	// Variables directly from the website
	double E = 0.054;
	double F = 0.054;
	double G = 0.063;
	double H = 0.063;
	double k = 0.0616;
	// X & Y coordinates for each point of the first star
	double[] xCoordinates = { 0, -.223, -.95, -.361, -.587, 0, .587, .361, .949, .223 };
	double[] yCoordinates = { -1, -.307, -.309, .117, .808, .38, .809, .117, -.309, -.307 };
	int[] starX = new int[10];
	int[] starY = new int[10];
	int starPoints;
	

	// main and also creates object
	public static void main(String[] args) {
		OldGlory starSpangledBanner = new OldGlory();
		starSpangledBanner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		starSpangledBanner.setVisible(true);
	}

	// sets the size of the star spangled banner
	public OldGlory() {
		setSize(initialLength, initialWidth);
		repaint();
	}

	public void paint(Graphics g) {
		// creates the window and its ablitiy to change size and scale with it.
		g.drawRect(0, 0, getWidth(), getHeight());

		int width = getHeight() - yOffset;
		int length = getWidth() - xOffset;
		int scale = minimum(length, width);
		/**
		 * creating the stripes with a for loop. It determines wich stripes will
		 * be red and which will be white.
		 */
		for (int i = 0; i < 13; i++) {
			if (i % 2 == 0) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.WHITE);
			}
			g.fillRect(xOffset, Math.round(i * (width / 13) + yOffset), length, width / 13);
		}
		int unionLength = (int) Math.round(UnionLength * width);
		int unionWidth = (int) Math.round(UnionWidth * width);

		// creates the union
		g.setColor(Color.BLUE);
		g.fillRect(xOffset, yOffset, unionLength, unionWidth);

		// from here on, creates the stars
		//this creates the rows of the stars
		for (int row = 0; row < 9; row++) {
			//I made a if else statement which divides the row number by two to determine if the row is odd or even.
			//The even row then creates 6 stars, while the odd row will create 5 stars
			if (row % 2 == 0) {
				for (int nStars = 0; nStars <= 5; nStars++) {
					//This creates the stars with a for loop.
					//It takes the points in the xCoordinates array and does math to the x & y coordinates to determine the new position of each coordinate
					for (starPoints = 0; starPoints < 10; starPoints++) {
						starX[starPoints] = (int) (unionXoffset + ( nStars * (unionLength /6) + (scale / 1.5) * (G + H + xCoordinates[starPoints] * (k / 2))));
						starY[starPoints] = (int) (unionYoffset + yOffset + row * (unionWidth/9) + (scale / 1.5) * (E + F + yCoordinates[starPoints] * (k / 2)));
					}	
					//draws the star and fills it while
					g.setColor(Color.WHITE);
					g.fillPolygon(starX, starY, 10);
					}
			} else {
				for (int nStars = 1; nStars <= 5; nStars++) {
					//same as the for loop above
					for (starPoints = 0; starPoints < 10; starPoints++) {
						starX[starPoints] = (int) (specialUnionXOffset + (nStars * (unionLength/6) + (scale / 1.5) * (G + xCoordinates[starPoints] * (k / 2))));
						starY[starPoints] = (int) (unionYoffset + yOffset + row * (unionWidth/9) + (scale / 1.5) * (E + F + yCoordinates[starPoints] * (k / 2)));
					}
					g.setColor(Color.WHITE);
					g.fillPolygon(starX, starY, 10);
				}
			}
		g.setColor(Color.WHITE);
		g.fillPolygon(starX, starY, 10);
		}
	}
//this is the minimum that helps with the scaling to prevent the window from cutting parts of the flag off when you scale it
	public int minimum(int a, int b) {
		if (a < b) {
			return a;
		} else {
			return b;
		}
	}
}