package miem.projects.vulnerabilities.MAJOR.FB;

import java.sql.*;

public class SQL_BAD_PREPARED_STATEMENT_ACCESS {

    private static final String URL = "jdbc:h2:mem:test_db;DB_CLOSE_DELAY=-1";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS users ("
                                                   + "id INT PRIMARY KEY)";
    private static final String INSERT_SQL = "INSERT INTO users (id) VALUES (?)";
    private static final String SELECT_SQL = "SELECT * FROM USERS WHERE id = ?";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_SQL)) {
                preparedStatement.executeUpdate();
                System.out.println("Таблица создана");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
                preparedStatement.setInt(1, 1);
                preparedStatement.executeUpdate();
                System.out.println("Запись добавлена");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)) {
                incorrectTest(preparedStatement);
                correctTest(preparedStatement);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void incorrectTest(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(0, 1);
        // ...
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
        }
    }

    public static void correctTest(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, 1);
        // ...
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
        }
    }
}