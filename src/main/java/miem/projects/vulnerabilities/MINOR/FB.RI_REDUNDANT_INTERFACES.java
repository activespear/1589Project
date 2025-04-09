package miem.projects.vulnerabilities.MINOR.FB;

public class FB_RI_REDUNDANT_INTERFACES {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: избыточное наследование интерфейсов
        interface Animal {
            void eat();
        }
        
        interface Mammal extends Animal {  // Избыточно, если не добавляет новых методов
            void eat();  // Тот же метод, что и в Animal
        }
        
        class Dog implements Mammal {
            @Override
            public void eat() {
                System.out.println("Dog is eating");
            }
        }
        
        new Dog().eat();
    }

    public static void correctTest() {
        // Корректно: использование только нужных интерфейсов
        interface Animal {
            void eat();
        }
        
        class Dog implements Animal {
            @Override
            public void eat() {
                System.out.println("Dog is eating");
            }
        }
        
        new Dog().eat();
    }
}