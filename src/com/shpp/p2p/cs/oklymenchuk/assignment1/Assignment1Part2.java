package com.shpp.p2p.cs.oklymenchuk.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Task 2. Columns.
 * Karel starts in the southwest corner and looks to the east, and he is full of beavers in his backpack.
 * The columns are on the 1st, 5th, 9th row, etc., the last column will be close to the edge of the level.
 * There can be many columns. There may be only one column (world size 1x8).
 * The top of the column is marked by a wall, but do not assume that all the columns are five heights high,
 * and do not assume that all the columns are the same height.
 * You can not put the beeper where they already lie.
 *
 * Algorithm for solving the problem:
 * 1. Look North. Determine column dimensions (at the start only)
 * 2. Fill empty cell with beepers.
 * 3. Select the direction to move to the next column.
 * 4. Move to the next column.
 */
public class Assignment1Part2 extends KarelTheRobot{

    public void run() throws Exception {
        initialize();                                       // switch on robot and look at North

        if (frontIsBlocked()){                              // The high of columns is 1 beeper.
            say("The high of columns is 1 beeper.");
            turnRight();
            while (frontIsClear()){
                if(!beepersPresent()){
                    putBeeper();
                }
                makeFourSteps();
            }
        } else {                                            // The high of columns is 2 beepers and above (normal mode).
            say("The high of columns is 2 beepers and above.");
            while (frontIsClear()) {
                buildColumn();
            }
        }
        say("Job`s done!");
    }

    /**
     * Builds a column. Fills all empty cells and moves to a next column
     */
    private void buildColumn() throws Exception {
        fillEmptyParts();
        if(rightIsClear()){                                 // if Karel is not at the eastern border
            goToNextColumn();                               // go to the next column
        }

    }

    /**
     * Fills empty parts in a column.
     */
    private void fillEmptyParts() throws Exception {
        while(frontIsClear()){                              // until not top of the column
            if(!beepersPresent()){                          // if a cell is empty
                putBeeper();                                // put beeper
            }
            move();                                         // move to a next cell
        }
        if(frontIsBlocked()){                               // at last cell before top of the column
            if(!beepersPresent()){                          // if a cell is empty
                putBeeper();                                // put beeper
            }
        }
    }

    /**
     * Goes to the next column.
     * If Karel is at the floor, he must turn at left, makes four steps and turns left again
     * to be on the next column. And conversely. If Karel is at the ceiling, he must turn at right, makes four steps
     * and turns right again to be on the next column.
     */
    private void goToNextColumn() throws Exception {
        turnBack();                                         // look South

        while (frontIsClear()){
            move();                                         // move to southern border
        }

        turnLeft();                                         // look East
        makeFourSteps();                                    // go to the next column
        turnLeft();                                         // look North (ready to fill new column)
    }

    /**
     * Initialize Karel.
     * Karel must say "Hello. I`m ready!" (switch on robot).
     * Turn at North.
     */
    private void initialize() throws Exception {
        say("Hello. I`m ready to work!");// switch on robot
        while (!facingNorth()){
            turnLeft();                                     // turn until not looking at North
        }
    }

    /**
     * Makes four steps.
     */
    private void makeFourSteps() throws Exception {
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

    /**
     * Turn right.
     */
    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }
}
