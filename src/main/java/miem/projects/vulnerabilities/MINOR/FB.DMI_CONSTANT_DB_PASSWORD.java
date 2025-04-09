package miem.projects.vulnerabilities.MINOR.FB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FB_DMI_CONSTANT_DB_PASSWORD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: пароль в открытом виде в коде
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", 
                "admin", 
                "SuperSecret123!");  // Пароль в коде
            System.out.println("Connected (INSECURE)");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void correctTest() {
        // Корректно: получение учетных данных из защищенного источника
        try {
            String password = System.getenv("DB_PASSWORD");  // Из переменных окружения
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", 
                "admin", 
                password);
            System.out.println("Connected (SECURE)");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}