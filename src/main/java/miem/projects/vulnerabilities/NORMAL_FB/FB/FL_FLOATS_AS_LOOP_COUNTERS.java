package miem.projects.vulnerabilities.NORMAL.FB;

public class FL_FLOATS_AS_LOOP_COUNTERS {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        for (float x = 0.1f; x != 1.0f; x += 0.1f) {
            System.out.println(x);
        }
    }

    public static void correctTest() {
        for (int i = 1; i <= 10; i++) {
            float x = i * 0.1f;
            System.out.println(x);
        }
    }
}

