/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import acm.util.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		try {
			Scanner sc = new Scanner(new File(filename));
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				int nameEnd = line.indexOf(' ');
				String name = line.substring(0 , nameEnd);
				dataMap.put(name.toLowerCase(), line);
			}
			sc.close();
		}catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		String nameRank = dataMap.get(name.toLowerCase());
		if(nameRank != null) {
			return new NameSurferEntry(nameRank);
		}else {
			return null;
		}
	}
	
	private HashMap<String, String> dataMap = new HashMap<String, String>();
}

