/*
 * File: DrawCenteredRect.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the DrawCenteredRect problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class DrawCenteredRect extends GraphicsProgram {
	
	/** Size of the centered rect */
	private static final int WIDTH = 350;
	private static final int HEIGHT = 270;

	public void run() {
		/*
		 * Coordinates of a shape is the coordinate of its top left corner.
		 * Use this rule to calculate the x and y coordinate of the rect.
		 * Coordinates are double.
		 */
		double x = (getWidth() - WIDTH)/2;
		double y = (getHeight() - HEIGHT)/2;
		GRect rect = new GRect(x, y, WIDTH, HEIGHT);
		rect.setFilled(true);
		rect.setColor(Color.BLUE);
		add(rect);
	}

}

