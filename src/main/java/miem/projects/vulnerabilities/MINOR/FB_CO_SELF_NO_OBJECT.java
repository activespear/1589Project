package miem.projects.vulnerabilities.MINOR.FB;

public class FB_CO_SELF_NO_OBJECT {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Person {
            int age;

            // Некорректно: не переопределяет compareTo(Object)
            public int compareTo(Person p) {
                return Integer.compare(this.age, p.age);
            }
        }

        Person a = new Person();
        Person b = new Person();
        a.age = 25;
        b.age = 30;

        System.out.println("Comparison result (INSECURE): " + a.compareTo(b));
    }

    public static void correctTest() {
        class Person implements Comparable<Object> {
            int age;

            @Override
            public int compareTo(Object o) {
                if (!(o instanceof Person)) {
                    throw new ClassCastException("Expected Person");
                }
                Person p = (Person) o;
                return Integer.compare(this.age, p.age);
            }
        }

        Person a = new Person();
        Person b = new Person();
        a.age = 25;
        b.age = 30;

        System.out.println("Comparison result (SECURE): " + a.compareTo(b));
    }
}
