package com.configuration;

import com.dao.DaoResource;
import com.dao.JdbcProductDao;

import javax.sql.DataSource;

public class ProductDaoFactory {
    private static DaoResource productDao;

    public static DaoResource getInstance(DataSource dataSource) {
        if (productDao == null) {
            productDao = new JdbcProductDao(dataSource);
        }
        return productDao;
    }

    private ProductDaoFactory(DataSource dataSource) {
        productDao = new JdbcProductDao(dataSource);
    }
}
