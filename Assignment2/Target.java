/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		/*
		 * Draw outer circle first, then draw circle in the middle.
		 * Draw inner circle last.
		 */
		drawCircle(72, Color.RED);
		drawCircle(46.8, Color.WHITE);
		drawCircle(21.6, Color.RED);
	}
	
	private void drawCircle(double r, Color color) {
		/*
		 * Coordinate of oval is the coordinate of its top left corner.
		 */
		double x = (getWidth() - 2*r)/2;
		double y = (getHeight() - 2*r)/2;
		
		GOval circle = new GOval(x, y, 2*r, 2*r);
		circle.setFilled(true);
		circle.setColor(color);
		add(circle);
	}
}
