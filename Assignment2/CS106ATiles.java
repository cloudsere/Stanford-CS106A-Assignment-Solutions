/*
 * File: CS106ATiles.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the CS106ATiles problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class CS106ATiles extends GraphicsProgram {
	
	/** Amount of space (in pixels) between tiles */
	private static final int TILE_SPACE = 20;
	
	/** Width of each tile */
	private static final int TILE_WIDTH = 150;
	
	/** Height of each tile */
	private static final int TILE_HEIGHT = 100;
	
	/** Number of tiles in horizontal direction */
	private static final int HORIZONTAL_TILE_NUMBER = 2;
	
	/** Number of tiles in vertical direction */
	private static final int VERTICAL_TILE_NUMBER = 2;
	

	public void run() {
		/*
		 * Calculate the coordinate of the top left tile, shift dx and dy for other tiles.
		 */
		double firstX = generateFirstX();
		double firstY = generateFirstY();
		
		for(int i=0; i<VERTICAL_TILE_NUMBER; i++) {
			double tileY = firstY + i*(TILE_HEIGHT + TILE_SPACE);
			
			for(int j=0; j<HORIZONTAL_TILE_NUMBER; j++) {
				double tileX = firstX + j*(TILE_WIDTH + TILE_SPACE);
				drawTileRec(tileX, tileY);
				drawTileLabel(tileX, tileY);
			}
		}
	}
	
	/*
	 * Generate x-coordinate of the first tile(top left).
	 */
	private double generateFirstX() {
		double x = (getWidth() - HORIZONTAL_TILE_NUMBER*TILE_WIDTH - TILE_SPACE*(HORIZONTAL_TILE_NUMBER-1))/2;
		return x;
	}
	
	/*
	 * Generate y-coordinate of the first tile(top left).
	 */
	private double generateFirstY() {
		double y = (getHeight() - VERTICAL_TILE_NUMBER*TILE_HEIGHT - TILE_SPACE*(VERTICAL_TILE_NUMBER-1))/2;
		return y;
	}
	
	/*
	 * Draw rectangle of tile.
	 */
	private void drawTileRec(double recX, double recY) {
		GRect tile = new GRect(recX, recY, TILE_WIDTH, TILE_HEIGHT);
		tile.setFilled(false);
		tile.setColor(Color.BLACK);
		add(tile);
	}
	
	/*
	 * @param x X-coordinate of the outer rectangle of the label.
	 */
	private void drawTileLabel(double recX, double recY) {
		GLabel label = new GLabel("CS106A");
		double labelX = (TILE_WIDTH - label.getWidth())/2 + recX;
		double labelY = (TILE_HEIGHT + label.getAscent())/2 + recY;
		add(label,labelX, labelY);
	}
}

