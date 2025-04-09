package miem.projects.vulnerabilities.MINOR.FB;

public class STI_INTERRUPTED_ON_CURRENTTHREAD {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Проблемный пример: излишнее прерывание текущего потока
    public static void incorrectTest() {
        try {
            Thread.sleep(1000);
            // Плохо: прерываем текущий поток без причины
            Thread.currentThread().interrupt(); 
        } catch (InterruptedException e) {
            // Неправильно: не восстанавливаем флаг прерывания
            System.out.println("Sleep interrupted");
        }
    }

    // Корректный пример: правильная обработка прерываний
    public static void correctTest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Правильно: восстанавливаем статус прерывания
            Thread.currentThread().interrupt();
            System.out.println("Sleep interrupted properly");
        }
    }
}