package miem.projects.vulnerabilities.MAJOR.FB;

public class DLS_DEAD_LOCAL_STORE_IN_RETURN {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass {
            int computeValue() {
                int x = 10;
                // не имеет эффекта
                return (x = 20);
            }
        }

        MyClass myClass = new MyClass();
        System.out.println(myClass.computeValue());
    }

    public static void correctTest() {
        class MyClass {
            int computeValue() {
                int x = 10;
                x = 20;
                return x;
            }
        }

        MyClass myClass = new MyClass();
        System.out.println(myClass.computeValue());
    }
}