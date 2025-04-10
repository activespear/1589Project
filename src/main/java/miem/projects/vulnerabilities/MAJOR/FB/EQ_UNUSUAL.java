package miem.projects.vulnerabilities.MAJOR.FB;

public class EQ_UNUSUAL {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Person {
            private final String name;
            private final int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public boolean equals(Object obj) {
                // нет стандартной проверки на совместимость типов
                try {
                    Person p = (Person) obj;
                    return this.name.equals(p.name) && this.age == p.age;
                } catch (ClassCastException e) {
                    return false;
                }
            }
        }

        Person p1 = new Person("Alice", 25);
        Person p2 = new Person("Alice", 25);
        Person p3 = new Person("Bob", 30);

        System.out.println(p1.equals(p2)); // true (но реализация equals странная)
        System.out.println(p1.equals(p3)); // false
        System.out.println(p1.equals("Alice")); // выброс ClassCastException
    }

    public static void correctTest() {
        class Person {
            private final String name;
            private final int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Person person = (Person) obj;
                return age == person.age && name.equals(person.name);
            }

            @Override
            public int hashCode() {
                return 31 * name.hashCode() + age;
            }
        }

        Person p1 = new Person("Alice", 25);
        Person p2 = new Person("Alice", 25);
        Person p3 = new Person("Bob", 30);

        System.out.println(p1.equals(p2)); // true
        System.out.println(p1.equals(p3)); // false
        System.out.println(p1.equals("Alice")); // false (без исключений)
    }
}