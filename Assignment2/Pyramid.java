/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		/*
		 * Y-coordinate of layer in base is the height of canvas
		 * minus height of one brick.
		 */
		double baseLayerY = getHeight() - BRICK_HEIGHT;
		
		for(int i=BRICKS_IN_BASE; i>0; i--) {
			double layerY = baseLayerY - (BRICKS_IN_BASE-i)*BRICK_HEIGHT;
			drawOneLayerBrick(i, layerY);
		}
	}
	/*
	 * Draw one layer of brick
	 * @param brickNumber Number of bricks on each layer.
	 * @param layerY Y-coordinate for each layer.
	 */
	private void drawOneLayerBrick(double brickNumber, double layerY) {
		double firstBrickX = (getWidth() - BRICK_WIDTH * brickNumber)/2;
		
		/*
		 * Bricks on each layer has the same y-coordinate,
		 * which is the y-coordinate of each layer.
		 * Use for loop to generate x-coordinate of each brick in every layer.
		 */
		for(int i=0; i<brickNumber; i++) {
			double brickX = firstBrickX + i*BRICK_WIDTH;
			GRect brickRect = new GRect(brickX, layerY, BRICK_WIDTH, BRICK_HEIGHT);
			add(brickRect);
		}
	}
}

