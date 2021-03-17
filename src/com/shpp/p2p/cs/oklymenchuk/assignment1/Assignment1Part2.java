package com.shpp.p2p.cs.oklymenchuk.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Task 2. Rows of stones.
 */
public class Assignment1Part2 extends KarelTheRobot{

    public void run() throws Exception {

    	karelInit();

    	while (facingNorth()) {

			if (!beepersPresent()) { 	// puts a beeper if column`s height equals 1
				putBeeper();
			}

			while (frontIsClear()) { 	// go to the top of the column and puts beepers
				move();

				if (!beepersPresent()) {
					putBeeper();
				}
			}

			turnBack();					// if top was reached turn back

			while (frontIsClear()) {	// and go to the bottom of the column
				move();
			}

			if (leftIsClear()){			// it was the last column (left is blocked)?
				turnLeft();				// no - turn left
				makeFoursSteps();		// go to the next column
				turnLeft();				// turn North for continue the cycle
			}
		}

    	say("Job is finished!");
    }

	/**
	 * Initialize Karel.
	 * Karel must say "Hello. Ready to work." (switch on robot).
	 * Turns North.
	 */
	private void karelInit() throws Exception {
		say("Hello. Ready to work.");

		while (!facingNorth()) {
			turnLeft();
		}
	}

	/**
	 * Karel makes four steps (to reach a next column).
	 */
	private void makeFoursSteps() throws Exception {
		for (int i = 0; i < 4; i++) {
			move();
		}
	}

	/**
	 * Turn back.
	 */
	private void turnBack() throws Exception {
		turnLeft();
		turnLeft();
	}
}
