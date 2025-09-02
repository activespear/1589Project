package miem.projects.vulnerabilities.MINOR.FB;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FB_REDOS {

    // Потенциально небезопасное использование
    public static void unsafeRegex() {
        Pattern pattern = Pattern.compile("^(a+)+$");
        Matcher matcher = pattern.matcher("aaaaaaaaaaaaaaaaaaaaaaaaaaaaX");
        boolean match = matcher.matches();
        System.out.println("Unsafe match: " + match);
    }

    // Корректная конструкция
    public static void safeRegex(String input) {
        // Ограничение длины входной строки
        if (input.length() > 100) {
            throw new IllegalArgumentException("Input too long");
        }

        // Более безопасное регулярное выражение
        Pattern pattern = Pattern.compile("^a{1,100}$");
        Matcher matcher = pattern.matcher(input);
        boolean match = matcher.matches();
        System.out.println("Safe match: " + match);
    }

    public static void main(String[] args) {
        unsafeRegex();

        // Пример безопасного вызова
        safeRegex("aaaaaaaaaa");  // безопасный вход
        // safeRegex("aaaaaaaaaaaaaaaaaaaaaaaaaaaaX"); // выбросит false
    }
}
