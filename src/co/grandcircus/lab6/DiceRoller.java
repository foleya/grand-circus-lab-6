// Written by Andrew (Dewey) Foley on 7/19/18

package co.grandcircus.lab6;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DiceRoller {

    public static void main(String[] args) {
	playingDiceLoop();
    }

    /**
     * This method contains the primary logic loop. It greets the user, asks them
     * how many sides the dice should have, allows the user to roll those dice until
     * they want to stop, then allows the user to try again with different dice if
     * they want, but saying goodbye and ending the program if they do not.
     * 
     */
    private static void playingDiceLoop() {
	String continueProgram;
	int diceSides;
	Scanner scnr = new Scanner(System.in);

	// Greet the user
	System.out.println("Hello, welcome to the dice roller!");

	// Loop program until user decides to quit
	do {
	    // Ask the user how many sides the dice should have.
	    diceSides = getValidDiceSides(scnr);

	    // Roll those dice until the user chooses to quit rolling them
	    rollingDiceLoop(scnr, diceSides);

	    // Ask if user would like to start over with different-sided dice
	    System.out.println("Would you like to try dice with different numbers of side? (Y/n)");
	    continueProgram = scnr.nextLine();

	} while (!continueProgram.equals("n"));

	// Say goodbye and close scanner
	System.out.println("Alea iacta est! Goodbye, and beware the Ides of March!");
	scnr.close();

    }

    /**
     * This method asks the user how many sides they want on the dice they are about
     * to roll. It takes a Scanner as a parameter, and uses that to read user input,
     * validating that the input is an integer that is greater than zero, then
     * returning the chosen integer.
     * 
     * @param scnr (Scanner)
     * @return diceSides (integer)
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
     * This method takes a Scanner (to read input) and an integer (representing the
     * number of sides on a die) as parameters. It prompts the user to roll the
     * dice, then displays the results of those rolls. It loops, allowing the user
     * to re-roll the dice, until the user wishes to stop rolling.
     * 
     * @param scnr (Scanner)
     * @param sides (integer)
     */
    private static void rollingDiceLoop(Scanner scnr, int sides) {
	String rollAgain;
	do {
	    // Prompt the user to roll dice
	    System.out.print("\nHit enter to roll the dice");
	    scnr.nextLine();

	    // Get results for two dice rolls
	    int dieOneResult = rollDie(sides);
	    int dieTwoResult = rollDie(sides);

	    // Display the results of the two rolls.
	    int dieSum = dieOneResult + dieTwoResult;
	    System.out.println("Die 1: " + dieOneResult);
	    System.out.println("Die 2: " + dieTwoResult);

	    // If playing craps, fetch the slang and print it
	    if (sides == 6) {
		String slang;
		slang = getCrapsSlang(dieOneResult, dieTwoResult);
		System.out.printf("You rolled %d! %s!!%n%n", dieSum, slang);
	    } else {
		// Otherwise just print the total.
		System.out.println("Total: " + dieSum);
	    }

	    // Ask if user wants to roll again
	    System.out.print("Would you like to roll again? (Y/n)");
	    rollAgain = scnr.nextLine().toLowerCase().trim();

	} while (!rollAgain.equals("n"));
    }

    /**
     * This method takes two integers that represent two rolls on six-sided dice,
     * then checks for the corresponding Craps slang term for the rolls, returning
     * the appropriate slang term as a String.
     * 
     * @param dieOneResult (integer)
     * @param dieTwoResult (integer)
     * @return slang (String)
     */
    private static String getCrapsSlang(int dieOneResult, int dieTwoResult) {
	int dieSum = dieOneResult + dieTwoResult;
	String slang;
	// When both rolls were the same ("The Hard Way")
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

	    // When rolls are different ("The Easy Way")
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
     * This method takes an integer (representing the number of sides on a die),
     * then using the Random class, returns an integer representing a pseudo-random
     * integer representing a roll of a die with the specified number of sides.
     * 
     * @param sides (integer)
     * @return integer
     */
    private static int rollDie(int sides) {
	Random die = new Random();
	/*
	 * You must add 1 to the result of nextInt because its range is 0 (inclusive)
	 * whereas the lowest value on most dies is 1, up to whatever integer is
	 * provided as an argument (exclusive). Here, because the number of sides is
	 * provided as an argument, nextInt returns an integer between (0 and (sides-1).
	 * Adding one, makes this function return and integer between (1 and (sides)).
	 * 
	 */
	return die.nextInt(sides) + 1;
    }

}
