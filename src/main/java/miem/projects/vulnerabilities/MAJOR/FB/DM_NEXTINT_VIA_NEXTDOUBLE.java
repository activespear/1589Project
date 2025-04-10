package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.Random;

public class DM_NEXTINT_VIA_NEXTDOUBLE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        Random random = new Random();
        int randomNumber = (int) (random.nextDouble() * 100);
        System.out.println("Random number: " + randomNumber);
    }

    public static void correctTest() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        System.out.println("Random number: " + randomNumber);
    }
}
