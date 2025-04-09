package miem.projects.vulnerabilities.MINOR.FB;

import java.sql.*;

public class ODR_OPEN_DATABASE_RESOURCE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректная работа с ресурсами БД
    public static void incorrectTest() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "user", "pass");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            
            while (rs.next()) {
                System.out.println(rs.getString("username"));
            }
            // ОШИБКА: ресурсы не закрываются явно
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Корректная работа с ресурсами БД
    public static void correctTest() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "user", "pass");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
            
            while (rs.next()) {
                System.out.println(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}