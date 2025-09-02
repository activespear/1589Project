package miem.projects.vulnerabilities.NORMAL;

public class SHADOWED_NAME {
    public static void main(String[] args) {
        incorrectExample();
        correctExample();
    }

    // ❌ Потенциально небезопасное: имена переменных и полей перекрываются (shadowing)
    static class IncorrectExample {
        private final int someInt = 42;

        public void doSmth1(int someInt) { // SHADOWED_NAME: перекрытие поля
            System.out.println(someInt);
        }

        public void doSmth() {
            int someInt = 123; // SHADOWED_NAME: перекрытие поля
            System.out.println(someInt);
        }

        private class NestedClass {
            private final int someInt = 99; // SHADOWED_NAME: перекрытие поля внешнего класса
        }
    }

    // ✅ Корректная конструкция: уникальные имена, исключено shadowing
    static class CorrectExample {
        private final int someInt;

        public CorrectExample(int someInt) {
            this.someInt = someInt; // явное присваивание через this
        }

        public void doSmth1(int value) { // уникальное имя параметра
            System.out.println(value);
        }

        public void doSmth() {
            int localSomeInt = 123; // уникальное имя локальной переменной
            System.out.println(localSomeInt);
        }

        private class NestedClass {
            private final int nestedSomeInt;

            public NestedClass(int nestedSomeInt) {
                this.nestedSomeInt = nestedSomeInt;
            }
        }
    }

    public static void incorrectExample() {
        IncorrectExample ex = new IncorrectExample();
        ex.doSmth1(10);
        ex.doSmth();
    }

    public static void correctExample() {
        CorrectExample ex = new CorrectExample(42);
        ex.doSmth1(10);
        ex.doSmth();
    }
}
