/*
 * File: Countdown.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the Countdown problem.
 */

import acm.program.*;

public class Countdown extends ConsoleProgram {
	
	/** Count down to 0 from this number */
	private static final int START = 10;

	public void run() {
		int number = START;
		/*
		 * Use a while loop for simplicity.
		 */
		while(number > 0) {
			println(number);
			number--;
		}
		println("Liftoff");
	}
}

