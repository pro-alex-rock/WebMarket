package webMarker.configuration;

import webMarker.dao.DaoResource;
import webMarker.dao.ProductDao;
import webMarker.dao.UserDao;
import webMarker.dao.source.DataSource;

public class UserDaoFactory {
    private static DaoResource userDao;

    public static DaoResource getInstance(DataSource dataSource) {
        if (userDao == null) {
            userDao = new UserDao(dataSource);
        }
        return userDao;
    }

    private UserDaoFactory(DataSource dataSource) {
        userDao = new ProductDao(dataSource);
    }
}
