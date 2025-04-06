package miem.projects.vulnerabilities.NORMAL;

public class DEREF_OF_NULL_RET {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String result = mayReturnNull();
        // Попытка разыменования без проверки
        System.out.println("Length: " + result.length());
    }

    public static void correctTest() {
        String result = mayReturnNull();
        if (result != null) {
            System.out.println("Length: " + result.length());
        } else {
            System.out.println("Result is null");
        }
    }

    public static String mayReturnNull() {
        return Math.random() > 0.5 ? "Hello" : null;
    }
}