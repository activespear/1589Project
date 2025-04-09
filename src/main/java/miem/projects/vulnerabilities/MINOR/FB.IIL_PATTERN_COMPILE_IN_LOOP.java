package miem.projects.vulnerabilities.MINOR.FB;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FB_IIL_PATTERN_COMPILE_IN_LOOP {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String[] inputs = {"item1", "item2", "item3"};
        
        // Некорректно: компиляция в каждой итерации
        for (String input : inputs) {
            Pattern pattern = Pattern.compile("item\\d");  // Компиляция в цикле
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                System.out.println("Found: " + input);
            }
        }
    }

    public static void correctTest() {
        String[] inputs = {"item1", "item2", "item3"};
        
        // Корректно: однократная компиляция перед циклом
        Pattern pattern = Pattern.compile("item\\d");
        for (String input : inputs) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                System.out.println("Found: " + input);
            }
        }
    }
}