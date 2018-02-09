/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	// Dimensions of the canvas, in pixels
	// These should be used when setting up the initial size of the game,
	// but in later calculations you should use getWidth() and getHeight()
	// rather than these constants for accurate size information.
	public static final double CANVAS_WIDTH = 420;
	public static final double CANVAS_HEIGHT = 600;

	// Number of bricks in each row
	public static final int NBRICK_COLUMNS = 10;

	// Number of rows of bricks
	public static final int NBRICK_ROWS = 10;

	// Separation between neighboring bricks, in pixels
	public static final double BRICK_SEP = 8;

	// Width of each brick, in pixels
	public static final double BRICK_WIDTH = Math.floor(
			(CANVAS_WIDTH - (NBRICK_COLUMNS + 1.0) * BRICK_SEP) / NBRICK_COLUMNS);

	// Height of each brick, in pixels
	public static final double BRICK_HEIGHT = 8;
	
	// Width of start button, in pixels
	public static final double BUTTON_WIDTH = 200;
		
	// Height of start button, in pixels
	public static final double BUTTON_HEIGHT = 40;

	// Offset of the top brick row from the top, in pixels
	public static final double BRICK_Y_OFFSET = 70;

	// Dimensions of the paddle
	public static final double PADDLE_WIDTH = 60;
	public static final double PADDLE_HEIGHT = 8;

	// Offset of the paddle up from the bottom 
	public static final double PADDLE_Y_OFFSET = 30;

	// Radius of the ball in pixels
	public static final double BALL_RADIUS = 5;

	// The ball's vertical velocity.
	public static final double VELOCITY_Y = 3.0;

	// The ball's minimum and maximum horizontal velocity; the bounds of the
	// initial random velocity that you should choose (randomly +/-).
	public static final double VELOCITY_X_MIN = 1.0;
	public static final double VELOCITY_X_MAX = 3.0;

	// Animation delay or pause time between ball moves (ms)
	public static final double DELAY = 1000.0 / 60.0;

	// Number of turns 
	public static final int NTURNS = 3;
	
	// Define three colors in RGB numbers.
	public static final Color BLACK_COLOR = new Color(12, 35, 47);
	public static final Color BLUE_COLOR = new Color(154,205,205);
	public static final Color RED_COLOR = new Color(216, 0, 53);
	
	public void run() {
		// Set the window's title bar text
		setTitle("CS 106A Breakout");

		// Set the canvas size.  In your code, remember to ALWAYS use getWidth()
		// and getHeight() to get the screen dimensions, not these constants!
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);

		/* You fill this in, along with any subsidiary methods */
		setUpGame();
		playGame();
	}
	
	// Set up the UI interface for the game and listen for mouse event. 
	public void setUpGame() {
		drawStartButton();
		setUpBall();
		drawBricks();
		drawPaddle();
		
		addMouseListeners();
	}
	
	public void playGame() {
		waitForClick();
		// Show the ball after user clicks on the canvas.
		showBall();
		
		while(turnsLeft > 0) {
			// Remove the "Game start!" button or "You missed one!" button.
			removeButton();
			playGameOneTurn();
		}
	}
	
	public void playGameOneTurn() {
		turnsLeft--;
		while(gameStart) {
			moveBall();
			checkForCollision();
			pause(DELAY);
		}
		restartGame();
	}
	

	// Locate the ball to the center of the canvas, set its velocity to initial value.
	private void restartGame() {
		ball.setVisible(false);
		drawLostButton();
		ball.setLocation((getWidth() - 2 * BALL_RADIUS) / 2, (getHeight() - 2 * BALL_RADIUS) / 2);
		setUpBallInitialDirection();
		ball.setVisible(true);
		gameStart = true;
	}
	
	private void winGame() {
		gameStart = false;
		turnsLeft = 0;
		drawWinButton();
	}
	
	/* ------------------------ Set Up and Move the Paddle ------------------------ */
	private void drawPaddle() {
		double paddleX = (getWidth() - PADDLE_WIDTH)/2;
		paddle = new GRect(paddleX, CANVAS_HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		add(paddle);
	}
	
	// Move the paddle according to the coordinate of mouse event.
	public void mouseMoved(MouseEvent e) {
		double mouseX = e.getX();
		movePaddle(mouseX);
	}
	
	public void movePaddle(double mouseX) {
		double dx = mouseX - paddle.getX();
		// Paddle cannot move further than the leftmost corner or the rightmost corner.
		if(paddle.getX() + dx < 0) {
			dx = -paddle.getX();
		}
		
		if(paddle.getX() + dx > getWidth() - PADDLE_WIDTH ) {
			dx = getWidth() - PADDLE_WIDTH - paddle.getX(); 
		}
		paddle.move(dx, 0);
	}
	
	/* ------------------------ Set Up and Move the Ball ------------------------ */
	// Draw body of the ball, and set up its initial direction.
	private void setUpBall() {
		setUpBallOval();
		setUpBallInitialDirection();
	}
	
	// Draw oval body of the ball.
	private void setUpBallOval() {
		double x = (getWidth() - 2 * BALL_RADIUS) / 2;
		double y = (getHeight() - 2 * BALL_RADIUS) / 2;
		ball = new GOval(x, y, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		
		Color ballColor = new Color(216, 0, 53);
		ball.setColor(ballColor);
	}
	
	// Give the ball an initial direction.
	private void setUpBallInitialDirection() {
		RandomGenerator rgen = RandomGenerator.getInstance();
		vx = rgen.nextDouble(VELOCITY_X_MIN, VELOCITY_X_MAX);
		if(rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
	}
	
	private void showBall() {
		add(ball);
	}
	
	private void moveBall() {
		ball.move(vx, vy);
	}
	
	/* ------------------------ Check For Collision ------------------------ */
	private void checkForCollision() {
		checkForWallCollision();
		checkForPaddleCollision();
		checkForBrickCollision();
	}
	
	private void checkForWallCollision() {
		// Collision with floor and finishes current turn.
		if(ball.getY() >= getHeight() - BALL_RADIUS) {
			gameStart = false;
		}
		
		// Collision with left and right walls;
		if(ball.getX() <= 0 || ball.getX() + BALL_RADIUS >= getWidth()) {
			vx = -vx;
		}
		
		// Collision with ceiling.
		if(ball.getY() <= 0) {
			vy = -vy;
		}
	}
	
	private GObject getCollidingObject() {
		double x = ball.getX();
		double y = ball.getY();
		double r = BALL_RADIUS;
		GObject obj = getElementAt(x, y);
		if(obj == null) {
			obj = getElementAt(x, y + 2 * r);
		}
		if(obj == null) {
			obj = getElementAt(x + 2 * r, y);
		}
		if(obj == null) {
			obj = getElementAt(x + 2 * r, y + 2 *r);
		}
		return obj;
	}
	
	private void checkForPaddleCollision() {
		GObject obj = getCollidingObject();
		if(obj == paddle) {
			vy = -vy;
		}
	}
	
	private void checkForBrickCollision() {
		GObject obj = getCollidingObject();
		
		if(obj != null && obj != paddle ) {
			// The ball should go for opposite direction after it hits the brick.
				vy = -vy;
				remove(obj);
				
				// If one of the corner of the ball collides with a brick, remove the brick.
				// Numbers of bricks left in canvas minuses one.
				bricksLeft--;
				
				// If there is no brick left in canvas, the user wins.
				if(bricksLeft == 0) {
					winGame();
				}
			
		}
	}
	
	/* ------------------------ Draw and Remove Buttons ------------------------ */
	private void removeButton() {
		remove(infoButton);
		remove(infoLabel);
	}
	
	private void drawLostButton() {
		String label = turnsLeft > 0 ? "You Missed One!" : "You Lost!";
		drawButtonAndLabel(RED_COLOR, label);
		
		// Pause for half a second for user to see.
		pause(1000.0 / 2);
	}
	
	private void drawWinButton() {
		drawButtonAndLabel(RED_COLOR, "You Win!");	
	}
	
	// Draw a start button in the center of the canvas.
	// Giving the user information about how to start the game.
	private void drawStartButton() {
		drawButtonAndLabel(BLUE_COLOR, "Game Start!");
	}
	
	private void drawButtonAndLabel(Color buttonColor, String buttonText) {
		double x = (getWidth() - BUTTON_WIDTH) / 2;
		double y = (getHeight() - BUTTON_HEIGHT) / 2;
		infoButton = new GRoundRect(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
		infoButton.setFilled(true);
		infoButton.setFillColor(buttonColor);
		add(infoButton);
		
		infoLabel = new GLabel(buttonText);
		infoLabel.setFont("Helvetica-20");
		double labelX = x + (BUTTON_WIDTH - infoLabel.getWidth()) / 2;
		double labelY = y + (BUTTON_HEIGHT + infoLabel.getAscent()) / 2;
		infoLabel.setLocation(labelX, labelY);
		infoLabel.setColor(Color.WHITE);
		add(infoLabel);
	}
	
	/* ------------------------ Draw Bricks ------------------------ */
	public void drawBricks() {
		for(int i=0; i < NBRICK_ROWS; i++) {
			double rowY = BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP);
			drawOneRowBrick(rowY);
		}
	}
	
	public Color generateBrickColor() {
		RandomGenerator rgen = RandomGenerator.getInstance();
		double colorRgen = rgen.nextDouble(1.0, 4.0);
		Color c;
		if(colorRgen < 2.0) {
			c = BLACK_COLOR;
		}else if( colorRgen < 3.0) {
			c = RED_COLOR;
		}else {
			c = BLUE_COLOR;
		}
		return c;
	}
	
	private void drawOneRowBrick(double rowY) {
		double firstBrickX = (getWidth() - BRICK_WIDTH * NBRICK_COLUMNS -BRICK_SEP * (NBRICK_COLUMNS-1)) / 2;
		
		/*
		 * Bricks on each layer has the same y-coordinate,
		 * which is the y-coordinate of each layer.
		 * Use for loop to generate x-coordinate of each brick in every layer.
		 */
		for(int i=0; i < NBRICK_COLUMNS; i++) {
			double brickX = firstBrickX + i * (BRICK_WIDTH + BRICK_SEP);
			GRect brick = new GRect(brickX, rowY, BRICK_WIDTH, BRICK_HEIGHT);
			brick.setFilled(true);
			
			Color brickColor = generateBrickColor();
			brick.setColor(brickColor);
			add(brick);
		}
	}
	
	/* ------------------------ Instance Variables ------------------------ */
	
	private GRect paddle;
	private GOval ball;
	
	// X and Y velocities of the ball.
	// Create a button to show the start of the game to the user.
	// This button can also be used to show the result of the game.
	private GRect infoButton;
	private GLabel infoLabel;
	
	private double vx;
	private double vy = VELOCITY_Y;
	
	// Flag for the start or the end of the game.
	Boolean gameStart  = true;
	
	// Turns left for the user to play.
	int turnsLeft = NTURNS;
	
	// Bricks left not collides with the ball.
	int bricksLeft = NBRICK_COLUMNS * NBRICK_ROWS;
}
