package miem.projects.vulnerabilities.MAJOR.FB;

public class MF_CLASS_MASKS_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Parent {
            protected int value = 10;
        }

        class Child extends Parent {
            // Маскировка поля суперкласса
            protected int value = 20;

            public void printValues() {
                System.out.println("Child value: " + value);
                System.out.println("Parent value: " + super.value);
            }
        }

        Child child = new Child();
        child.printValues();
    }

    public static void correctTest() {
        class Parent {
            protected int value = 10;
        }

        class Child extends Parent {
            // Используется унаследованное поле
            public void printValues() {
                System.out.println("Value: " + value);
            }
        }

        Child child = new Child();
        child.printValues();
    }
}