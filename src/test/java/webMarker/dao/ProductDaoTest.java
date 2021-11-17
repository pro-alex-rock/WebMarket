package webMarker.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

class ProductDaoTest {
    @Mock
    DataResource mockProductDao;
    @Mock
    DataSource mockPostgresSource;
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
        when(mockPostgresSource.getConnection()).thenReturn(mockConnection);
        when(mockPostgresSource.getPrepareStatement(query)).thenReturn(mockPreparedStmnt);
    }


    @Test
    public void ddd() throws SQLException {
        query = "SELECT * FROM products WHERE id=?";
        when(mockPostgresSource.getConnection()).thenReturn(mockConnection);
        when(mockPostgresSource.getPrepareStatement(query)).thenReturn(mockPreparedStmnt);
    }

}