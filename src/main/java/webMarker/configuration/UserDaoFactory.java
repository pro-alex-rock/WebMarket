package webMarker.configuration;

import webMarker.dao.DaoResource;
import webMarker.dao.JdbcProductDao;
import webMarker.dao.JdbcUserDao;

import javax.sql.DataSource;

public class UserDaoFactory {
    private static DaoResource userDao;

    public static DaoResource getInstance(DataSource dataSource) {
        if (userDao == null) {
            userDao = new JdbcUserDao(dataSource);
        }
        return userDao;
    }

    private UserDaoFactory(DataSource dataSource) {
        userDao = new JdbcProductDao(dataSource);
    }
}
