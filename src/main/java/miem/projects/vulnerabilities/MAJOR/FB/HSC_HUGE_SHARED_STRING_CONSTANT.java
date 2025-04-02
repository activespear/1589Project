package miem.projects.vulnerabilities.MAJOR.FB;

public class HSC_HUGE_SHARED_STRING_CONSTANT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class A {
            public static final String HUGE_STRING = "Очень длинная строка... (2+ KB)";
            public static String getString() {
                return HUGE_STRING;
            }
        }
        class B {
            public static final String HUGE_STRING = "Очень длинная строка... (2+ KB)";
            public static String getString() {
                return HUGE_STRING;
            }
        }
        String s1 = A.getString();
        String s2 = B.getString();
    }

    public static void correctTest() {
        class Constants {
            public static final String HUGE_STRING = "Очень длинная строка... (2+ KB)";
        }

        class A {
            // ...
            public static String getString() {
                return Constants.HUGE_STRING;
            }
        }
        class B {
            // ...
            public static String getString() {
                return Constants.HUGE_STRING;
            }
        }
        String s1 = A.getString();
        String s2 = B.getString();
    }
}
