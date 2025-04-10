package miem.projects.vulnerabilities.MAJOR.FB;

public class SS_SHOULD_BE_STATIC {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class InnerClass {
            // Поле экземпляра
            private final String staticValue = "value";

            public void printValue() {
                System.out.println(staticValue);
            }
        }

        InnerClass inner = new InnerClass();
        inner.printValue();
    }

    public static void correctTest() {
        class InnerClass {
            // Статическое поле
            private static final String staticValue = "value";

            public void printValue() {
                System.out.println(staticValue);
            }
        }

        InnerClass inner = new InnerClass();
        inner.printValue();
    }
}
