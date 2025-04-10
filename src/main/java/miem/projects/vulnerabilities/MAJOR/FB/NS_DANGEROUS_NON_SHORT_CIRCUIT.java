package miem.projects.vulnerabilities.MAJOR.FB;

public class NS_DANGEROUS_NON_SHORT_CIRCUIT {
    public static void main(String[] args) {
        incorrectTest(5);
        correctTest(5);
    }

    public static void incorrectTest(int x) {
        // Не короткая логика с побочным эффектом
        if (x > 0 & expensiveOperation()) {
            System.out.println("Condition met!");
        }
    }

    public static void correctTest(int x) {
        // Короткая логика: правая часть не оценивается, если левая уже ложна
        if (x > 0 && expensiveOperation()) {
            System.out.println("Condition met!");
        }
    }

    public static boolean expensiveOperation() {
        // Например, это может быть запрос к базе данных или сложная вычислительная операция
        return true;
    }
}