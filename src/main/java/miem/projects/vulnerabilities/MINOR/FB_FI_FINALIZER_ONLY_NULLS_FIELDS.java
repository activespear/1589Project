package miem.projects.vulnerabilities.MINOR.FB;

public class FB_FI_FINALIZER_ONLY_NULLS_FIELDS {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass {
            private String field = "data";

            @Override
            protected void finalize() throws Throwable {
                if (field == null) {
                    // Ничего не делаем — finalize бесполезен
                }
                super.finalize();
            }
        }

        MyClass obj = new MyClass();
        obj = null; // Объект готов к сборке мусора
        System.gc();
        System.out.println("Incorrect finalize used (does nothing meaningful)");
    }

    public static void correctTest() {
        class MyClass {
            private String field = "data";

            // finalize удален, так как он не нужен
            // Либо выполнять реальные действия очистки ресурсов
        }

        MyClass obj = new MyClass();
        obj = null; // Объект готов к сборке мусора
        System.gc();
        System.out.println("No finalize — safe and clean");
    }
}
