package webMarker.service;

import webMarker.dao.ProductDao;

public class ProductDaoGenerator {
    private static ProductDao productDao;

    public static ProductDao getInstance() {
        if (productDao == null) {
            productDao = new ProductDao();
        }
        return productDao;
    }

    private ProductDaoGenerator() {
        productDao = new ProductDao();
    }
}
