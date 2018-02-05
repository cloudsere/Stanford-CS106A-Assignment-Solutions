/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		println("This program finds the alrgest and samllest numbers.");

		int userInput = readInt("? ");
		if(userInput == 0) {
			/*
			 * Verify the first user input.
			 */
			println("You didn't enter any numbers.");
		}else {
			/*
			 * Initialize the largest and smallest number as the first user input.
			 */
			int max;
			int min;
			max = userInput;
			min = userInput;
			
			while (true) {
				userInput = readInt("? ");
				if(userInput != 0) {
					if(max < userInput) {
						max = userInput;
					}
					if(min > userInput) {
						min = userInput;
					}
				}else {
					break;
				}
			}
			
			println("smallest: " + min);
			println("largest: " + max);
		}
	}
}

