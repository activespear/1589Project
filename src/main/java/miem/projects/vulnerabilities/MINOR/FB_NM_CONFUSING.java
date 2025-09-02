package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NM_CONFUSING {

    public static void main(String[] args) {
        System.out.println("Пример демонстрирует некорректное и корректное именование методов.");
    }

    // Потенциально небезопасный вариант
    static class ConfusingMethods {
        // Методы отличаются только регистром букв
        public void processData() {
            System.out.println("Обработка данных в первом методе.");
        }

        public void ProcessData() {
            System.out.println("Обработка данных во втором методе (дублирующий).");
        }
    }

    // Корректный вариант
    static class ClearMethods {
        // Методы имеют уникальные и понятные имена
        public void processData() {
            System.out.println("Обработка данных в первом методе.");
        }

        public void handleData() {
            System.out.println("Обработка данных во втором методе с уникальным именем.");
        }
    }
}
