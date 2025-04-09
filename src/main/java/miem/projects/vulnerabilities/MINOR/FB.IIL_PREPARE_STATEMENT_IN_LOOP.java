package miem.projects.vulnerabilities.MINOR.FB;

import java.sql.*;

public class FB_IIL_PREPARE_STATEMENT_IN_LOOP {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String[] names = {"Alice", "Bob", "Charlie"};
        
        try (Connection conn = DriverManager.getConnection("jdbc:mock:test")) {
            // Некорректно: подготовка statement в цикле
            for (String name : names) {
                PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users(name) VALUES(?)");
                stmt.setString(1, name);
                stmt.executeUpdate();
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void correctTest() {
        String[] names = {"Alice", "Bob", "Charlie"};
        
        try (Connection conn = DriverManager.getConnection("jdbc:mock:test");
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO users(name) VALUES(?)")) {
            
            // Корректно: подготовка statement перед циклом
            for (String name : names) {
                stmt.setString(1, name);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}