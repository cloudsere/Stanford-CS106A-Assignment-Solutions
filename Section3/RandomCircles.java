/*
 * File: RandomCircles.java
 * ------------------------
 * This program draws a set of 10 circles with different sizes,
 * positions, and colors.  Each circle has a randomly chosen
 * color, a randomly chosen radius between 5 and 50 pixels,
 * and a randomly chosen position on the canvas, subject to
 * the condition that the entire circle must fit inside the
 * canvas without extending past the edge.
 */

import acm.program.*;

import java.awt.Color;

import acm.graphics.*;
import acm.util.*;

public class RandomCircles extends GraphicsProgram {

/** Number of circles */
	private static final int NCIRCLES = 10;

/** Minimum radius */
	private static final double MIN_RADIUS = 5;

/** Maximum radius */
	private static final double MAX_RADIUS = 50;

	public void run() {
		for(int i = 0; i < NCIRCLES; i++) {
			drawCircle();
		}
	}
	
	private void drawCircle() {
		double r = rgen.nextDouble(MIN_RADIUS, MAX_RADIUS);
		double x = rgen.nextDouble(0, getWidth() - 2 * r);
		double y = rgen.nextDouble(0, getHeight() - 2 * r);
		GOval circle = new GOval(x, y, 2 * r, 2 * r);
		
		/* Generate random color for each circle */
		int colorR = rgen.nextInt(0, 255);
		int colorG = rgen.nextInt(0, 255);
		int colorB = rgen.nextInt(0, 255);
		Color color = new Color(colorR, colorG, colorB);
		
		circle.setFilled(true);
		circle.setColor(color);
		add(circle);
	}
			
/* Private instance variable */
	private RandomGenerator rgen = RandomGenerator.getInstance();

}
