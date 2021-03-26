package com.shpp.p2p.cs.oklymenchuk.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Task 4. Make a chessboard.
 */
public class Assignment1Part4 extends KarelTheRobot {
	public void run() throws Exception {

		karelInit();

		if (frontIsBlocked()) {                                // has 1xN world
			putBeeper();                                    // put a beeper at the start position
			turnLeft();                                        // look North
			fillsTheLineBeepers();                            // fill the line with beepers through one cell
		} else {                                            // has Mx1 or MxN world
			while (frontIsClear()) {                        // main cycle
				putBeeper();                                // put a beeper at the start position
				fillsTheLineBeepers();                        // fill the line with beepers through one cell

				if (facingEast() && frontIsBlocked() && leftIsClear()) {// reached the left border
					if (beepersPresent()) {                    // has a beeper in the last cell
						moveToTheLeftLineAndTurnLeft();        // go to the next line
						move();                                // and make one step for the indent between beepers
					} else {
						moveToTheLeftLineAndTurnLeft();        // go to the next line
					}
				}

				if (facingWest() && frontIsBlocked() && rightIsClear()) {// reached the right border
					if (beepersPresent()) {                    // has a beeper in the last cell
						moveToTheRightLineAndTurnRight();   // go to the next line
						move();                                // and make one step for indent between beepers
					} else {
						moveToTheRightLineAndTurnRight();    // go to the next line
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
		while (frontIsClear()) {
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
	 * Goes to the next left line and turns left again.
	 * <-
	 * |
	 * ->
	 */
	private void moveToTheLeftLineAndTurnLeft() throws Exception {
		turnLeft();
		move();
		turnLeft();
	}

	/**
	 * Goes to the next right line and turns right again.
	 * ->
	 * |
	 * <-
	 */
	private void moveToTheRightLineAndTurnRight() throws Exception {
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
