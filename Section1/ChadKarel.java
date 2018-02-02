/*
 * File: ChadKarel.java
 * --------------------
 * ChadKarel is a program in which Karel cleans up hanging chads
 * from a ballot.
 */

import stanford.karel.*;

public class ChadKarel extends SuperKarel {
	public void run() {
		while(frontIsClear()) {
			checkBallot();
			move();
		}
	}
	
	private void checkBallot() {
		if(beepersPresent()) {
			moveToNextBallot();
		}else {
			cleanBallot();
		}
	}
	
	private void moveToNextBallot() {
		move();
	}
	
	/*
	 * Karel cleans the ballot from the upper beeper to the lower beeper
	 * Precondition: Karel stands at the center of ballot
	 */
	private void cleanBallot() {
		cleanUpperBeeper();
		cleanLowerBeeper();
		backToBallotCenter();
	}
	
	private void cleanUpperBeeper() {
		turnLeft();
		/*
		 * There might be more than one beeper.
		 */
		move();
		while(beepersPresent()) {
			pickBeeper();
		}
	}
	
	private void cleanLowerBeeper() {
		turnAround();
		move();
		move();
		while(beepersPresent()) {
			pickBeeper();
		}
	}
	
	/*
	 * Precondition: Karel cleans up all the beepers facing south.
	 * Postcondition: Karel stands at center of ballot facing east;
	 */
	private void backToBallotCenter() {
		turnAround();
		move();
		turnRight();
	}
}
