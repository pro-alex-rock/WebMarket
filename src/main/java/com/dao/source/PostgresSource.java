package com.dao.source;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class PostgresSource implements DataSource {
    private final Properties properties = new Properties();

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(getProperty("postgres.url"), getProperty("postgres.user"), getProperty("postgres.password"));
        } catch (SQLException e) {
            throw new RuntimeException("Couldn`t open connection.", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn`t found jdbc Driver.", e);
        } catch (IOException e) {
            throw new RuntimeException("Absent needed property.", e);
        }
    }

    private String getProperty(String prop) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("/application.properties");
        properties.load(resourceAsStream);
        return properties.getProperty(prop);
    }

    @Override
    public Connection getConnection(String s, String s1) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter printWriter) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int i) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return false;
    }
}
