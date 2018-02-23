/*
 * File: Collapse.java
 * ----------------------
 * This program is a test program for the collapse method in section
 * handout #5, problem 6.  It prompts the user to enter a list of
 * numbers, and then prints out the collapsed elements.
 */

import acm.program.*;

public class Collapse extends ConsoleProgram {
	
	public void run() {
		int[] array = getArrayFromUser();
		int[] collapsedArray = collapse(array);
		
		for(int i = 0; i< collapsedArray.length; i++) {
			println(collapsedArray[i]);
			
		}
	}
	private int[] getArrayFromUser() {
		int len = readInt("Please enter the amount of elements in your array: ");
		int[] output = new int[len];
		for(int i = 0; i < len; i++) {
			output[i] = readInt("Please enter an integer: ");
		}
		return output;
		
	}
	
	private int[] collapse(int[] array) {
		// if the length of the array is even, the length of the output array is array.length/2 + 0
		// if the length of the array is odd, the length of the output array is array.length/2 + 1
		int len = array.length/2 + array.length % 2;
		int[] output = new int[len];
		
		for(int i = 0; i < len; i++) {
			if(2*i == array.length - 1) {
				output[i] = array[2*i];
			}else {
				output[i] = array[2*i] + array[2*i + 1];
			}
		}
		return output;
	}

}
