/*
 * File: Mirror.java
 * ----------------------
 * This program is a test program for the mirror method in section
 * handout #5, problem 3.  It asks the user for ArrayList elements,
 * and then prints out an ArrayList with those items and the mirror
 * of those items.
 */

import acm.program.*;
import java.util.*;

public class Mirror extends ConsoleProgram {
	
	public void run() {
		ArrayList<String> myList = new ArrayList<String>();
		prepareList(myList);
		mirror(myList);
		println(myList);
	}
	
	private void prepareList(ArrayList list) {
		while(true) {
			String input = readLine("Input a string: ");
			if(input.equals("")) break;
			list.add(input);
		}
	}
	
	private void mirror(ArrayList list) {
		for(int i = list.size() - 1; i >= 0; i--) {
			list.add(list.get(i));
		}
	}
}
