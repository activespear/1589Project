package miem.projects.vulnerabilities.MINOR.FB;

import java.sql.*;

public class FB_CUSTOM_INJECTION {

    private final Connection connection;

    public FB_CUSTOM_INJECTION(Connection connection) {
        this.connection = connection;
    }

    // ❌ Потенциально небезопасное использование
    public String getUserDataUnsafe(String username) {
        String query = "SELECT * FROM users WHERE name = '" + username + "'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    // ✅ Корректная конструкция
    public String getUserDataSafe(String username) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM users WHERE name = ?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    // Демонстрация
    public static void main(String[] args) throws Exception {
        // Для теста можно использовать H2 или SQLite
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        Statement st = conn.createStatement();
        st.execute("CREATE TABLE users (id INT AUTO_INCREMENT, name VARCHAR(255))");
        st.execute("INSERT INTO users (name) VALUES ('admin'), ('user1')");

        FB_CUSTOM_INJECTION demo = new FB_CUSTOM_INJECTION(conn);

        System.out.println("Unsafe: " + demo.getUserDataUnsafe("admin' OR '1'='1"));
        System.out.println("Safe: " + demo.getUserDataSafe("admin"));
    }
}
