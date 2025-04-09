package miem.projects.vulnerabilities.MINOR.FB;

import java.sql.*;

public class SQL_BAD_RESULTSET_ACCESS {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Пример с ошибками: индексы вместо имен, нет закрытия ресурсов
    public static void incorrectTest() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "user", "pass");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM users");

            while (rs.next()) {
                int id = rs.getInt(1); // Плохо: доступ по индексу
                String name = rs.getString(2); // Плохо: доступ по индексу
                System.out.println(id + ": " + name);
            }
            // Плохо: ресурсы не закрыты!
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Исправленный пример: имена колонок, try-with-resources, PreparedStatement
    public static void correctTest() {
        String query = "SELECT id, name FROM users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "user", "pass");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, 1); // Защита от SQL-инъекций
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id"); // Хорошо: доступ по имени
                    String name = rs.getString("name"); // Хорошо: доступ по имени
                    System.out.println(id + ": " + name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}