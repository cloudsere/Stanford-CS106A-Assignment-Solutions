/*
 * File: UnitedNationsKarel.java
 * ----------------------------
 * The UnitedNationsKarel program builds houses at corners
 * marked by rubble.
 */

import stanford.karel.*;

public class UnitedNationsKarel extends SuperKarel {

	public void run() {
		while(frontIsClear()) {
			move();
			if(beepersPresent()) {
				buildHouseOnRubble();
			}
		}
	}
	
	/*
	 * Postcondition: Karel finishes building house facing east.
	 * Karel stands at the rightmost root of the house.
	 */
	private void buildHouseOnRubble() {
		/*
		 * Karel comes back to the leftmost root of the house facing east.
		 */
		turnAround();
		move();
		turnAround();
		
		for(int i=0; i<2; i++) {
			buildThreeBeepers();
			walkToWall();
			move();
		}
		buildThreeBeepers();
		walkToWall();
	}
	
	/*
	 * Precondition: Karel stands at root of the house facing east.
	 * Postcondition: Karel finishes putting three beepers at his initial place
	 * and the second and the third places.Karel faces south in the end.
	 */
	private void buildThreeBeepers() {
		turnLeft();
		/*
		 * Special case for the center of the house.
		 * Karel should start putting beepers after one step move.
		 */
		if(beepersPresent()) {
			pickBeeper();
			move();
		}
		/*
		 * Use a for loop to put beeper and move to the next position. In case
		 * height of the house changes over time.
		 */
		for(int i = 0; i<2; i++) {
			putBeeper();
			move();
		}
		/*
		 * Karel doesn't need to move forward at last position.
		 */
		putBeeper();
		turnAround();
	}
	
	/*
	 * Precondition: Karel faces south
	 * Postcondition: Karel moves to the wall facing east.
	 */
	private void walkToWall() {
		while(frontIsClear()) {
			move();
		}
		turnLeft();
	}
}
