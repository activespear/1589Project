package miem.projects.vulnerabilities.MINOR.FB;

public class FB_BIT_AND {

    // Потенциально небезопасное использование побитового AND
    static class ExampleUnsafe {
        public void checkFlags() {
            int x = getFlags();
            if ((x & 0x08) == 0x10) {
                doSomething();
            }
        }

        private int getFlags() {
            return 0x08; //
        }

        private void doSomething() {
            System.out.println("Unsafe action executed");
        }
    }

    // Корректная конструкция
    static class ExampleSafe {
        public void checkFlags() {
            int x = getFlags();
            if ((x & 0x08) != 0) {
                doSomething();
            }
        }

        private int getFlags() {
            return 0x08; // Пример возвращаемого флага
        }

        private void doSomething() {
            System.out.println("Safe action executed");
        }
    }

    public static void main(String[] args) {
        ExampleSafe safe = new ExampleSafe();
        safe.checkFlags();
    }
}
