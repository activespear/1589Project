package miem.projects.vulnerabilities.MINOR.FB;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FB_IIL_PATTERN_COMPILE_IN_LOOP_INDIRECT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String[] inputs = {"test1", "test2", "test3"};
        String regex = "test\\d";  // Одно и то же выражение
        
        // Некорректно: косвенная компиляция в цикле через метод
        for (String input : inputs) {
            if (matchesPattern(input, regex)) {  // Компиляция при каждом вызове
                System.out.println("Match: " + input);
            }
        }
    }

    private static boolean matchesPattern(String input, String regex) {
        return Pattern.compile(regex).matcher(input).matches();  // Компиляция внутри
    }

    public static void correctTest() {
        String[] inputs = {"test1", "test2", "test3"};
        String regex = "test\\d";
        
        // Корректно: однократная компиляция
        Pattern pattern = Pattern.compile(regex);
        for (String input : inputs) {
            if (pattern.matcher(input).matches()) {
                System.out.println("Match: " + input);
            }
        }
    }
}