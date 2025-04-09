package miem.projects.vulnerabilities.MINOR.FB;

public class FB_USM_USELESS_ABSTRACT_METHOD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: абстрактный метод без реальной цели
        abstract class Shape {
            abstract void draw();  // Ничего не делает в подклассах
        }
        
        class Circle extends Shape {
            @Override
            void draw() {}  // Пустая реализация
        }
        
        new Circle().draw();  // Бессмысленный вызов
    }

    public static void correctTest() {
        // Корректно: осмысленная абстракция
        abstract class Shape {
            abstract void draw();
            
            void display() {
                System.out.println("Displaying shape:");
                draw();
            }
        }
        
        class Circle extends Shape {
            @Override
            void draw() {
                System.out.println("Drawing circle");
            }
        }
        
        new Circle().display();  // Полезный вызов
    }
}