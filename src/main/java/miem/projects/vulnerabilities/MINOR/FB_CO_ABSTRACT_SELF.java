package miem.projects.vulnerabilities.MINOR.FB;

public class FB_CO_ABSTRACT_SELF {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass implements Comparable<MyClass> {
            public int value;

            // Некорректно: не переопределяет compareTo(Object)
            public int compareTo(MyClass other) {
                return Integer.compare(this.value, other.value);
            }
        }

        MyClass a = new MyClass();
        MyClass b = new MyClass();
        a.value = 10;
        b.value = 20;

        System.out.println("Comparison result (INSECURE): " + a.compareTo(b));
    }

    public static void correctTest() {
        class MyClass implements Comparable<MyClass> {
            public int value;

            @Override
            public int compareTo(Object o) {
                if (!(o instanceof MyClass)) {
                    throw new ClassCastException("Invalid type");
                }
                MyClass other = (MyClass) o;
                return Integer.compare(this.value, other.value);
            }
        }

        MyClass a = new MyClass();
        MyClass b = new MyClass();
        a.value = 10;
        b.value = 20;

        System.out.println("Comparison result (SECURE): " + a.compareTo(b));
    }
}
