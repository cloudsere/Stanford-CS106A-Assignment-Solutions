/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int initialNumber = readInt("Enter a number: ");
		int nextNumber = initialNumber;
		
		/*
		 * Use round to count each process.
		 */
		int round = 0;
		
		/*
		 * Prepare two variables.InitialNumber is used as start the input of each calculation.
		 * NextNumber is the result of each calculation, if nextNumber reaches to 1,
		 * the while loop should stop.
		 */
		while(nextNumber!=1) {
			if(initialNumber%2 == 0) {
				nextNumber = initialNumber/2;
				println(initialNumber + " is even so I take half: " + nextNumber);
			}else {
				nextNumber = initialNumber * 3 + 1;
				println(initialNumber + " is odd, so I make 3n + 1: " + nextNumber);
			}
			initialNumber = nextNumber;
			round++;
		}
		println("The process took " + round + " to reach 1");
	}
}

