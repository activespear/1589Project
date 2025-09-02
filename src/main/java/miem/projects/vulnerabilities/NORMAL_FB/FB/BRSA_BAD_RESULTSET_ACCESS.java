package miem.projects.vulnerabilities.NORMAL.FB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BRSA_BAD_RESULTSET_ACCESS {
    public static void main(String[] args) throws SQLException {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws SQLException {
        Connection conn = null; // предполагаем, что соединение инициализировано
        Statement stmt = conn.createStatement();

        try (ResultSet rs = stmt.executeQuery("SELECT id, name FROM users")) {
            while (rs.next()) {
                // Потенциально небезопасный доступ: индекс начинается с 0
                int id = rs.getInt(0);
                String name = rs.getString(1);
                System.out.println("User: " + id + ", " + name);
            }
        }
    }

    public static void correctTest() throws SQLException {
        Connection conn = null; // предполагаем, что соединение инициализировано
        Statement stmt = conn.createStatement();

        try (ResultSet rs = stmt.executeQuery("SELECT id, name FROM users")) {
            while (rs.next()) {
                // Правильный доступ: индекс начинается с 1
                int id = rs.getInt(1);
                String name = rs.getString(2);
                System.out.println("User: " + id + ", " + name);
            }
        }
    }
}