package com.shpp.p2p.cs.oklymenchuk.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Task 4. Make a chessboard.
 * At a number of employees Karel was instructed to set up a chessboard behind
 * the auxiliary beeper in a rectangular (NOT square!) empty world (the final position and Karel is not important).
 * It is important that one of the set beepers is located at the backward-facing corner of the world.
 * Karel always starts from the South-West corner looking East. The world is clear from walls and beepers.
 *
 */
public class Assignment1Part4 extends KarelTheRobot {
    public void run() throws Exception {

        karelInit();

        if (frontIsBlocked()){

            putBeeper();

            turnLeft();

            fillsTheLineBeepers();

        } else {

            while (frontIsClear()) {

                if (!beepersPresent()) {
                    putBeeper();
                }

                fillsTheLineBeepers();

                if (facingEast() && frontIsBlocked() && leftIsClear()) {
                    if (beepersPresent()) {
                        leftReverseTurn();
                        move();
                    } else {
                        leftReverseTurn();
                    }
                }

                if (facingWest() && frontIsBlocked() && rightIsClear()) {
                    if (beepersPresent()) {
                        rightReverseTurn();
                        move();
                    } else {
                        rightReverseTurn();
                    }
                }
            }
        }
        // когда закончили работу - сообщили
        say("Job is finished!");
    }

    private void fillsTheLineBeepers() throws Exception {
        while (frontIsClear()){
            move();
            if (frontIsClear()) {
                move();
                putBeeper();
            }
        }
    }

    private void rightReverseTurn() throws Exception {
        turnRight();
        move();
        turnRight();
    }

    private void leftReverseTurn() throws Exception {
        turnLeft();
        move();
        turnLeft();
    }

    /**
     * Initialize Karel.
     * Karel must say "Hello. Ready to work." (switch on robot).
     */
    private void karelInit() throws Exception {
        say("Hello. Ready to work.");
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
