package com.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JdbcUserDaoTest {

    Connection mockConnection = mock(Connection.class);
    PreparedStatement mockStatement = mock(PreparedStatement.class);
    ResultSet mockResultSet = mock(ResultSet.class);

    @Test
    void shouldCompareUsersAndTrue() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("user");
        when(mockResultSet.getString("password")).thenReturn("TestPassword");

        when(mockStatement.executeQuery("SELECT username, password FROM users WHERE id=?")).thenReturn(mockResultSet);
        when(mockConnection.prepareStatement("SELECT username, password FROM users WHERE id=?")).thenReturn(mockStatement);

        int expectedId = 1;
        String expectedName = "user";
        String expectedPassword = "TestPassword";

        int actualId = 0;
        String actualName = null;
        String actualPassword = null;

        while (mockResultSet.next()) {
            actualId = mockResultSet.getInt("id");
            actualName = mockResultSet.getString("name");
            actualPassword = mockResultSet.getString("password");
        }
        Assertions.assertEquals(expectedId, actualId);
        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void shouldCompareUsersAndFalse() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("user");
        when(mockResultSet.getString("password")).thenReturn("TestPassword");

        when(mockStatement.executeQuery("SELECT username, password FROM users WHERE id=?")).thenReturn(mockResultSet);
        when(mockConnection.prepareStatement("SELECT username, password FROM users WHERE id=?")).thenReturn(mockStatement);

        int expectedId = 2;
        String expectedName = "userFalse";
        String expectedPassword = "TestPasswordFalse";

        int actualId = 0;
        String actualName = null;
        String actualPassword = null;

        while (mockResultSet.next()) {
            actualId = mockResultSet.getInt("id");
            actualName = mockResultSet.getString("name");
            actualPassword = mockResultSet.getString("password");
        }
        Assertions.assertNotEquals(expectedId, actualId);
        Assertions.assertNotEquals(expectedName, actualName);
        Assertions.assertNotEquals(expectedPassword, actualPassword);
    }
}