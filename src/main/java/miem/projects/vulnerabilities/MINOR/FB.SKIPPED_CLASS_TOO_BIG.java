package miem.projects.vulnerabilities.MINOR.FB;

public class SKIPPED_CLASS_TOO_BIG {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Пример использования слишком большого класса
        MonsterClass monster = new MonsterClass();
        monster.processUser();
        monster.validateOrder();
    }

    public static void correctTest() {
        // Пример использования разбитых классов
        UserManager userManager = new UserManager();
        OrderProcessor orderProcessor = new OrderProcessor();
        
        userManager.registerUser();
        orderProcessor.processOrder();
    }
}

// Проблемный класс (вложен в основной для соответствия шаблону)
class MonsterClass {
    private String userData;
    private String orderData;

    public void processUser() { System.out.println("Processing user"); }
    public void validateOrder() { System.out.println("Validating order"); }
    // ... 100+ других методов
}

// Исправленные классы (вложены)
class UserManager {
    public void registerUser() { System.out.println("User registered"); }
}

class OrderProcessor {
    public void processOrder() { System.out.println("Order processed"); }
}