/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 * 
 * Precondition: None
 * Postcondition: 
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		findMidBeeper();
		clearBeepersOnTwoSides();
		returnToMidBeeper();
	}
	
	private void findMidBeeper() {
		putBeeperAtStartAndEnd();
		
		turnAround();
		if(frontIsClear()) {
			move();
			putBeeperBetweenStartAndEnd();
		}
	}
	
	private void putBeeperAtStartAndEnd() {
		putBeeper();
		moveToWall();
		putBeeper();
	}
	private void putBeeperBetweenStartAndEnd() {
		while(noBeepersPresent()) {
			moveToBeeper();
			turnAround();
			move();
			putBeeper();
			move();
		}
	}
	
	private void clearBeepersOnTwoSides() {
		collectOneSideBeepers();
		
		turnAround();
		moveToBeeper();
		if(frontIsClear()) {
			move();
			collectOneSideBeepers();
		}
	}
	
	private void collectOneSideBeepers() {
		while(frontIsClear()) {
			pickBeeper();
			move();
		}
		pickBeeper();
	}
	
	private void returnToMidBeeper() {
		turnAround();
		moveToBeeper();
	}
	
	private void moveToWall() {
		while(frontIsClear()) {
			move();
		}
	}
	
	private void moveToBeeper() {
		while(noBeepersPresent() && frontIsClear()) {
			move();
		}
	}
	
}
