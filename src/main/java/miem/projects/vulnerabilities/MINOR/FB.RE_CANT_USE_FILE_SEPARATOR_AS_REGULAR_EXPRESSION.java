package miem.projects.vulnerabilities.MINOR.FB;

import java.io.File;
import java.util.regex.Pattern;

public class RE_CANT_USE_FILE_SEPARATOR_AS_REGULAR_EXPRESSION {
    public static void main(String[] args) {
        incorrectUsage();
        correctUsage();
    }

    // Некорректный пример
    public static void incorrectUsage() {
        String path = "C:\\temp\\file.txt";
        boolean isTemp = path.matches(".*" + File.separator + "temp" + 
                                    File.separator + ".*");
        // На Windows выбросит исключение из-за неэкранированных \
        System.out.println("Is temp file (incorrect): " + isTemp);
    }

    // Корректные примеры
    public static void correctUsage() {
        String path = "C:\\temp\\file.txt";
        
        // Способ 1: Pattern.quote()
        boolean safe1 = path.matches(".*" + Pattern.quote(File.separator) + "temp" + 
                       Pattern.quote(File.separator) + ".*");
        
        // Способ 2: Явное экранирование
        String sep = File.separator.equals("\\") ? "\\\\" : File.separator;
        boolean safe2 = path.matches(".*" + sep + "temp" + sep + ".*");
        
        // Способ 3: Использование File API
        boolean safe3 = new File(path).getParent().endsWith(File.separator + "temp");
        
        System.out.println("Is temp file (safe1): " + safe1);
        System.out.println("Is temp file (safe2): " + safe2);
        System.out.println("Is temp file (safe3): " + safe3);
    }
}