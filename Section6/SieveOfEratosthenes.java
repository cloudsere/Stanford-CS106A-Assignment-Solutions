/* File: SieveOfEratosthenes.java
 * ------------------------
 * This program prints out prime numbers in the range
 * up to and including UPPER_LIMIT.
 */
import acm.program.*;

public class SieveOfEratosthenes extends ConsoleProgram{
	public static final int UPPER_LIMIT = 1000;
	
	public void run() {
		int len = UPPER_LIMIT - 1;
		int[] array = new int[len];
		for(int i = 0; i < len; i++) {
			array[i] = i+2;
		}
		
		for(int i = 0; i < len; i++) {
			int currentNumber = array[i];
			if(currentNumber > 0) {
				println(currentNumber);
				for(int j = i+currentNumber; j<len; j+=currentNumber) {
					array[j] = -1;
				}
			}
		}
	}
}

//public class SieveOfEratosthenes extends ConsoleProgram{
//	public static final int UPPER_LIMIT = 1000;
//	
//	public void run() {
//		int[] flagArray = initFlagArray();
//		
//		// lh is the index of every circled number
//		for(int lh = 0; lh < flagArray.length; lh++) {
//			if(flagArray[lh] > 0) {
//				for(int j = lh+1; j < flagArray.length; j++) {
//					// mark the number which is composite as -1
//					if(flagArray[j] % flagArray[lh] == 0) flagArray[j] = -1;
//				}
//			}
//		}
//		
//		printPrime(flagArray);
//		
//	}
//	//It's better to print the number once a time when processing the array
//  //in order to reduce one loop
//	private void printPrime(int[] array) {
//		for(int i = 0; i<array.length; i++) {
//			if(array[i] > 0) {
//				println(array[i]);
//			}
//		}
//	}
//	
//	private int[] initFlagArray() {
//		int len = UPPER_LIMIT-1;
//		int[] output = new int[len];
//		for(int i = 0; i < len; i++) {
//			// initialize the array from 2 to 1000
//			output[i] = i+2;
//		}
//		return output;
//	}
//}