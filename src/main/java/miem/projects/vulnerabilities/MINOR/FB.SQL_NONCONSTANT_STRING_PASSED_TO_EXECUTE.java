package miem.projects.vulnerabilities.MINOR.FB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FB_SQL_NONCONSTANT_STRING_PASSED_TO_EXECUTE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: конкатенация SQL-запроса
        String username = "admin' OR '1'='1";  // Может прийти из пользовательского ввода
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM users WHERE username = '" + username + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);  // Уязвимость!
            var rs = stmt.executeQuery();
            System.out.println("SQL executed (INSECURE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void correctTest() {
        // Корректно: параметризованные запросы
        String username = "admin' OR '1'='1";
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);  // Безопасная подстановка
            var rs = stmt.executeQuery();
            System.out.println("SQL executed securely");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        // Заглушка для примера
        return null;
    }
}