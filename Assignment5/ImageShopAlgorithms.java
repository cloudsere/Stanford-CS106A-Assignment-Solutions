/* 
 * Note: these methods are public in order for them to be used by other files
 * in this assignment; DO NOT change them to private.  You may add additional
 * private methods to implement required functionality if you would like.
 * 
 * You should remove the stub lines from each method and replace them with your
 * implementation that returns an updated image.
 */

// TODO: comment this file explaining its behavior

import java.util.*;
import acm.graphics.*;

public class ImageShopAlgorithms implements ImageShopAlgorithmsInterface {

	public GImage flipHorizontal(GImage source) {
		int [][] array = source.getPixelArray();
		int row = array.length;
		int column = array[0].length;
		int [][] newArray = new int[row][column];
		for(int i=0; i<row; i++) {
			for(int j=0; j<column; j++) {
				newArray[i][column-j-1] = array[i][j];
			}
		}
		return new GImage(newArray);
	}

	public GImage rotateLeft(GImage source) {
		int [][] array = source.getPixelArray();
		int row = array.length;
		int column = array[0].length;
		int [][] newArray = new int[column][row];
		for(int i = 0; i<row; i++) {
			for(int j = 0; j<column; j++) {
				int pixel = array[i][j];
				newArray[column-1-j][i] = pixel;
			}
		}
		return new GImage(newArray);
	}

//	public GImage rotateRight(GImage source) {
//		for(int i = 0; i<3; i++) {
//			source = rotateLeft(source);
//		}
//		return source;
//	}
	
	public GImage rotateRight(GImage source) {
		int [][] array = source.getPixelArray();
		int row = array.length;
		int column = array[0].length;
		int [][] newArray = new int[column][row];
		for(int i = 0; i<row; i++) {
			for(int j = 0; j<column; j++) {
				int pixel = array[i][j];
				newArray[j][row-1-i] = pixel;
			}
		}
		return new GImage(newArray);
	}

	public GImage greenScreen(GImage source) {
		int [][] array = source.getPixelArray();
		int row = array.length;
		int column = array[0].length;
		for(int i = 0; i<row; i++) {
			for(int j = 0; j<column; j++) {
				int pixel = array[i][j];
				int r = GImage.getRed(pixel);
				int g = GImage.getGreen(pixel);
				int b = GImage.getBlue(pixel);
				if(g >= 2* Math.max(r, b)) {
					array[i][j] = GImage.createRGBPixel(r, g, b, 0);
				}
			}
		}
		return new GImage(array);
	}

	public GImage equalize(GImage source) {
		int[][] array = source.getPixelArray();
		int[] lumHistogram = computeLumHistogram(array);
		int[] cumuHistogram = computeCumuHistogram(lumHistogram);
		int[][] newArray = modifyPixel(array, cumuHistogram);
		return new GImage(newArray);
	}
	
	private int[] computeLumHistogram(int[][] array) {
		int[] output = new int[256];
		int row = array.length;
		int column = array[0].length;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				int pixel = array[i][j];
				int red = GImage.getRed(pixel);
				int green = GImage.getGreen(pixel);
				int blue = GImage.getBlue(pixel);
				int luminosity = computeLuminosity(red, green, blue);
				output[luminosity]++;
			}
		}
		return output;
	}
	
	private int[] computeCumuHistogram(int[] array) {
		int[] output = new int[256];
		output[0] = array[0];
		for(int i = 1; i < 256; i++) {
			output[i] = array[i] + output[i-1];
		}
		return output;
	}
	
	private int[][] modifyPixel(int[][] array, int[] cumuHistogram) {
		int row = array.length;
		int column = array[0].length;
		int totalPixels = row * column;
		
		int[][] output = new int[row][column];
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				int pixel = array[i][j];
				int red = GImage.getRed(pixel);
				int green = GImage.getGreen(pixel);
				int blue = GImage.getBlue(pixel);
				int luminosity = computeLuminosity(red, green, blue);
				
				int pixelLuminosity = cumuHistogram[luminosity];
				
				// It's important to add a double cast, because int/int will always get an integer,
				// which is always 0 in this case.
				double percentage = (double) pixelLuminosity / totalPixels;
				
				int RGBValue = (int) Math.floor(percentage * 255);
				int newPixel = GImage.createRGBPixel(RGBValue, RGBValue, RGBValue);
				output[i][j] = newPixel;
			}
		}
		
		return output;
	}

	public GImage negative(GImage source) {
		int [][] array = source.getPixelArray();
		int row = array.length;
		int column = array[0].length;
		for(int i = 0; i<row; i++) {
			for(int j = 0; j<column; j++) {
				int pixel = array[i][j];
				int r = GImage.getRed(pixel);
				int g = GImage.getGreen(pixel);
				int b = GImage.getBlue(pixel);
				array[i][j] = GImage.createRGBPixel(255-r, 255-g, 255-b);
			}
		}
		return new GImage(array);
	}

	public GImage translate(GImage source, int dx, int dy) {
		int[][] array = source.getPixelArray();
		int row = array.length;
		int column = array[0].length;
		int[][] newArray = new int[row][column];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				int pixel = array[i][j];
				
				int xCoordinate;
				if(i + dy >= 0) {
					xCoordinate = (i + dy) % row;
				}else {
					xCoordinate = (column - 1) + (i + dy) % row;
				}
				
				int yCoordinate = 0;
				if(j + dx >= 0) {
					yCoordinate = (j + dx) % column;
				}else {
					yCoordinate = (row - 1) + (j + dx) % column;
				}
				
				newArray[xCoordinate][yCoordinate] = pixel;
			}
		}
		return new GImage(newArray);
	}

	public GImage blur(GImage source) {
		int[][] array = source.getPixelArray();
		int row = array.length;
		int column = array[0].length;
		int[][] newArray = new int[row][column];
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				// for each pixel in the origin array
				int red = 0;
				int green = 0;
				int blue = 0;
				int pixelNumber = 0;
				
				// loop through all the neighbors of current pixel and itself
				// n stands for column and m stands for row
				for(int n = j-1; n <= j+1; n++) {
					for(int m = i-1; m <= i+1; m++) {
						if(0<=m && m<row && 0<=n && n<column) {
							int pixel = array[m][n];
							red += GImage.getRed(pixel);
							green += GImage.getGreen(pixel);
							blue += GImage.getBlue(pixel);
							pixelNumber++;
						}
					}
				}
				// get the average r,g,b values after loop
				red = red / pixelNumber;
				green = green / pixelNumber;
				blue = blue / pixelNumber;
				
				// generate a new pixel for the new array
				newArray[i][j] = GImage.createRGBPixel(red, green, blue);
			}
		}
		return new GImage(newArray);
	}
}
