package miem.projects.vulnerabilities.MINOR.FB;

import java.sql.Connection;
import java.sql.DriverManager;

public class FB_HARD_CODE_PASSWORD {

    public void unsafe(String dbUrl) throws Exception {
        // ❌ Потенциально небезопасное: пароль захардкожен в коде
        String password = "P@ssw0rd123";
        Connection conn = DriverManager.getConnection(dbUrl, "user", password);
        System.out.println("Connected (insecure)!");
        conn.close();
    }

    public void safe(String dbUrl) throws Exception {
        // ✅ Корректная конструкция: пароль берём из переменной окружения
        String password = System.getenv("DB_PASSWORD");
        if (password == null) {
            throw new IllegalStateException("DB_PASSWORD environment variable not set");
        }
        Connection conn = DriverManager.getConnection(dbUrl, "user", password);
        System.out.println("Connected (secure)!");
        conn.close();
    }

    public static void main(String[] args) throws Exception {
        String dbUrl = "jdbc:mysql://localhost:3306/testdb";

        FB_HARD_CODE_PASSWORD demo = new FB_HARD_CODE_PASSWORD();

        // ❌ небезопасный вариант
        demo.unsafe(dbUrl);

        // ✅ безопасный вариант (ожидает, что DB_PASSWORD задан в окружении)
        demo.safe(dbUrl);
    }
}
