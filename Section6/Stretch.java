/* File: Stretch.java
 * ------------------------
 * A sample program that uses the stretch method
 * to display a normal image and the same image
 * stretched 2x and 3x.
 */
import acm.program.*;
import acm.graphics.*;

public class Stretch extends GraphicsProgram {
	public void run() {
		GImage image = new GImage("karel.png");
		GImage stretched2x = stretch(image, 2);
		GImage stretched3x = stretch(image, 3);
		add(image);
		add(stretched2x, 0, image.getY() + image.getHeight());
		add(stretched3x, 0, stretched2x.getY() + stretched2x.getHeight());
	}
	
	private GImage stretch(GImage image, int factor) {
		int[][] array = image.getPixelArray();
		int row = array.length;
		int column = array[0].length;
		
		int[][] output = new int[row][column * factor];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				for(int m = 0; m < factor; m++) {
					output[i][j * factor + m] = array[i][j];
				}
			}
		}
		return new GImage(output);
	}
}
