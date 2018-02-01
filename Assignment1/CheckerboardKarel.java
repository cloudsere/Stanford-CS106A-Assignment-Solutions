/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		putBeeper();
		
		while(frontIsClear()) {
			fillInOneAvenue();
			backToWall();
			moveToNextAvenue();
		}
		fillInOneAvenue();
	}
	
	private void fillInOneAvenue() {
		turnLeft();
		if(noBeepersPresent() && frontIsClear()) {
			move();
			putBeeper();
		}
		putBeeperOneStreetApart();
	}
	
	private void putBeeperOneStreetApart() {
		while(frontIsClear()) {
			move();
			if(frontIsClear()) {
				move();
				putBeeper();
			}
		}
	}
	private void backToWall() {
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnLeft();
	}
	
	private void moveToNextAvenue() {
		if(beepersPresent()) {
			move();
		}else {
			move();
			putBeeper();
		}
	}
}
