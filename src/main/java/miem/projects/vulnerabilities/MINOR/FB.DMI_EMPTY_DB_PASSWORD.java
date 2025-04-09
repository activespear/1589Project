package miem.projects.vulnerabilities.MINOR.FB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FB_DMI_EMPTY_DB_PASSWORD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        try {
            // Некорректно: пустой пароль
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb",
                "admin",
                "");  // Пустой пароль
            System.out.println("Connected with empty password (INSECURE)");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void correctTest() {
        try {
            // Корректно: получение пароля из защищенного источника
            String password = System.getenv("DB_PASSWORD");
            if (password == null || password.isEmpty()) {
                throw new IllegalStateException("Database password not configured");
            }
            
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb",
                "admin",
                password);
            System.out.println("Connected with secure password");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}