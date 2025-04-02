package miem.projects.vulnerabilities.MAJOR.FB;

public class EQ_DOESNT_OVERRIDE_EQUALS {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Parent {
            private final int id;

            public Parent(int id) {
                this.id = id;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Parent parent = (Parent) obj;
                return id == parent.id;
            }
        }

        class Child extends Parent {
            // Новое поле, но equals() не переопределен
            private final String name;

            public Child(int id, String name) {
                super(id);
                this.name = name;
            }
        }

        Child c1 = new Child(1, "Alice");
        Child c2 = new Child(1, "Bob");
        // сравнивает только id, игнорируя name
        System.out.println(c1.equals(c2));
    }

    public static void correctTest() {
        class Parent {
            private final int id;

            public Parent(int id) {
                this.id = id;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Parent parent = (Parent) obj;
                return id == parent.id;
            }

            @Override
            public int hashCode() {
                return Integer.hashCode(id);
            }
        }

        class Child extends Parent {
            private final String name;

            public Child(int id, String name) {
                super(id);
                this.name = name;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (!(obj instanceof Child)) return false;
                if (!super.equals(obj)) return false;
                Child child = (Child) obj;
                return name.equals(child.name);
            }

            @Override
            public int hashCode() {
                return 31 * super.hashCode() + name.hashCode();
            }
        }

        Child c1 = new Child(1, "Alice");
        Child c2 = new Child(1, "Bob");

        System.out.println(c1.equals(c2));
    }
}