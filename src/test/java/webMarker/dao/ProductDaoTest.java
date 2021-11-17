package webMarker.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import webMarker.service.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

class ProductDaoTest {
    @Mock
    DataResource mockProductDao;
    @Mock
    DataSource mockDataSource;
    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStmnt;
    @Mock
    ResultSet mockResultSet;
    int productId = 10;
    String query;

    @BeforeEach
    public void setUp() throws SQLException {
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockDataSource.getPrepareStatement(query)).thenReturn(mockPreparedStmnt);
    }


    @Test
    public void ddd() throws SQLException {
        query = "SELECT * FROM products WHERE id=?";
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockDataSource.getPrepareStatement(query)).thenReturn(mockPreparedStmnt);
    }

}