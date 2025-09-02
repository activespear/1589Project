package miem.projects.vulnerabilities.NORMAL;

public class UNUSED_VALUE {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.compute(2, 3));
    }

    // Потенциально небезопасное: лишнее присваивание, которое перезаписывается
    static class Calculator {
        public int computeUnsafe(int a, int b) {
            int result = a + b;  // Это значение никогда не используется
            result = a * b;      // Перезаписывается перед использованием
            return result;
        }

        // Корректная конструкция: нет лишнего присваивания
        public int compute(int a, int b) {
            int result = a * b;
            return result;
        }
    }
}
