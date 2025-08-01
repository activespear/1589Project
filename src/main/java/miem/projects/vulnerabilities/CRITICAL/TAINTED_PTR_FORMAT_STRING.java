package miem.projects.vulnerabilities.CRITICAL;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TAINTED_PTR_FORMAT_STRING {

    // Небезопасная версия — передает строку из байтов напрямую в printf, что опасно
    public void unsafeFmtStr(Socket s, byte[] buf, int len, int flags) throws IOException {
        InputStream in = s.getInputStream();
        int readBytes = in.read(buf, 0, len);
        if (readBytes > 0) {
            System.out.printf(new String(buf, 0, readBytes));
        }
    }

    // Безопасная версия — использует фиксированный формат "%s" и передает строку как параметр
    public void safeFmtStr(Socket s, byte[] buf, int len, int flags) throws IOException {
        InputStream in = s.getInputStream();
        int readBytes = in.read(buf, 0, len);
        if (readBytes > 0) {
            String safeFormat = "%s";
            System.out.printf(safeFormat, new String(buf, 0, readBytes));
        }
    }

    public static void main(String[] args) throws IOException {
        final int PORT = 12345;
        final int BUF_SIZE = 1024;

        // Запускаем простой сервер для демонстрации
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен, ожидаем подключения...");

            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Клиент подключен");

                TAINTED_PTR_FORMAT_STRING example = new TAINTED_PTR_FORMAT_STRING();

                byte[] buffer = new byte[BUF_SIZE];

                System.out.println("=== Запуск небезопасной версии ===");
                try {
                    example.unsafeFmtStr(clientSocket, buffer, BUF_SIZE, 0);
                } catch (Exception e) {
                    System.err.println("Небезопасная версия выбросила исключение: " + e);
                }

                System.out.println("\n=== Запуск безопасной версии ===");
                try {
                    example.safeFmtStr(clientSocket, buffer, BUF_SIZE, 0);
                } catch (Exception e) {
                    System.err.println("Безопасная версия выбросила исключение: " + e);
                }
            }
        }
    }
}

