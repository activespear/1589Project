package miem.projects.vulnerabilities.MAJOR.FB;

public class EC_UNRELATED_INTERFACES {

    interface A {}
    interface B {}

    static class C implements A {
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof C)) return false;
            return true;
        }
    }

    // Небезопасная конструкция: сравнение объектов разных интерфейсов без переопределения equals
    public static void unsafeCompare() {
        A a = new A() {};
        B b = new B() {};
        if (a.equals(b)) {
            System.out.println("Equal (unsafe)");
        } else {
            System.out.println("Not equal (unsafe)");
        }
    }

    // Безопасная конструкция: сравнение объектов одного класса с правильно переопределённым equals
    public static void safeCompare() {
        A a1 = new C();
        A a2 = new C();
        if (a1.equals(a2)) {
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

