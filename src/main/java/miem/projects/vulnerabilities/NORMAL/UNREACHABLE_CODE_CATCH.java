package miem.projects.vulnerabilities.NORMAL;

public class UNREACHABLE_CODE_CATCH {
    public static void main(String[] args) {
        incorrectExample();
        correctExample();
    }

    // ❌ Потенциально небезопасное: блок catch недостижим
    abstract static class IncorrectExample {
        static class MyException extends Exception {}
        static class SubException extends MyException {}

        public abstract void couldEmitDerived() throws SubException;

        public void example() {
            try {
                couldEmitDerived();
            } catch (SubException e) {
                System.err.println("SubException");
            } catch (MyException e) { // ❌ Никогда не выполняется, т.к. SubException уже перехватывается выше
                System.err.println("MyException");
            }
        }
    }

    // ✅ Корректная конструкция: порядок исключений правильный
    static class CorrectExample {
        public void method() {
            try {
                int a = 5 / 0; // Искусственное исключение
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void incorrectExample() {
        System.out.println("Запуск некорректного примера...");
        IncorrectExample ex = new IncorrectExample() {
            @Override
            public void couldEmitDerived() throws SubException {
                throw new SubException();
            }
        };
        ex.example();
    }

    public static void correctExample() {
        System.out.println("Запуск корректного примера...");
        CorrectExample ex = new CorrectExample();
        ex.method();
    }
}