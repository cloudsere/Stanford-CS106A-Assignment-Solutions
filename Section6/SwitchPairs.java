/* File: SwitchPairs.java
 * ------------------------
 * This is a program to test the switchPairs method that
 * swaps pairs of elements in a provided string array and
 * returns the new array.
 */
import acm.program.*;
import java.util.*;

public class SwitchPairs extends ConsoleProgram {
	public void run() {
		String[] arr1 = {"four", "score", "and", "seven", "years", "ago"};
		String[] arr2 = {"one", "two", "three", "four", "five"};
		println(Arrays.toString(arr1) + " -> " + Arrays.toString(switchPairs(arr1)));
		println(Arrays.toString(arr2) + " -> " + Arrays.toString(switchPairs(arr2)));
	}
	
	private String[] switchPairs(String[] arr) {
		int len = arr.length;
		String[] output = new String[len];
		for(int i = 0; i<len/2; i++) {
			output[2*i] = arr[2*i+1];
			output[2*i+1] = arr[2*i];
		}
		if(len%2 == 1) output[len-1] = arr[len-1];
		return output;
	}
}
