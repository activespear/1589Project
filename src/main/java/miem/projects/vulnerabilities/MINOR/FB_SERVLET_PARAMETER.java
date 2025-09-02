package miem.projects.vulnerabilities.MINOR.FB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FB_SERVLET_PARAMETER {

    private Connection connection;

    // Потенциально небезопасное
    public void unsafeQuery(String id) {
        try {
            Statement stmt = connection.createStatement();
            // Недоверенные данные напрямую подставляются в SQL
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = " + id);
            // Возможна SQL-инъекция
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Корректная конструкция — валидация + параметризованный запрос
    public static String validateInput(String input, String pattern) {
        if (input == null || !input.matches(pattern)) {
            throw new IllegalArgumentException("Invalid input");
        }
        return input;
    }

    public void safeQuery(String id) {
        try {
            String safeId = validateInput(id, "^[a-zA-Z0-9]{1,10}$");

            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM users WHERE id = ?"
            );
            pstmt.setString(1, safeId);
            ResultSet rs = pstmt.executeQuery();
            // Обработка результата
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
