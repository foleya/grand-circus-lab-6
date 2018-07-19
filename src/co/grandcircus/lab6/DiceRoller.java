// Written by Andrew (Dewey) Foley on 7/19/18

package co.grandcircus.lab6;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DiceRoller {

    public static void main(String[] args) {
	Scanner scnr = new Scanner(System.in);

	System.out.println("Hello, welcome to the dice roller!");

	playingDiceLoop(scnr);

	System.out.println("Alea iacta est! Goodbye, and beware the Ides of March!");

	scnr.close();
    }

    /**
     * STUFF
     * 
     * @param scnr
     */
    private static void playingDiceLoop(Scanner scnr) {
	String continueProgram;
	int sides;
	do {
	    sides = getValidDiceSides(scnr);

	    rollingDiceLoop(scnr, sides);

	    System.out.println("Would you like to try dice with different numbers of side? (y/N)");
	    continueProgram = scnr.nextLine();

	} while (continueProgram.equals("y"));
    }

    /**
     * 
     * @param scnr
     * @return
     */
    private static int getValidDiceSides(Scanner scnr) {
	boolean inputIsValid = false;
	int diceSides = 0;

	do {
	    System.out.print("\nHow many sides on the dice? (Choose 6 to play Craps): ");
	    try {
		diceSides = scnr.nextInt();
		if (diceSides > 0) {
		    inputIsValid = true;
		} else {
		    System.out.println("\n -- You must enter a positive integer! --\n");
		}
	    } catch (InputMismatchException ime) {
		System.out.println("\n -- You must enter a positive integer! --\n");
	    }

	    scnr.nextLine(); /* Clear trash values */

	} while (!inputIsValid);

	return diceSides;
    }

    /**
     * 
     * @param scnr
     * @param sides
     */
    private static void rollingDiceLoop(Scanner scnr, int sides) {
	String rollAgain;
	do {
	    // prompt user to roll
	    System.out.print("\nHit enter to roll the dice");
	    scnr.nextLine();

	    // display results
	    int dieOneResult = rollDie(sides);
	    int dieTwoResult = rollDie(sides);

	    displayResults(sides, dieOneResult, dieTwoResult);

	    // ask if user wants to roll again
	    System.out.println("Would you like to roll again? (Y/n)");
	    rollAgain = scnr.nextLine().toLowerCase().trim();

	} while (!rollAgain.equals("n"));
    }

    /**
     * 
     * @param sides
     * @param dieOneResult
     * @param dieTwoResult
     */
    private static void displayResults(int sides, int dieOneResult, int dieTwoResult) {
	int dieSum = dieOneResult + dieTwoResult;
	String slang;

	// Print results
	System.out.println("Die 1: " + dieOneResult);
	System.out.println("Die 2: " + dieTwoResult);

	// If playing Craps, check rolls and assign slang
	if (sides == 6) {
	    slang = getCrapsSlang(dieOneResult, dieTwoResult, dieSum);
	    // Print roll and slang
	    System.out.printf("You rolled %d! %s!!%n%n", dieSum, slang);
	}
    }

    /**
     * 
     * @param dieOneResult
     * @param dieTwoResult
     * @param dieSum
     * @return
     */
    private static String getCrapsSlang(int dieOneResult, int dieTwoResult, int dieSum) {
	String slang;
	// When both rolls were the same (The Hard Way)
	if (dieOneResult == dieTwoResult) {
	    if (dieSum == 2) {
		slang = "Snake Eyes";
	    } else if (dieSum == 4) {
		slang = "Ballerina";
	    } else if (dieSum == 6) {
		slang = "Jimmie Hicks from the Sticks";
	    } else if (dieSum == 8) {
		slang = "Mom and Dad";
	    } else if (dieSum == 10) {
		slang = "Dos Equis";
	    } else {
		slang = "Boxcars";
	    }

	    // When rolls are different (The Easy Way)
	} else {
	    if (dieSum == 3) {
		slang = "Ace Deuce";
	    } else if (dieSum == 4) {
		slang = "Little Joe from Kokomo";
	    } else if (dieSum == 5) {
		slang = "Fever Five";
	    } else if (dieSum == 6) {
		slang = "Easy Six";
	    } else if (dieSum == 7) {
		slang = "Natural";
	    } else if (dieSum == 8) {
		slang = "Easy Eight";
	    } else if (dieSum == 9) {
		slang = "Nina from Pasadena";
	    } else if (dieSum == 10) {
		slang = "Easy Ten";
	    } else {
		slang = "Six five no jive";
	    }
	}
	return slang;
    }

    /**
     * 
     * @param sides
     * @return
     */
    private static int rollDie(int sides) {
	Random die = new Random();
	return die.nextInt(sides) + 1;
    }

}
