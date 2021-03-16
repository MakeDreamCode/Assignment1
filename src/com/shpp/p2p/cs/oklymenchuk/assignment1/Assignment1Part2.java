package com.shpp.p2p.cs.oklymenchuk.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part2 extends KarelTheRobot{

    public void run() throws Exception {

    	karelInit();

    	while (facingNorth()) {


			// положить бипер на случай, если колонна имеет высоту = 1
			if (!beepersPresent()) {
				putBeeper();
			}

			// идем до верха колонны и докладываем биперы
			while (frontIsClear()) {

				move();

				if (!beepersPresent()) {
					putBeeper();
				}
			}

			// достигнув верха разворачиваемся и идем вниз
			turnBack();

			// идем до основания колонны
			while (frontIsClear()) {
				move();
			}

			// дошли до основания и проверяем последняя ли колонна?

			if (leftIsClear()){
				// нет - поворачиваем на лево
				turnLeft();
				// и бежим через 4 клетки
				makeFoursSteps();
				turnLeft();
			}
		}

    	say("Job is finished!");
    }

	private void makeFoursSteps() throws Exception {
		for (int i = 0; i < 4; i++) {
			move();
		}
	}

	/**
	 * Initialize Karel.
	 * Karel must say "Hello. Ready to work." (switch on robot).
	 * Mark the place by installing a beeper to return.
	 * Make one step forward for get off from the beeper.
	 */
	private void karelInit() throws Exception {
		say("Hello. Ready to work.");
		// повернуться на север
		while (!facingNorth()) {
			turnLeft();
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
