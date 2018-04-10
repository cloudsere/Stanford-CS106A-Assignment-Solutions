/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		int leftIndex = 0;
		int rightIndex = line.indexOf(' ', leftIndex);
		
		rank = new int[NDECADES];
		int loopIndex = 0;
		while(rightIndex > 0) {
			if(loopIndex == 0) {
				name = line.substring(leftIndex, rightIndex);
			}else {
				rank[loopIndex-1] = Integer.parseInt(line.substring(leftIndex, rightIndex));
			}
			
			loopIndex++;
			leftIndex = rightIndex+1;
			rightIndex = line.indexOf(' ', leftIndex);
		}
		rank[NDECADES-1] = Integer.parseInt(line.substring(leftIndex));
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		if(decade < 0 || decade>NDECADES-1) return -1;
		
		return rank[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		String rankString = "";
		for(int i = 0; i < rank.length; i++) {
			rankString += i==0 ? rank[i] : "," + rank[i];
		}
		return name + " [" + rankString + "]";
	}
	
	private String name;
	private int[] rank;
}

