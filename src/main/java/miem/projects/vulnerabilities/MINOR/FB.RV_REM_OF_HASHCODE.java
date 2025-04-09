package miem.projects.vulnerabilities.MINOR.FB;

public class FB_RV_REM_OF_HASHCODE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        Object obj = new Object();
        // Некорректно: взятие остатка от хэш-кода
        int bucket = obj.hashCode() % 100;
        System.out.println("Incorrect bucket: " + bucket);
    }

    public static void correctTest() {
        Object obj = new Object();
        // Корректно: использование Math.floorMod для равномерного распределения
        int bucket = Math.floorMod(obj.hashCode(), 100);
        System.out.println("Correct bucket: " + bucket);
        
        // Альтернативный корректный вариант
        int bucket2 = (obj.hashCode() & 0x7FFFFFFF) % 100;
        System.out.println("Alternative correct bucket: " + bucket2);
    }
}