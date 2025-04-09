package miem.projects.vulnerabilities.MINOR.FB;

public class FB_PZLA_PREFER_ZERO_LENGTH_ARRAYS {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: возврат null вместо пустого массива
        String[] strings = getStringsIncorrect();
        if (strings != null) {  // Лишняя проверка
            for (String s : strings) {
                System.out.println(s);
            }
        }
    }

    public static void correctTest() {
        // Корректно: возврат пустого массива
        String[] strings = getStringsCorrect();
        for (String s : strings) {  // Без проверки null
            System.out.println(s);
        }
    }

    // Некорректный метод (возвращает null)
    private static String[] getStringsIncorrect() {
        return null;  // Плохая практика
    }

    // Корректный метод (возвращает пустой массив)
    private static String[] getStringsCorrect() {
        return new String[0];  // Правильный подход
    }
}