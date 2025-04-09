package miem.projects.vulnerabilities.MINOR.FB;

public class FB_UC_USELESS_OBJECT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: создание ненужного объекта
        String result = new String("constant");  // Избыточный конструктор
        System.out.println(result);
        
        // Другой пример
        Integer sum = new Integer(0);  // Ненужная обертка
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        System.out.println("Sum: " + sum);
    }

    public static void correctTest() {
        // Корректно: использование литералов
        String result = "constant";  // Прямое присваивание
        System.out.println(result);
        
        // Примитив вместо обертки
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        System.out.println("Sum: " + sum);
        
        // Когда объекты действительно нужны
        String dynamic = new String(new char[]{'d','y','n','a','m','i','c'});
        System.out.println("Needed object: " + dynamic);
    }
}