package miem.projects.vulnerabilities.MAJOR.FB;

public class SA_FIELD_SELF_COMPARISON {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class A {
            private String str;
            public boolean method() {
                // всегда true:
                return str.equals(str);
            }
        }
    }

    public static void correctTest() {
        class A {
            private String str;
            public boolean method(String otherStr) {
                // всегда true:
                return str.equals(otherStr);
            }
        }
    }
}