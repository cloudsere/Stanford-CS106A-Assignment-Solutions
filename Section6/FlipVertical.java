/* File: FlipVertical.java
 * ---------------------
 * A sample program that uses the flipVertical method
 * to display a normal image and the same image
 * flipped vertically.
 */
import acm.program.*;
import acm.graphics.*;

public class FlipVertical extends GraphicsProgram {
	public void run() {
		GImage image = new GImage("milkmaid.png");
		GImage flipped = flipVertical(image);
		add(image);
		add(flipped, image.getWidth(), 0);
	}

	private GImage flipVertical(GImage img) {
		int[][] array = img.getPixelArray();
		int row = array.length;
		int column = array[0].length;
		int[][] output = new int[row][column];
		
		int lh = 0;
		int rh = row-1;
		while(lh <= rh) {
			for(int i = 0; i < column; i++) {
				output[lh][i] = array[rh][i];
				output[rh][i] = array[lh][i];
			}
			lh++;
			rh--;
		}
		return new GImage(output);
	}
}
