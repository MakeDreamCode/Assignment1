package com.shpp.p2p.cs.oklymenchuk.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Task 4. Make a chessboard.
 */
public class Assignment1Part4 extends KarelTheRobot {
    public void run() throws Exception {

        karelInit();

        if (frontIsBlocked()){								// has 1xN world
            putBeeper();
            turnLeft();
            fillsTheLineBeepers();
        } else {											// has MxN world
            while (frontIsClear()) {						// main cycle

            	if (!beepersPresent()) {					// puts a beeper at the start position
                    putBeeper();
                }

                fillsTheLineBeepers();						// fills the line with beepers through one cell

                if (facingEast() && frontIsBlocked() && leftIsClear()) {// reached the left border
                    if (beepersPresent()) {					// has a beeper in the last cell
                        leftReverseTurn();					// goes to the next line
                        move();								// and makes one step for indent between beepers
                    } else {
                        leftReverseTurn();				    // go to the next line
                    }
                }

                if (facingWest() && frontIsBlocked() && rightIsClear()) {// reached the right border
                    if (beepersPresent()) {					// has a beeper in the last cell
                        rightReverseTurn();					// goes to the next line
                        move();								// and makes one step for indent between beepers
                    } else {
                        rightReverseTurn();					// go to the next line
                    }
                }
            }
        }

        say("Job is finished!");
    }

	/**
	 * Fills the line with beepers through one cell.
	 */
    private void fillsTheLineBeepers() throws Exception {
        while (frontIsClear()){
            move();
            if (frontIsClear()) {
                move();
                putBeeper();
            }
        }
    }

	/**
	 * Initialize Karel.
	 * Karel must say "Hello. Ready to work." (switch on robot).
	 */
	private void karelInit() throws Exception {
		say("Hello. Ready to work.");
	}

	/**
	 * Turns left, goes to the next line and turns left again.
	 *  <-
	 *   |
	 * --
	 */
	private void leftReverseTurn() throws Exception {
		turnLeft();
		move();
		turnLeft();
	}

	/**
	 * Turns right, goes to the next line and turns right again.
	 *  ->
	 * |
	 * --
	 */
	private void rightReverseTurn() throws Exception {
        turnRight();
        move();
        turnRight();
    }

    /**
     * Turn right.
     */
    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }
}
