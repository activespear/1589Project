package miem.projects.vulnerabilities.MAJOR.FB;

public class SIC_INNER_SHOULD_BE_STATIC {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Outer {
            class InnerClass { // Нестатический внутренний класс
                public void doSomething() {
                    System.out.println("...");
                }
            }
        }

        Outer outer = new Outer();
        // Требуется ссылка на внешний объект
        Outer.InnerClass inner = outer.new InnerClass();
        inner.doSomething();
    }

    public static void correctTest() {
        class Outer {
            // Статический вложенный класс
            static class InnerClass {
                public void doSomething() {
                    System.out.println("...");
                }
            }
        }
        // Не требует ссылки на внешний объект
        Outer.InnerClass inner = new Outer.InnerClass();
        inner.doSomething();
    }
}

