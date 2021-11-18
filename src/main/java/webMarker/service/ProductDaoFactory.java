package webMarker.service;

import webMarker.dao.DaoResource;
import webMarker.dao.DataSource;
import webMarker.dao.ProductDao;

public class ProductDaoFactory {
    private static DaoResource productDao;

    public static DaoResource getInstance(DataSource dataSource) {
        if (productDao == null) {
            productDao = new ProductDao(dataSource);
        }
        return productDao;
    }

    private ProductDaoFactory(DataSource dataSource) {
        productDao = new ProductDao(dataSource);
    }
}
