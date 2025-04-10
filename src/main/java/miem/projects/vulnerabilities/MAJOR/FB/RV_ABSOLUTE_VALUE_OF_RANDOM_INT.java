package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.Random;

public class RV_ABSOLUTE_VALUE_OF_RANDOM_INT {
    public static void main(String[] args) {
        incorrectTest(new Random());
        correctTest(new Random());
    }

    public static void incorrectTest(Random random) {
        int number = random.nextInt();
        // Возможен Integer.MIN_VALUE:
        int absNumber = Math.abs(number);
    }

    public static void correctTest(Random random) {
        int number = random.nextInt();
        int absNumber = (number == Integer.MIN_VALUE) ? 0 : Math.abs(number);
    }
}