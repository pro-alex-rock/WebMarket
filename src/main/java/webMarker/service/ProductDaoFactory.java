package webMarker.service;

import webMarker.dao.DataResource;
import webMarker.dao.DataSource;
import webMarker.dao.ProductDao;

public class ProductDaoFactory {
    private static DataResource productDao;

    public static DataResource getInstance(DataSource dataSource) {
        if (productDao == null) {
            productDao = new ProductDao(dataSource);
        }
        return productDao;
    }

    private ProductDaoFactory(DataSource dataSource) {
        productDao = new ProductDao(dataSource);
    }
}
