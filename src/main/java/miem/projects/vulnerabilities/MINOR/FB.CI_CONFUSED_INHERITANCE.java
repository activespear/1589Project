package miem.projects.vulnerabilities.MINOR.FB;

public class FB_CI_CONFUSED_INHERITANCE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректная иерархия наследования
    static class Bird {
        void fly() {
            System.out.println("Flying");
        }
    }

    static class Penguin extends Bird {  // Пингвин не может летать!
        @Override
        void fly() {
            throw new UnsupportedOperationException("Penguins can't fly!");
        }
    }

    public static void incorrectTest() {
        Bird bird = new Penguin();
        bird.fly();  // Выбросит исключение
    }

    // Корректная реализация через композицию
    static interface Bird {
        void move();
    }

    static class FlyingBird implements Bird {
        public void move() {
            System.out.println("Flying");
        }
    }

    static class Penguin implements Bird {
        public void move() {
            System.out.println("Swimming");
        }
    }

    public static void correctTest() {
        Bird bird1 = new FlyingBird();
        Bird bird2 = new Penguin();
        
        bird1.move();  // Flying
        bird2.move();  // Swimming
    }
}