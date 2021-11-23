package webMarker.dao.source;

import webMarker.dao.source.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgresSource implements DataSource {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    @Override
    public PreparedStatement getPrepareStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn`t connect to postgres db.", e);
        }
    }
}
