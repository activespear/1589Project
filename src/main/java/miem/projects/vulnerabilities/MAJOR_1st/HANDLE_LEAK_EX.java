package miem.projects.vulnerabilities.MAJOR_1st;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HANDLE_LEAK_EX {

    // Небезопасная конструкция: ресурсы создаются, но не закрываются в finally
    public static void unsafeResourceHandling() {
        Socket socket = null;
        BufferedReader reader = null;
        try {
            socket = new Socket("localhost", 8080);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = reader.readLine();
            System.out.println("Небезопасно: " + line);
        } catch (IOException e) {
            System.out.println("Ошибка в unsafeResourceHandling: " + e);
        }
        // finally блок есть, но пуст — ресурсы не освобождаются
    }

    // Безопасная конструкция: try-with-resources гарантирует закрытие
    public static void safeResourceHandling() {
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String line = reader.readLine();
            System.out.println("Безопасно: " + line);
        } catch (IOException e) {
            System.out.println("Ошибка в safeResourceHandling: " + e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Запуск небезопасной конструкции:");
        unsafeResourceHandling();

        System.out.println("\nЗапуск безопасной конструкции:");
        safeResourceHandling();
    }
}

