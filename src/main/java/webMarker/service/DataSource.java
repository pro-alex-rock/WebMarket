package webMarker.service;

import java.sql.*;

public class DataSource {
    private static final String URL = "jdbc:postgresql://localhost:5432/product_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    PreparedStatement statement;

    public PreparedStatement getPrepareStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn`t download postgres driver.", e);
        }

        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn`t connect to postgres db.", e);
        }
    }
}
