package miem.projects.vulnerabilities.NORMAL;

public class PASS_TO_PROC_AFTER_CHECK {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int value = getValueFromInput();

        if (value < 0 || value > 100) {
            System.out.println("Invalid value in incorrectTest: " + value);
        }
        // Передача значения в функцию без дополнительной проверки
        processValue(value);
    }

    public static void correctTest() {
        int value = getValueFromInput();

        if (value < 0 || value > 100) {
            System.out.println("Invalid value in correctTest: " + value);
            return;
        }
        // Передача значения в функцию только после проверки
        processValue(value);
    }

    public static int getValueFromInput() {
        return 120;
    }

    public static void processValue(int value) {
        System.out.println("Processing value: " + value);
    }
}