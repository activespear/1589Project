package miem.projects.vulnerabilities.MAJOR.FB;

public class BC_VACUOUS_INSTANCEOF {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass {
            void checkInstance(Object obj) {
                // всегда истинна, если obj не null
                if (obj instanceof Object) {
                    System.out.println("...");
                }
            }
        }

        MyClass myClass = new MyClass();
        myClass.checkInstance(new String("Hello"));
        myClass.checkInstance(null); // null не пройдет проверку
    }

    public static void correctTest() {
        class MyClass {
            void checkInstance(Object obj) {
                // явная проверка на null
                if (obj != null) {
                    System.out.println("obj не равен null");
                }
            }
        }

        MyClass myClass = new MyClass();
        myClass.checkInstance(new String("Hello"));
        myClass.checkInstance(null);
    }
}

