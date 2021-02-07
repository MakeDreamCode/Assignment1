package com.shpp.p2p.cs.oklymenchuk.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Task 3. Finding a middle.
 * Karel starts in the southwest corner and looks to the east, he is full of beavers in his backpack.
 * There are no beavers or walls in the world.
 * The world may not be square, but it is at least as tall as it is wide.
 * If the width of the world is odd, then Karel needs to put a beeper in the central cell,
 * otherwise you need to put in one of the two central cells.
 * It doesn't matter where Karel looks after he finishes his race.
 *
 * Algorithm for solving the problem:
 * 1. Fill the entire field with beepers as a result of a spiral movement. The last free cell is on the normal
 * that passes through the center of the south line.
 * 2. Descend normal to the south line, put second beeper and return to the start position.
 * 3. Remove all beepers.
 */
public class Assignment1Part3 extends KarelTheRobot{

    public void run() throws Exception {
        initialize();                                       // switch on robot and look East

        if (frontIsBlocked()) {                             // block 1х1
            say("Block 1х1");
            putBeeper();
        } else {                                            // normal mode
            say("Block 2х2 or higher");
            fillEverythingWithBeepers();                    //fill all cells beepers
            returnToStartPositionAndPutBeeper();            // put second beeper and return to start(middle point has two beepers)
            clearAllBeepers();                              // remove all beepers
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
     * Clear all beepers.
     * Karel moves from West (start position) to East and pickups beepers.
     * After the border is reached Karel goes at next line above,
     * turns West and moves and pickups beepers to next border (West).
     * <-<-<-<-
     * ->->->->
     */
    private void clearAllBeepers() throws Exception {
        while (frontIsClear()) {

            pickBeeper();                                   // pickup a beeper if the front is clear

            if (frontIsClear()) {
                move();                                     // if the next cell is free, move to the next cell
            }

            if (frontIsBlocked() && facingEast()) {

                if (beepersPresent()) {
                    pickBeeper();                           // pickup a beeper if the last cell at East border has a beeper
                }

                    turnLeft();                             // look North

                if (frontIsClear()) {
                    move();                                 // move at the next free cell
                    turnLeft();                             // look West
                }
            }

            if (frontIsBlocked() && facingWest()) {

                if(beepersPresent()){
                    pickBeeper();                           // pickup a beeper if the last cell at West border has a beeper
                }

                turnRight();                                // look North

                if (frontIsClear()) {
                    move();                                 // move at the next free cell
                    turnRight();                            // look East
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
     * Initialize Karel.
     * Karel must say "Hello. I`m ready!" (switch on robot).
     * Turn East.
     */
    private void initialize() throws Exception {
        say("Hello. I`m ready to work!"); // switch on robot
        while (!facingEast()){
            turnLeft();                                     // turn until not looking East
        }
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
     * Returns to start position and puts second beeper at the middle of south line.
     */
    private void returnToStartPositionAndPutBeeper() throws Exception {
        turnToSouth();                                      // turn South
        moveWhileFrontIsClear();                            // move to the southern border
        putBeeper();                                        // put second beeper
        turnRight();                                        // turn West
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
    private void turnToSouth() throws Exception {
        while (!facingSouth()){
            turnLeft();                                     // turn left until look South
        }
    }
}
