/*
 * File: RobotFace.java
 * --------------------
 * This program draws a robot face using GRects and GOvals, centered
 * in the graphics window.  We make sure to define constants at the
 * top of our program instead of using magic numbers. We also write
 * the program in terms of reusable and general methods
 * drawRectangle and drawCircle.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class RobotFace extends GraphicsProgram {
	
	/** Constants */
	private static final int HEAD_WIDTH = 150;
	private static final int HEAD_HEIGHT = 300;
	private static final int EYE_RADIUS = 20;
	private static final int MOUTH_WIDTH = 120;
	private static final int MOUTH_HEIGHT = 40;
	
	public void run() {
		double headX = (getWidth() - HEAD_WIDTH) / 2;
		double headY = (getHeight() - HEAD_HEIGHT) / 2;
		drawHead(headX, headY);
		drawEyes(headX, headY);
		drawMouth(headY);
	}
	
	private void drawHead(double headX, double headY) {
		GRect head = new GRect(headX, headY, HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setColor(Color.GRAY);
		add(head);
	}
	
	private void drawEyes(double headX, double headY) {
		double leftEyeX = headX + HEAD_WIDTH / 4 - EYE_RADIUS;
		double rightEyeX = leftEyeX + HEAD_WIDTH / 2;
		double eyeY = headY + HEAD_HEIGHT / 4 - EYE_RADIUS;
		drawCircle(leftEyeX, eyeY);
		drawCircle(rightEyeX, eyeY);
	}
	
	private void drawCircle(double x, double y) {
		GOval circle = new GOval(x, y, 2*EYE_RADIUS, 2*EYE_RADIUS);
		circle.setFilled(true);
		circle.setColor(Color.YELLOW);
		add(circle);
	}
	
	private void drawMouth(double headY) {
		double x = (getWidth() - MOUTH_WIDTH) / 2;
		double y = headY + 3 * HEAD_HEIGHT / 4 - MOUTH_HEIGHT / 2;
		GRect mouth = new GRect(x, y, MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setColor(Color.WHITE);
		add(mouth);
	}

}