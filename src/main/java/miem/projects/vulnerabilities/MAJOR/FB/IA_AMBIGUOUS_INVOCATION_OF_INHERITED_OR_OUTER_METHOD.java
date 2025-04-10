package miem.projects.vulnerabilities.MAJOR.FB;

public class IA_AMBIGUOUS_INVOCATION_OF_INHERITED_OR_OUTER_METHOD {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Outer {
            public void foo(int x) {
                System.out.println("Внешний класс, foo: " + x);
            }
        }

        class Inner extends Outer {
            public void foo(int x) {
                System.out.println("Внутренний класс, foo: " + x);
            }

            public void callFoo(int x) {
                // Неоднозначный вызов (может вызвать метод как из внешнего, так и из внутреннего класса)
                foo(x);
            }
        }

        Inner inner = new Inner();
        inner.callFoo(17); // вызов может быть неоднозначным
    }

    public static void correctTest() {
        class Outer {
            public void foo(int x) {
                System.out.println("Внешний класс, foo: " + x);
            }
        }

        class Inner extends Outer {
            @Override
            public void foo(int x) {
                System.out.println("Внутренний класс, foo: " + x);
            }

            public void callFoo(int x) {
                super.foo(x); // Явное указание на вызов метода внешнего класса
            }
        }

        Inner inner = new Inner();
        inner.callFoo(17);
    }
}