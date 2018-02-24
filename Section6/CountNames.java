/* File: CountNames.java
 * ---------------------
 * This program shows an example of using a HashMap.  It reads a 
 * list of names from the user and list out how many times each name 
 * appeared in the list.
 */
import acm.program.*;
import java.util.*;

public class CountNames extends ConsoleProgram {
	public void run() {
		HashMap<String, Integer> nameList = new HashMap<String, Integer>();
		loadNameList(nameList);
		printList(nameList);
	}
	
	private void loadNameList(HashMap<String, Integer> list) {
		while(true) {
			String name = readLine("Enter name: ");
			if(name.equals("")) break;
			if(list.containsKey(name)) {
				int number = list.get(name);
				list.put(name, number + 1);
			}else {
				list.put(name, 1);
			}
		}
	}
	
	private void printList(HashMap<String, Integer> list) {
		Iterator<String> it = list.keySet().iterator();
		while(it.hasNext()) {
			String name = it.next();
			println("Entry [" + name + "] has count " + list.get(name));
		}
	}

}  
