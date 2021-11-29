package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.dao.DaoResource;
import com.dao.source.PostgresSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;

class JdbcProductDaoTest {

    PostgresSource mockPostgresSource = mock(PostgresSource.class);

    DaoResource productDao;

    Connection mockConnection = mock(Connection.class);
    PreparedStatement mockPreparedStmnt = mock(PreparedStatement.class);
    ResultSet mockResultSet = mock(ResultSet.class);

    @BeforeEach
    public void setUp() throws SQLException {
        /*when(mockPostgresSource.getConnection()).thenReturn(mockConnection);
        when(mockPostgresSource.getPrepareStatement("SELECT MAX(id) as id FROM products;"))
                .thenReturn(mock(PreparedStatement.class));
        when(mockPreparedStmnt.executeQuery()).thenReturn(mock(ResultSet.class));
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);
        productDao = new JdbcProductDao(mockPostgresSource);*/
    }


    @Test
    public void shouldCompareProductsAndTrue() throws SQLException {
        /*when(mockPostgresSource.getPrepareStatement("SELECT name, price FROM products WHERE id=?")).thenReturn(mockPreparedStmnt);
        when(mockPreparedStmnt.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Test");
        when(mockResultSet.getBigDecimal("price")).thenReturn(new BigDecimal(10));
        when(mockResultSet.getString("description")).thenReturn("test description");
        Product expectedProduct = new Product();
        expectedProduct.setId(1);
        expectedProduct.setName("Test");
        expectedProduct.setPrice(new BigDecimal(10));
        expectedProduct.setDescription("test description");
        Product actualProduct = productDao.selectOne(1);
        Assertions.assertEquals(expectedProduct, actualProduct);*/
    }

}