package miem.projects.vulnerabilities.MINOR.FB;

public class FB_EQ_ABSTRACT_SELF {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Person {
            private String name;

            public Person(String name) {
                this.name = name;
            }

            // Некорректное переопределение equals
            public boolean equals(Person other) {
                return name.equals(other.name);
            }
        }

        Person p1 = new Person("Alice");
        Person p2 = new Person("Alice");

        // Неверно: метод equals(Object) не переопределен, может дать unexpected behavior
        System.out.println("Incorrect equals: " + p1.equals(p2));
    }

    public static void correctTest() {
        class Person {
            private String name;

            public Person(String name) {
                this.name = name;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Person other = (Person) obj;
                return name.equals(other.name);
            }

            @Override
            public int hashCode() {
                return name.hashCode();
            }
        }

        Person p1 = new Person("Alice");
        Person p2 = new Person("Alice");

        System.out.println("Correct equals: " + p1.equals(p2));
    }
}
