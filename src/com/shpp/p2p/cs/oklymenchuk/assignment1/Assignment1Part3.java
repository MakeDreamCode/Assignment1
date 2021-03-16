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

 */
public class Assignment1Part3 extends KarelTheRobot{

    public void run() throws Exception {

        karelInit();

        // для 1хч

        if (frontIsBlocked()){
            putBeeper();
        } else {

            // заполнить нижний ряд биперами
            while (frontIsClear()) {
                putBeeper();
                move();

                if (frontIsBlocked()) {
                    putBeeper();
                }
            }

            //вернуться в начало
            turnBack();
            while (frontIsClear()) {
                move();
            }
            turnBack();


            while (beepersPresent() && frontIsClear()) {

                move();

                // удаляем биперы с краев
                if (frontIsBlocked() && beepersPresent()) {
                    pickBeeper();
                    turnBack();
                    move();
                }

                // удаляем биперы с краев
                if (!beepersPresent()) {

                    //нет бипера? разворот и возврат на две клетки назад
                    turnBack();
                    move();
                    move();

                    // если в предпоследней от "края" клетке есть бипер
                    if (beepersPresent()) {
                        // развернулись и перешли в последнюю клетку с бипером
                        turnBack();
                        move();
                        // подняли бипер, развернулись и перешли на "теперь уже последнюю" клетку с бипером
                        pickBeeper();
                        turnBack();
                        move();
                    }
                }
            }
        }

        /*turnBack();
        move();*/
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
     * Turn back.
     */
    private void turnBack() throws Exception {
        turnLeft();
        turnLeft();
    }
}
