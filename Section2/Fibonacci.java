
/*
 * File: Fibonacci.java
 * --------------------
 * This program lists the terms in the Fibonacci sequence up to
 * a constant MAX_TERM_VALUE, which is the largest Fibonacci term
 * the program will display.
 */

import acm.program.*;

public class Fibonacci extends ConsoleProgram {

   /* Defines the largest term to be displayed */
	private static final int MAX_TERM_VALUE = 10000;

	public void run() {
		println("This program lists the Fibonacci sequence.");
		int start1 = 0;
		int start2 = 1;

		while(start1 <= MAX_TERM_VALUE) {
			println(start1);
			int temp = start1 + start2;
			start1 = start2;
			start2 = temp;
		}
	}

}
