package com.dao;

import com.dao.DaoResource;
import com.dao.source.PostgresSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JdbcProductDaoTest {

    Connection mockConnection = mock(Connection.class);
    PreparedStatement mockStatement = mock(PreparedStatement.class);
    ResultSet mockResultSet = mock(ResultSet.class);


    @Test
    public void shouldCompareProductsAndTrue() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("name")).thenReturn("test");
        when(mockResultSet.getBigDecimal("price")).thenReturn(new BigDecimal(10));
        when(mockResultSet.getString("description")).thenReturn("TestDescription");

        when(mockStatement.executeQuery("SELECT name, price, description FROM products WHERE id=?")).thenReturn(mockResultSet);
        when(mockConnection.prepareStatement("SELECT name, price, description FROM products WHERE id=?")).thenReturn(mockStatement);

        String expectedName = "test";
        String expectedDescription = "TestDescription";
        BigDecimal expectedPrice = new BigDecimal(10);

        String actualName = null;
        String actualDescription = null;
        BigDecimal actualPrice = null;

        while (mockResultSet.next()) {
            actualName = mockResultSet.getString("name");
            actualDescription = mockResultSet.getString("description");
            actualPrice = mockResultSet.getBigDecimal("price");
        }
        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedDescription, actualDescription);
        Assertions.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void shouldCompareProductsAndFalse() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("name")).thenReturn("FalseTest");
        when(mockResultSet.getBigDecimal("price")).thenReturn(new BigDecimal(1));
        when(mockResultSet.getString("description")).thenReturn("FalseDescription");

        when(mockStatement.executeQuery("SELECT name, price, description FROM products WHERE id=?")).thenReturn(mockResultSet);
        when(mockConnection.prepareStatement("SELECT name, price, description FROM products WHERE id=?")).thenReturn(mockStatement);

        String expectedName = "test";
        String expectedDescription = "TestDescription";
        BigDecimal expectedPrice = new BigDecimal(10);

        String actualName = null;
        String actualDescription = null;
        BigDecimal actualPrice = null;

        while (mockResultSet.next()) {
            actualName = mockResultSet.getString("name");
            actualDescription = mockResultSet.getString("description");
            actualPrice = mockResultSet.getBigDecimal("price");
        }
        Assertions.assertNotEquals(expectedName, actualName);
        Assertions.assertNotEquals(expectedDescription, actualDescription);
        Assertions.assertNotEquals(expectedPrice, actualPrice);
    }

}