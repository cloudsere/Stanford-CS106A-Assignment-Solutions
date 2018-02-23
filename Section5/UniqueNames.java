/*
 * File: UniqueNames.java
 * ----------------------
 * This program continually asks the user for a name until the user  
 * enters a blank line.  Then the program prints out the list of unique   
 * names entered.
 */
import acm.program.*;
import java.util.*;

public class UniqueNames extends ConsoleProgram {	
	
	private static String SENTINEL = "";
	
	public void run() {
		ArrayList<String> names = new ArrayList<String>();
		while(true) {
			String name = readLine("Enter name: ");
			if(name.equals(SENTINEL)) break;
			if(!names.contains(name)) names.add(name);
		}
		printAllNames(names);
	}
	
	private void printAllNames(ArrayList<String> names) {
		println("Unique name list contains: ");
		for(String name: names) {
			println(name);
		}
	}
}
