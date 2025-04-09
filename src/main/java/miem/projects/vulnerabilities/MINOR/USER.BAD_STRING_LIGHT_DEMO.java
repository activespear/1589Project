package miem.projects.vulnerabilities.MINOR.FB;

public class USER_BAD_STRING_LIGHT_DEMO {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Демонстрация неоптимальной работы со строками
    public static void incorrectTest() {
        String result = "";
        for (int i = 0; i < 100; i++) {
            result += i; // ОШИБКА: конкатенация в цикле
        }
        System.out.println(result);
    }

    // Исправленная версия
    public static void correctTest() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append(i);
        }
        System.out.println(sb.toString());
    }
}