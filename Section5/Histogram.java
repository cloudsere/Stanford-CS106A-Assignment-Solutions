/*
 * File: Histogram.java
 * --------------------
 * This program reads a list of exam scores, with one score per line.
 * It then displays a histogram of those scores, divided into the
 * ranges 0-9, 10-19, 20-29, and so forth, up to the range containing
 * only the value 100.
 */

import acm.program.*;
import acm.util.*;
import java.io.*;
import java.util.*;

public class Histogram extends ConsoleProgram {	
	
	private static int INTERVAL = 10;
	public void run() {
		ArrayList<Integer> examScores = getScoreFromFile();
		int[] examHistogram = generateHistogram(examScores);
		printHistogram(examHistogram);
	}
	
	private void printHistogram(int[] examHistogram) {
		for(int i = 0; i < examHistogram.length - 1; i++) {
			//format the output
			String leftLabel = (i * 10 + "0").substring(0,2);
			String rightLabel = ("0" + (i* 10 + 9));
			int rightLabelLen = rightLabel.length();
			rightLabel = rightLabel.substring(rightLabelLen-2);
			
			String label = leftLabel + "-" + rightLabel;
			
			String number = "";
			for(int j = 0; j < examHistogram[i]; j++) {
				number += "*";
			}
			
			println(label + ":" + number);
		}
		// print amount of 100s
		String lastLabel = "  100";
		String lastNumber = "";
		for(int i = 0; i < examHistogram[examHistogram.length-1]; i++) {
			lastNumber += "*";
		}
		println(lastLabel + ":" + lastNumber);
	}
	
	private int[] generateHistogram(ArrayList<Integer> examScores) {
		int len = 100/INTERVAL + 1;
		int[] output = new int[len];
		
		for(int i = 0; i < examScores.size(); i++) {
			// int/int will only get an integer without the fractional part,
			// so it's easy to divide the scores into different part
			int group = examScores.get(i).intValue() / 10;
			output[group]++;
		}
		return output;
	}
	private ArrayList<Integer> getScoreFromFile() {
		Scanner sc = openFile("Please enter the file name: ");
		// Use an arraylist to store scores with uncertain length
		ArrayList<Integer> scoreArray = new ArrayList<Integer>();
		while(sc.hasNext()) {
			int score = sc.nextInt();
			scoreArray.add(score);
		}
		sc.close();
		return scoreArray;
	}
	
	private Scanner openFile(String prompt) {
		Scanner sc = null;
		while(sc == null) {
			String name = readLine(prompt);
			try {
				sc = new Scanner(new File(name));
			}catch(IOException e) {
				throw new ErrorException(e);
			}
		}
		return sc;
	}

}
