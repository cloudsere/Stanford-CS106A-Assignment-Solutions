/*
 * File: UniqueNumbers.java
 * ----------------------
 * This program is a test program for the numUnique method in section
 * handout #5, problem 5.  It tests calls to the numUnique method and
 * prints out the results.
 */

import acm.program.*;

public class UniqueNumbers extends ConsoleProgram {
	
	public void run() {
		int[] list1 = {5, 7, 7, 7, 22, 22, 23, 35, 35, 40, 40, 40};
		println("numUnique(list1) = " + numUnique(list1));
		
		int[] list2 = {1, 2, 11, 17, 24, 25, 26, 31, 34, 37, 40, 41};
		println("numUnique(list2) = " + numUnique(list2)); 
	}
	
	private int numUnique(int[] array) {
		if(array.length == 0) return 0;
		
		int count = 1; // for loop will start at the second element, so the count is initialized to 1
		
		for(int i = 1; i < array.length; i++) {
			if(array[i] != array[i-1]) {
				count++;
			}
		}
		return count;
	}
}
