package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Objects;

public class FB_HE_HASHCODE_USE_OBJECT_EQUALS {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Потенциально небезопасно: hashCode переопределен, но equals() не переопределен
        MyClass obj1 = new MyClass(1);
        MyClass obj2 = new MyClass(1);

        System.out.println("Incorrect equals check: " + obj1.equals(obj2)); // false, сравнение по ссылке
        System.out.println("Hash codes: " + obj1.hashCode() + ", " + obj2.hashCode()); // 1, 1
    }

    public static void correctTest() {
        // Корректная реализация: переопределены hashCode() и equals()
        MyClassCorrect obj1 = new MyClassCorrect(1);
        MyClassCorrect obj2 = new MyClassCorrect(1);

        System.out.println("Correct equals check: " + obj1.equals(obj2)); // true
        System.out.println("Hash codes: " + obj1.hashCode() + ", " + obj2.hashCode()); // одинаковые
    }

    // Некорректная реализация
    static class MyClass {
        private int id;

        public MyClass(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id;
        }

        // equals() не переопределен, используется Object.equals()
    }

    // Корректная реализация
    static class MyClassCorrect {
        private int id;

        public MyClassCorrect(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id); // можно использовать несколько полей
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            MyClassCorrect other = (MyClassCorrect) obj;
            return id == other.id;
        }
    }
}
