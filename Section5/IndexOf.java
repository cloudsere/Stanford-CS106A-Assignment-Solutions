/*
 * File: IndexOf.java
 * ----------------------
 * This program is a test program for the index of method in section
 * handout #5, problem 4.  It tests calls to the indexOf method and
 * prints out the results.
 */

import acm.program.*;

public class IndexOf extends ConsoleProgram {
	
	public void run() {
		int[] arr = {42, 7, -9, 14, 8, 39, 42, 8, 19, 0};
		println(indexOf(arr, 8));
		println(indexOf(arr, 2));
	}
	
	private int indexOf(int[] array, int key) {
		for(int i = 0; i < array.length; i++) {
			if(key == array[i]) {
				 return i;
			}
		}
		return -1;
	}
}
