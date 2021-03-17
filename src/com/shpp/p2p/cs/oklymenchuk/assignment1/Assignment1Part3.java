package com.shpp.p2p.cs.oklymenchuk.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Task 3. Finding a middle.
 */
public class Assignment1Part3 extends KarelTheRobot{

    public void run() throws Exception {

        karelInit();

        if (frontIsBlocked()){                              // if has 1x1
            putBeeper();
        } else {                                            // else has 2х2 or above
            while (frontIsClear()) {                        // fill the bottom row with beepers
                putBeeper();
                move();

                if (frontIsBlocked()) {
                    putBeeper();
                }
            }

            turnBack();                                     // return to starting position

            while (frontIsClear()) {
                move();
            }

            turnBack();

            while (beepersPresent() && frontIsClear()) {    // main cycle of removing beepers
                move();

                if (frontIsBlocked() && beepersPresent()) { // remove beepers from the walls of the map
                    pickBeeper();
                    stepBack();
                }

                if (!beepersPresent()) {                    // remove beepers from the edges of the "beeper line"
                    stepBack();                             // no beeper? turn and return two cells back
                    move();

                    if (beepersPresent()) {                 // if the cell next to last from the "edge" has a beeper
                        stepBack();                         // turn around and go to the last cell with a beeper
                        pickBeeper();                       // remove a beeper
                        stepBack();                         // turn around and go to the new cycle
                    }
                }
            }
        }

        say("Job is finished!");
    }

    /**
     * Initialize Karel.
     * Karel must say "Hello. Ready to work." (switch on robot).
     */
    private void karelInit() throws Exception {
        say("Hello. Ready to work.");
    }

    /**
     * Makes one step back.
     */
    private void stepBack() throws Exception {
        turnBack();
        move();
    }

    /**
     * Turn back.
     */
    private void turnBack() throws Exception {
        turnLeft();
        turnLeft();
    }
}
