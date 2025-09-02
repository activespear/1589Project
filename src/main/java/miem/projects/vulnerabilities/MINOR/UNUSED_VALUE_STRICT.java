package miem.projects.vulnerabilities.STRICT;

public class UNUSED_VALUE_STRICT {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.compute(3, 5));
    }

    // Потенциально небезопасное: лишнее присваивание, которое перезаписывается
    static class Calculator {
        public int computeUnsafe(int x, int y) {
            int result = x * 2;  // Результат вычисления никогда не используется
            result = x + y;      // Перезапись до первого чтения
            return result;
        }

        // Корректная конструкция: нет лишних операций
        public int compute(int x, int y) {
            int result = x + y;
            return result;
        }
    }
}
