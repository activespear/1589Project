package miem.projects.vulnerabilities.MAJOR.FB;

public class INT_BAD_COMPARISON_WITH_SIGNED_BYTE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        byte value = 100;
        if (value > 128) {
            // ...
        }
    }

    public static void correctTest() {
        byte value = 100;
        // Преобразование в беззнаковый int
        int unsignedValue = value & 0xff;
        if (unsignedValue > 128) {
            // ...
        }
    }
}