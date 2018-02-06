 /*
 * File: CalculateLine.java
 * --------------------
 * This program reads in a line equation from a user and, 
 * for every x entered until -1, outputs the corresponding y value.
 */

import acm.program.*;

public class CalculateLine extends ConsoleProgram {
	
	/* Defines the term the user enters to stop the program */
	private static final int SENTINEL = -1;
	
	public void run() {
		println("This program calculates y coordinates for a line.");
		int m = readInt("Enter slope (m): ");
		int b = readInt("Enter intercept (b): ");
		int x = readInt("Enter x: ");
		while(x != SENTINEL) {
			int y = m*x + b;
			println("f(" + x + ") = " + y);
			x = readInt("Enter x: ");
		}
	}
}