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

    //TODO header + comments
    private void moveIfForwardFree() throws Exception {
        while (frontIsBlocked()) {
            turnRight();
        }
        move();
    }

    //TODO header + comments
    private void moveForNewspaper() throws Exception {

        karelInit();

        while (noBeepersPresent() && leftIsBlocked()) {
            moveIfForwardFree();
        }
    }

    //TODO header + comments
    private void returnToStartPosition() throws Exception {
        while (noBeepersPresent()) {
            moveIfForwardFree();
        }

        pickBeeper();
        say("Job is finished!");
    }

    //TODO header + comments
    private void takeTheNewspaper() throws Exception {

        turnLeft();

        while (noBeepersPresent()){
            move();
        }

        pickBeeper();
        turnBack();

        while (leftIsClear() && rightIsClear()){
            move();
        }

        move();
        turnLeft();
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
