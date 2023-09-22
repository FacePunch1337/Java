package step.learning.basics;

import java.util.Random;

public class Dice {
    public Random random;
    public Dice() {
        random = new Random();
    }

    public int roll() {
        return random.nextInt(6) + 1;

    }
}
