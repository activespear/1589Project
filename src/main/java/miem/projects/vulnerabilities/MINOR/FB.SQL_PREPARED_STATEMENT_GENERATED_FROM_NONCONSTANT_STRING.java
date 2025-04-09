package miem.projects.vulnerabilities.MINOR.FB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FB_SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: динамическое создание SQL из ненадежного источника
        String tableName = "users; DROP TABLE users; --"; // Может прийти из пользовательского ввода
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM " + tableName; // Опасная конкатенация
            PreparedStatement stmt = conn.prepareStatement(sql); // Ложное чувство безопасности
            stmt.executeQuery();
            System.out.println("Query executed (INSECURE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void correctTest() {
        // Корректно: валидация имен таблиц/колонок
        String tableName = "users";
        try (Connection conn = getConnection()) {
            if (!isValidTableName(tableName)) {
                throw new IllegalArgumentException("Invalid table name");
            }
            String sql = "SELECT * FROM " + tableName;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            System.out.println("Query executed securely");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidTableName(String name) {
        return name != null && name.matches("^[a-zA-Z_][a-zA-Z0-9_]*$");
    }

    private static Connection getConnection() throws SQLException {
        // Заглушка для примера
        return null;
    }
}