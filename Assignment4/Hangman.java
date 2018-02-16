/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman extends ConsoleProgram {

	/***********************************************************
	 *              CONSTANTS                                  *
	 ***********************************************************/
	
	/* The number of guesses in one game of Hangman */
	private static final int N_GUESSES = 7;
	/* The width and the height to make the karel image */
	private static final int KAREL_SIZE = 150;
	/* The y-location to display karel */
	private static final int KAREL_Y = 230;
	/* The width and the height to make the parachute image */
	private static final int PARACHUTE_WIDTH = 300;
	private static final int PARACHUTE_HEIGHT = 130;
	/* The y-location to display the parachute */
	private static final int PARACHUTE_Y = 50;
	/* The y-location to display the partially guessed string */
	private static final int PARTIALLY_GUESSED_Y = 430;
	/* The y-location to display the incorrectly guessed letters */
	private static final int INCORRECT_GUESSES_Y = 460;
	/* The fonts of both labels */
	private static final String PARTIALLY_GUESSED_FONT = "Courier-36";
	private static final String INCORRECT_GUESSES_FONT = "Courier-26";
	
	/***********************************************************
	 *              Instance Variables                         *
	 ***********************************************************/
	
	/* An object that can produce pseudo random numbers */
	private RandomGenerator rg = new RandomGenerator();
	
	private GCanvas canvas = new GCanvas();
	
	/* guessedWord and wrongGuesses are painted on the canvas to show user
	 * the partially guessed word and the user’s incorrect guesses.
	 * Set these variables as instance variables, because they need
	 * to be updated every time the user enters a guess.
	 */
	private GLabel guessedWord;
	private GLabel wrongGuesses;
	
	/* KarelImage stores the karel GImage object */
	private GImage karelImage;
	
	/* ConnectLine is an ordered arraylist containing every
	 * connecting line between Karel and parachute.
	 */
	private ArrayList<GLine> connectLine = new ArrayList<GLine>();
	
	/* ArrayList stores the word read from the file */
	private ArrayList<String> wordList = new ArrayList<String>();
	
	
	/***********************************************************
	 *                    Methods                              *
	 ***********************************************************/
	
	public void init() {
		add(canvas);
	}
	
	public void run() {
		setUpWordList();
		setUpCanvas();
		setUpConsole();
	}
	
	private void setUpCanvas(){
		drawBackground();
		drawKarel("karel.png");
		drawParachute();
		drawConnectLine();
	}
	
	private void setUpConsole() {
		/* Answer to the riddle */
		String answer = getRandomWord();
		
		/* Number of left guesses in a game. */
	    int guessesLeft = N_GUESSES;
		
		/* answerWithHole is now initialized to a '-' made string */
		String answerWithHole = setUpPrompt(answer);
		
		/* Draw initial hint on the canvas.*/
		drawGuessedWord(answerWithHole);
		
		/* Loop while the user still gets chance to guess and the user
		 * hasn't reach the final answer.
		 */
		while(guessesLeft > 0 && !answerWithHole.equals(answer)) {
			char guess = getUserGuess();
		
			if(verifyGuess(answer, guess)) {
				/* If the guess is a right guess */
				answerWithHole = generateAnswerWithHole(answer, answerWithHole, guess);
				
				updateGuessedWord(answerWithHole);
				println("That guess is correct.");
			}else {
				guessesLeft--;
				
				cutConnectLine(guessesLeft);
				drawWrongGuesses(guess);
				println("There are no " + guess + "\'s in the word.");
			}
			if(guessesLeft == 0) flipKarel();
			printPromptAfterGuess(guessesLeft, answer, answerWithHole);
		}
	}
	
	private void drawWrongGuesses(char character) {
		String text;
		if(wrongGuesses != null) {
			text = wrongGuesses.getLabel() + character;
			canvas.remove(wrongGuesses);
		}else {
			text = character + "";
		}
		wrongGuesses = new GLabel(text);

		/* Set font first to get the right size */
		wrongGuesses.setFont(INCORRECT_GUESSES_FONT);
		double x = (canvas.getWidth() - wrongGuesses.getWidth())/2;
		double y = INCORRECT_GUESSES_Y;
		canvas.add(wrongGuesses, x, y);
	}
	
	private void drawGuessedWord(String text) {
		guessedWord = new GLabel(text);

		/* Set font first to get the right size */
		guessedWord.setFont(PARTIALLY_GUESSED_FONT);
		double x = (canvas.getWidth() - guessedWord.getWidth())/2;
		double y = PARTIALLY_GUESSED_Y;
		canvas.add(guessedWord, x, y);
	}
	
	private void updateGuessedWord(String text) {
		guessedWord.setLabel(text);
	}
	
	private void flipKarel() {
		drawKarel("karelFlipped.png");	
	}
	
	/* The left most line is the first element in the arraylist,
	 * while the right most line is the last element.
	 */
    private void cutConnectLine(int guessLeft) {
    	    if(connectLine.isEmpty()) return;
    		GLine line;
    		if( (N_GUESSES - guessLeft)%2 == 1) {
    			line = connectLine.remove(connectLine.size() - 1 );
    		}else {
    			line = connectLine.remove(0);
    		}
    		canvas.remove(line);
	}
	
	private void drawConnectLine() {
		/* TopY is the y-coordinate of the upper point of the line. */
		double topY = PARACHUTE_Y + PARACHUTE_HEIGHT;
		double parachuteX = (canvas.getWidth() - PARACHUTE_WIDTH)/2;
		
		/* Coordinates of karel. */
		double karelX = (canvas.getWidth() - KAREL_SIZE)/2;
		double karelY = KAREL_Y;
		
		double bottomX = karelX + KAREL_SIZE / 2;
		double bottomY = karelY;
		
		/* Interval between each x coordinate of the upper point of the line. */
		double interval = PARACHUTE_WIDTH / (N_GUESSES-1);
		
		for(int i = 0; i < N_GUESSES; i++) {
			double x = parachuteX + i * interval;
			GLine line = new GLine(x, topY, bottomX, bottomY);
			connectLine.add(line);
			canvas.add(line);
		}
	}
	
	private void drawKarel(String filename) {
		if(karelImage != null) canvas.remove(karelImage);
		double width = KAREL_SIZE;
		double height = KAREL_SIZE;
		double x = (canvas.getWidth() - width)/2;
		double y = KAREL_Y;
		karelImage = drawImage(filename, width, height, x, y);
	}
	
	private void drawParachute() {
		double width = PARACHUTE_WIDTH;
		double height = PARACHUTE_HEIGHT;
		double x = (canvas.getWidth() - width)/2;
		double y = PARACHUTE_Y;
		drawImage("parachute.png", width, height, x, y);
	}
	
	private void drawBackground() {
		double width = canvas.getWidth();
		double height = canvas.getHeight();
		drawImage("background.jpg", width, height, 0, 0);
	}

	private GImage drawImage(String filename, double width, double height, double x, double y) {
		GImage img = new GImage(filename);
		img.setSize(width, height);
		canvas.add(img, x, y);
		return img;
	}
	
	/* Prints initial prompt and returns a '-' made string with
	 * the number of the '-' equals to the length of the answer
	 */
	private String setUpPrompt(String answer) {
		String output = "";
		 
		for(int i = 0; i<answer.length(); i++) {
			output += "-";
		}
		println("Welcome to Hangman");
		println("Your word now looks like this: " + output);
		
		/* Print out the initial amount of guesses.*/
		println("You have " + N_GUESSES + " left.");
		return output;
	}
	
	/* Return upper case of the user guess */
	private char getUserGuess() {
		String errorMessage = "Illegal input, please guess again.";
				
		while(true) {
			String userGuess = readLine("Your guess: ");
			/* If user enters only one character and the character is a letter*/
			if(userGuess.length() == 1 && Character.isLetter(userGuess.charAt(0))) {
				return Character.toUpperCase(userGuess.charAt(0));
			}else {
				println(errorMessage);
			}
		}
	}
	
	private boolean verifyGuess(String answer, char guess) {
		/* If a new guess is in the answer */
		if(answer.indexOf(guess) >= 0) {
			return true;
		}else {
			return false;
		}
	}
	
	private String generateAnswerWithHole(String answer, String answerWithHole, char guess) {
		int index = answer.indexOf(guess);
		String output = answerWithHole;
		while(index >= 0) {
			output = output.substring(0, index) + guess + output.substring(index+1);
			index = answer.indexOf(guess, index + 1);
		}
		return output;
	}
	
	/* Print what the word looks like after one guess.
	 * Print the number of guesses left for the user.
	 */
	private void printPromptAfterGuess(int guessLeft, String answer, String answerWithHole){
		
		/* If the user hasn't figured out the right word and still got chances to guess. */
		if(guessLeft > 0 && !answer.equals(answerWithHole)) {
			println("Your word now looks like this: " + answerWithHole);
			println("You have " + guessLeft + " guesses left.");
		}
		
		if(guessLeft > 0 && answer.equals(answerWithHole)) {
			/* If the user figures out the word with his amount of guesses in a game. */
			println("You win.");
			println("The word was: " + answer);
		}
		
		if(guessLeft <= 0) {
			/* If the user runs out of all the chances to guess */
			println("You are completely hung.");
			println("The word was: " + answer);
		}
	}
	
	private void setUpWordList() {
		readWordFile("HangmanLexicon.txt");
	}
	
	private void readWordFile(String filename) {
		try {
			Scanner sc = new Scanner(new File(filename));
			while(sc.hasNextLine()) {
				wordList.add(sc.nextLine());
			}
			sc.close();
		}catch(IOException ex) {
			println("Cannot open file");
		}
		
	}
	
	/**
	 * Method: Get Random Word
	 * -------------------------
	 * This method returns a word to use in the hangman game.
	 * It randomly selects from wordList.
	 */
	private String getRandomWord() {
		int index = rg.nextInt(wordList.size() - 1);
		return wordList.get(index);
	}

}
