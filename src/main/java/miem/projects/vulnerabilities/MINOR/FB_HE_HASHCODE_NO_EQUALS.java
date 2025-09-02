package miem.projects.vulnerabilities.MINOR.FB;

public class FB_HE_HASHCODE_NO_EQUALS {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Потенциально небезопасно: переопределен только hashCode(), нет equals()
        MyClass obj1 = new MyClass(1);
        MyClass obj2 = new MyClass(1);

        System.out.println("Incorrect equals check: " + obj1.equals(obj2)); // false
        System.out.println("Hash codes: " + obj1.hashCode() + ", " + obj2.hashCode()); // 1, 1
    }

    public static void correctTest() {
        // Корректная конструкция: переопределены и hashCode(), и equals()
        MyClassCorrect obj1 = new MyClassCorrect(1);
        MyClassCorrect obj2 = new MyClassCorrect(1);

        System.out.println("Correct equals check: " + obj1.equals(obj2)); // true
        System.out.println("Hash codes: " + obj1.hashCode() + ", " + obj2.hashCode()); // 1, 1
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

        // Нет equals()
    }

    // Корректная реализация
    static class MyClassCorrect {
        private int id;

        public MyClassCorrect(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id;
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
