/* File: MutualFriends.java
 * ------------------------
 * An example program that tests the mutualFriends method for finding
 * friends in common between two maps/phonebooks.
 */ 
import acm.program.*;
import java.util.*;

public class MutualFriends extends ConsoleProgram {
	public void run() {
		HashMap<String, Integer> map1 = new HashMap<String, Integer>();
		map1.put("Jenny", 8675309);
		map1.put("Nick", 2124320);
		map1.put("Mehran", 4602121);
		map1.put("Alyssa", 4444444);
		map1.put("Stefanie", 8080543);
		
		HashMap<String, Integer> map2 = new HashMap<String, Integer>();
		map2.put("Logan", 6202121);
		map2.put("Jeff", 8888888);
		map2.put("Nick", 2124320);
		map2.put("Mehran", 4602121);
		map2.put("Alyssa", 4444444);
		map2.put("Jenny", 2128765);
		
		HashMap<String, Integer> mutualMap = mutualFriends(map1, map2);
		println(mutualMap);
	}
	
	private HashMap<String, Integer> mutualFriends(HashMap<String, Integer> map1, HashMap<String, Integer> map2){
		HashMap<String, Integer> output = new HashMap<String, Integer>();
		for(String name: map1.keySet()) {
			int value = map1.get(name);
			// Cannot compare two integers with == because they are objects
			// So if use map1.get(name) == map2.get(name), this condition will never be passed
			if(map2.containsKey(name) && map1.get(name).equals(map2.get(name))) {
				output.put(name, value);
			}
		}
		return output;
	}
}
