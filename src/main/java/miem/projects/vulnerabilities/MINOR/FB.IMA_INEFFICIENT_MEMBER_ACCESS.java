package miem.projects.vulnerabilities.MINOR.FB;

public class FB_IMA_INEFFICIENT_MEMBER_ACCESS {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class DataHolder {
            int value = 0;
            int getValue() { return value; }
        }
        
        DataHolder holder = new DataHolder();
        
        // Некорректно: многократный доступ к полю/методу
        for (int i = 0; i < 100; i++) {
            System.out.println(holder.getValue());  // Доступ в каждой итерации
        }
    }

    public static void correctTest() {
        class DataHolder {
            int value = 0;
            int getValue() { return value; }
        }
        
        DataHolder holder = new DataHolder();
        
        // Корректно: кэширование значения
        int value = holder.getValue();
        for (int i = 0; i < 100; i++) {
            System.out.println(value);  // Использование кэшированного значения
        }
        
        // Альтернатива для изменяемых полей
        for (int i = 0; i < 100; i++) {
            int currentValue = holder.getValue();  // Если значение может меняться
            System.out.println(currentValue);
        }
    }
}