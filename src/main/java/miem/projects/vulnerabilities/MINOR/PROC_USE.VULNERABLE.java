package miem.projects.vulnerabilities.MINOR.FB;

import java.io.*;

public class PROC_USE_VULNERABLE {
    public static void main(String[] args) {
        try {
            incorrectTest("calc.exe");
            correctTest("notepad.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Уязвимое выполнение процесса
    public static void incorrectTest(String command) throws IOException {
        Runtime.getRuntime().exec(command); // Опасность: выполнение произвольной команды
        System.out.println("Команда выполнена (небезопасно)");
    }

    // Безопасное выполнение процесса
    public static void correctTest(String safeCommand) throws IOException {
        // Валидация и санитизация ввода
        if (!safeCommand.matches("^[a-zA-Z0-9_.-]+$")) {
            throw new IllegalArgumentException("Недопустимое имя команды");
        }
        
        // Использование белого списка разрешенных команд
        if (safeCommand.equals("notepad.exe") || safeCommand.equals("explorer.exe")) {
            ProcessBuilder pb = new ProcessBuilder(safeCommand);
            pb.start();
            System.out.println("Безопасное выполнение команды");
        } else {
            throw new SecurityException("Команда не разрешена");
        }
    }
}