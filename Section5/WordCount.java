/*
 * File: WordCount.java
 * --------------------
 * Counts the characters, words, and lines in a file.
 */

import acm.program.*;
import acm.util.ErrorException;

import java.io.*;
import java.util.*;

public class WordCount extends ConsoleProgram {	

	public void run() {
		int lineNumber = 0;
		int wordNumber = 0;
		int charNumber = 0;
		
		Scanner sc = openFile("File: ");
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			lineNumber++;
			wordNumber += calcWordNumber(line);
			charNumber += calcCharNumber(line);
		}
		sc.close();
		
		println("Lines = " + lineNumber);
		println("Words = " + wordNumber);
		println("Chars = " + charNumber);
	}
	
	private Scanner openFile(String prompt) {
		Scanner sc = null;
		while(sc == null) {
			try {
				String name = readLine(prompt);
				sc = new Scanner(new File(name));
			}catch(IOException e){
				throw new ErrorException(e);
			}
		}
		return sc;
	}
	
	private int calcWordNumber(String line) {
		Boolean inWord = false;
		int num = 0;
		for(int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if(Character.isLetter(ch)) {
				inWord = true;
			}else {
				if(inWord) {
					num++;
				}
				inWord = false;
			}
		}
		// If the last character is in a word, add one to the total number of words
		if(inWord) {
			num++;
		}
		return num;
	}
	
	private int calcCharNumber(String line) {
		return line.length();
	}

	
}
