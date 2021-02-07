package com.shpp.p2p.cs.oklymenchuk.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Task 4. Make a chessboard.
 * At a number of employees Karel was instructed to set up a chessboard behind
 * the auxiliary beeper in a rectangular (NOT square!) empty world (the final position and Karel is not important).
 * It is important that one of the set beepers is located at the backward-facing corner of the world.
 * Karel always starts from the South-West corner looking East. The world is clear from walls and beepers.
 *
 * Algorithm for solving the problem:
 * 1. Fill the entire field with beepers as a result of a spiral movement (took from Assignment1.Part3).
 * 2. Descend normal to the south line and return to the start position (also took from Assignment1.Part3).
 * 3. Remove beepers through one.
 */
public class Assignment1Part4 extends KarelTheRobot {
    public void run() throws Exception {
        initialize();                                                   // switch on robot and look East

        if (frontIsBlocked() && leftIsBlocked() && rightIsBlocked()) {  // check the surface for 1x1
            say("Block 1х1");
            putBeeper();                                                // only put a beeper
        }

        if(frontIsBlocked() && rightIsBlocked() && leftIsClear()){      // check the surface for Nx1
            say("Block Nх1");
            turnLeft();                                                 // turn North
            fillTheLineWithBeepers();                                   // fill the line with beepers
            returnToStartPosition();                                    // return to start
            turnLeft();                                                 // turn North
            clearBeepersThroughOne();                                   // clear beepers through one
        }

        if(frontIsClear() && leftIsBlocked() && rightIsBlocked()){      // check the surface for 1xN
            say("Block 1хN");
            fillTheLineWithBeepers();                                   // fill the line with beepers
            returnToStartPosition();                                    // return to start
            clearBeepersThroughOne();                                   // clear beepers through one
        }

        if(frontIsClear() && leftIsClear()) {                           //check the surface for 2х2 or higher
            say("Block 2х2 or higher");
            fillEverythingWithBeepers();                                // fill all cells with beepers (normal mode)
            returnToStartPosition();                                    // return to start
            clearBeepersThroughOne();                                   // clear beepers through one
        }

        say("Job`s done!");
    }

    /**
     * Back to previous cell and turn back.
     */
    private void backToPreviousPosition() throws Exception {
        turnBack();
        move();
        turnBack();
    }

    /**
     * Clear beepers through one.
     */
    private void clearBeepersThroughOne() throws Exception {
        while (frontIsClear()) {                            // while front is clear

            moveOneStepForwardAndPickUpBeeper();            // move one step forward and pickup a beeper

            if (frontIsClear()) {                           // check the border. If the next cell is free
                move();                                     // move one step forward
            }

            if (frontIsBlocked() && facingEast()) {         // if the border is reached and the border is East

                turnLeft();                                 // turn North

                if (frontIsClear()) {                       // if the North border is not reached

                    if(!beepersPresent()){                  // and a beeper is present
                        move();                             // move to the next cell
                        turnLeft();                         // and turn West
                    } else {                                // or a beeper is not preset
                        moveOneStepForwardAndPickUpBeeper();// move one step forward and pickup a beeper
                        turnLeft();                         // and turn West
                        move();                             // move to the next cell
                    }
                }
            }

            if (frontIsBlocked() && facingWest()) {         // if the border is reached and the border is West

                turnRight();                                // turn North

                if (frontIsClear()) {                       // if the North border is not reached

                    if(!beepersPresent()){                  // and a beeper is present
                        move();                             // move to the next cell
                        turnRight();                        // and turn East
                    }else {                                 // or a beeper is not preset
                        moveOneStepForwardAndPickUpBeeper();// move one step forward and pickup a beeper
                        turnRight();                        // and turn East
                        move();                             // move to the next cell
                    }
                }
            }
        }
    }

    /**
     * Puts a beeper in each cell.
     * Movement and puts beepers - along the perimeter of the rectangle.
     * <-<-<-
     * |    ^
     * v    |
     * ->->->
     * When the square is complete, Karel moves to the inner rectangle.
     * 1 1 1 1 1
     * 1       1
     * 1       1
     * 1->     1
     * 1 1 1 1 1
     * Karel moves until he is in the center(or one of two) cell surrounded by beepers.
     * 1 1 1 1 1
     * 1 1 1 1 1
     * 1 1-> 1 1
     * 1 1 1 1 1
     * 1 1 1 1 1
     * And puts the last beeper in a centre cell (central column is found (or one of two))
     */
    private void fillEverythingWithBeepers() throws Exception {
        while (!beepersPresent()) {
            moveOneStepForwardAndPutBeeper();               // until the beeper is not found fill empty cells with beepers
            if (beepersPresent()) {                         // If a beeper is found, the square is closed. You need to go to the internal one.
                backToPreviousPosition();                   // back to the previous cell
                takeNewDirection();                         // go to the inner square
            }
        }
    }

    /**
     * Fills the line with beepers.
     * Method for working with surfaces 1 x N or N x 1.
     */
    private void fillTheLineWithBeepers() throws Exception {
        while (frontIsClear()){                             // while a border is not reached
            putBeeper();                                    // put a beeper
            move();                                         // move one step forward
            if(frontIsBlocked()){                           // if a border is reached
                putBeeper();                                // put a beeper (in the last cell)
            }
        }
    }

    /**
     * Initialize Karel.
     * Karel must say "Hello. I`m ready!" (switch on robot).
     * Turn East.
     */
    private void initialize() throws Exception {
        say("Hello. I`m ready to work!");// switch on robot
        while (!facingEast()){
            turnLeft();                                     // turn until not looking East
        }
    }

    /**
     * Moves one step forward and pickup beeper.
     */
    private void moveOneStepForwardAndPickUpBeeper() throws Exception {
        move();
        pickBeeper();
    }

    /**
     * Moves and puts beepers along the side of the rectangle.
     */
    private void moveOneStepForwardAndPutBeeper() throws Exception {
        if(frontIsBlocked()){                               // if
            turnLeft();                                     // Karel moves
        }                                                   // along the border of the world
        putBeeper();                                        // put a beeper
        move();                                             // move to the next cell
    }

    /**
     * Moves while the front is clear (movement to the border).
     */
    private void moveWhileFrontIsClear() throws Exception {
        while (frontIsClear()){
            move();                                         // moving until the front is clear
        }
    }

    /**
     * Returns to start position.
     */
    private void returnToStartPosition() throws Exception {
        turnSouth();                                        // turn South
        moveWhileFrontIsClear();                            // move to the southern border
        turnRight();                                        // turn East
        moveWhileFrontIsClear();                            // move to the western border
        turnBack();                                         // turn back for start of cleaning beepers
    }

    /**
     * Takes a new direction. To move to the inner square.
     */
    private void takeNewDirection() throws Exception {
        turnLeft();
        move();
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

    /**
     * Turn South.
     */
    private void turnSouth() throws Exception {
        while (!facingSouth()){
            turnLeft();                                     // turn left until look South
        }
    }
}
