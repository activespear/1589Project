package miem.projects.vulnerabilities.MINOR.FB;

public class FB_EQ_SELF_NO_OBJECT {

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

        Person p1 = new Person("Bob");
        Person p2 = new Person("Bob");

        // Неверно: equals(Object) не переопределен
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

        Person p1 = new Person("Bob");
        Person p2 = new Person("Bob");

        System.out.println("Correct equals: " + p1.equals(p2));
    }
}
