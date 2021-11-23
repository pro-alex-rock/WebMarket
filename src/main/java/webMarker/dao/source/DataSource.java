package webMarker.dao.source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Oleksandr Haleta
 * 2021
 */
public interface DataSource {
    PreparedStatement getPrepareStatement(String query) throws SQLException;

    Connection getConnection();
}
