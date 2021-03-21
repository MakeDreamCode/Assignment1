package com.shpp.p2p.cs.oklymenchuk.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Task 1. Pickup the newspaper.
 * Algorithm for solving this problem is a simply connected maze.
 */
public class Assignment1Part1 extends KarelTheRobot {

    /**
     * Runs the program. Must contains ONLY free methods.
     * moveForNewspaper() - Initialize Karel. Karel goes to a newspaper.
     * takeTheNewspaper() - Karel pickups the newspaper.
     * returnToStartPosition() - Karel returns to the start position with the newspaper.
     */
    public void run() throws Exception {
        moveForNewspaper();
        takeTheNewspaper();
        returnToStartPosition();
    }

    /**
     * Initialize Karel.
     * Karel must say "Hello. Ready to work." (switch on robot).
     * Mark the place by installing a beeper to return.
     * Make one step forward for get off from the beeper.
     */
    private void karelInit() throws Exception {
        say("Hello. Ready to work.");
        putBeeper();
        move();
    }

    /**
     * Karel moves if front is free.
     * When front will blocked (Karel reached a corner),
     * Karel must turn left for keep moving.
     */
    private void moveIfForwardFree() throws Exception {
        while (frontIsBlocked()) {
            turnRight();
        }
        move();
    }

    /**
     * First main method.
     * Init Karel. Karel goes to the door, holding on to the left wall.
     * If there is no wall, this is a door.
     */
    private void moveForNewspaper() throws Exception {

        karelInit();

        while (noBeepersPresent() && leftIsBlocked()) { // holding on to the left wall
            moveIfForwardFree();                        // Karel goes to a next corner
        }
    }

    /**
     * Third main method.
     * Karel walks until he finds a beeper.
     * If a beeper is found, then Karel has returned to the starting position.
     * Karel raises the beeper and says "Job is finished!".
     */
    private void returnToStartPosition() throws Exception {
        while (noBeepersPresent()) {
            moveIfForwardFree();
        }

        pickBeeper();
        say("Job is finished!");
    }

    /**
     * Second main method.
     * Karel leaves the house, takes a newspaper and returns to the house.
     */
    private void takeTheNewspaper() throws Exception {

        turnLeft();                                 // Karel reached the door and turned left

        while (noBeepersPresent()){                 // moving until it reaches the newspaper
            move();
        }

        pickBeeper();                               // pickup the newspaper
        turnBack();                                 // and turn back

        while (leftIsClear() && rightIsClear()){    // return to the door
            move();
        }

        move();                                     // enter the house
        turnLeft();                                 // turn left to start moving at the start position
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
