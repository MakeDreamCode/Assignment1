package com.shpp.p2p.cs.oklymenchuk.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Task 1. Pickup the newspaper.
 * Algorithm for solving this problem is a simply connected maze.
 */
public class Assignment1Part1 extends KarelTheRobot {

    /**
     * Added new comments.
     * Runs the program. Must contains ONLY free methods.
     * goForTheNewspaper() - Initialize Karel. Karel goes to a newspaper.
     * pickupTheNewspaper() - Karel pickups the newspaper.
     * backToStartWithTheNewspaper() - Karel returns to the start position with the newspaper.
     */
    public void run() throws Exception {
       goForTheNewspaper();
       pickupTheNewspaper();
       backToStartWithTheNewspaper();
    }

    /**
     * Calculates a direction for nex step.
     * Method checks left side of Karel and keeps Karel against the left wall
     * for left-hand traffic.
     */
    private void calculateDirectionForNextStep() throws Exception {
        if (leftIsClear()){                                 // if the left is clear, it means that Karel has reached the turn
            turnLeft();                                     // and need to turn left
        }
        while (frontIsBlocked()) {                          // if the front is blocked Karel is looking for a free direction.
                                                            // The wall should be on the left.
            if(leftIsBlocked() && rightIsBlocked()){        // if Karel is at a dead end
                turnBack();                                 // reversal for exit.
            }
            if (leftIsBlocked() && rightIsClear()) {        // if Karel is in the corner
                turnRight();                                // must turn for exit
            }
        }
    }

    /**
     * Moves Karel while Karel will not found the beeper.
     */
    private void go() throws Exception {
        while (noBeepersPresent()){                         // beeper not found
            oneStepForward();                               // make one step forward
        }
    }

    /**
     *Initializes and moves Karel to the newspaper.
     */
    private void goForTheNewspaper() throws Exception {
        initialize();                                       // initialize Karel
        go();                                               // move to the newspaper
    }

    /**
     * Initialize Karel.
     * Karel must say "Hello. I`m ready!" (switch on robot).
     * Mark the place by installing a beeper to return.
     * Turn around so that the wall is on the left side.
     * Make one step forward for get off from the beeper.
     */
    private void initialize() throws Exception {
        say("Hello. I`m ready!");        // switch on robot
        putBeeper();
        while(leftIsClear()){
            turnLeft();                                     // turn left until a wall is on the left side
        }
        oneStepForward();                                   // get off from the beeper
    }

    /**
     * Makes one step forward.
     */
    private void backToStartWithTheNewspaper() throws Exception {
        turnBack();                                         // turn back
        move();                                             // back to the door (wall must be at left side)
        go();                                               // move to the start beeper
        pickBeeper();                                       // pickup the start beeper
        say("Job`s done!");
    }

    /**
     * Makes one step forward.
     */
    public void oneStepForward() throws Exception {
        calculateDirectionForNextStep();                    // looking for the right direction of movement
        move();                                             // make one step forward
    }

    /**
     * Pickups the newspaper.
     */
    private void pickupTheNewspaper() throws Exception {
        say("The Newspaper is found!");
        pickBeeper();
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
