package miem.projects.vulnerabilities.MINOR.FB;

public class FB_FI_MISSING_SUPER_CALL {

    static class ParentClass {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("ParentClass finalize called");
            super.finalize();
        }
    }

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass extends ParentClass {
            @Override
            protected void finalize() throws Throwable {
                // Не вызывает super.finalize(), пропуская действия суперкласса
                System.out.println("MyClass finalize called (INSECURE)");
            }
        }

        MyClass obj = new MyClass();
        obj = null;
        System.gc();
        System.out.println("Incorrect finalize executed");
    }

    public static void correctTest() {
        class MyClass extends ParentClass {
            @Override
            protected void finalize() throws Throwable {
                try {
                    System.out.println("MyClass finalize logic");
                } finally {
                    super.finalize(); // Обязательно вызываем super.finalize()
                }
            }
        }

        MyClass obj = new MyClass();
        obj = null;
        System.gc();
        System.out.println("Correct finalize executed with super call");
    }
}
