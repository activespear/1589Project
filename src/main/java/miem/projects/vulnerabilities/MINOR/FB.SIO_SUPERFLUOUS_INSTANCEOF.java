package miem.projects.vulnerabilities.MINOR.FB;

public class SIO_SUPERFLUOUS_INSTANCEOF {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Пример с избыточной проверкой instanceof
    public static void incorrectTest() {
        Object obj = "Test String";
        
        if (obj instanceof String) { // Проверка избыточна, т.к. obj уже String
            String str = (String) obj;
            System.out.println(str.toUpperCase());
        }
    }

    // Исправленный пример (удалена лишняя проверка)
    public static void correctTest() {
        Object obj = "Test String";
        
        // Без лишнего instanceof, т.к. приведение безопасно
        String str = (String) obj;
        System.out.println(str.toUpperCase());
    }
}