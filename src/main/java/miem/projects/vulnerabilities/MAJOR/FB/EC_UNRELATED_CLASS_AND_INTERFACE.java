package miem.projects.vulnerabilities.MAJOR.FB;

public class EC_UNRELATED_CLASS_AND_INTERFACE {

    interface MyInterface {}

    static class MyClass {}

    static class MyImpl implements MyInterface {
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof MyImpl)) return false;
            return true;
        }
    }

    // Небезопасная конструкция: сравнение объектов разных классов без переопределения equals
    public static void unsafeCompare() {
        Object a = new MyClass();
        Object b = new MyInterface() {};
        if (a.equals(b)) {
            System.out.println("Equal (unsafe)");
        } else {
            System.out.println("Not equal (unsafe)");
        }
    }

    // Безопасная конструкция: сравнение объектов одного класса с правильно переопределённым equals
    public static void safeCompare() {
        Object a = new MyImpl();
        Object b = new MyImpl();
        if (a.equals(b)) {
            System.out.println("Equal (safe)");
        } else {
            System.out.println("Not equal (safe)");
        }
    }

    public static void main(String[] args) {
        unsafeCompare();
        safeCompare();
    }
}

