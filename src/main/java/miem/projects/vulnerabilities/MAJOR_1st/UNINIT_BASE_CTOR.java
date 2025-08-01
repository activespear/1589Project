package miem.projects.vulnerabilities.MAJOR_1st;

public class UNINIT_BASE_CTOR {

    // Небезопасная версия — вызов виртуального метода в конструкторе базового класса
    static class BaseUnsafe {
        protected String message;

        public BaseUnsafe() {
            printMessage();
        }

        public void printMessage() {
            System.out.println(message);
        }
    }

    static class DerivedUnsafe extends BaseUnsafe {
        public DerivedUnsafe() {
            this.message = "Hello from DerivedUnsafe";
        }

        @Override
        public void printMessage() {
            System.out.println("DerivedUnsafe: " + message);
        }
    }

    // Безопасная версия — разделение инициализации и вызова виртуального метода
    static class BaseSafe {
        protected String message;

        public BaseSafe() {
            initialize();
        }

        public void initialize() {
            printMessage();
        }

        public void printMessage() {
            System.out.println(message);
        }
    }

    static class DerivedSafe extends BaseSafe {
        public DerivedSafe() {
            this.message = "Hello from DerivedSafe";
            super.initialize();
        }

        @Override
        public void printMessage() {
            System.out.println("DerivedSafe: " + message);
        }
    }

    public static void runUnsafe() {
        System.out.println("Running unsafe version:");
        DerivedUnsafe du = new DerivedUnsafe();
        du.printMessage();
    }

    public static void runSafe() {
        System.out.println("Running safe version:");
        DerivedSafe ds = new DerivedSafe();
        ds.printMessage();
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}
