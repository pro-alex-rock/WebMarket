package webMarker.service;

import webMarker.configuration.ProductDaoFactory;
import webMarker.dao.DaoResource;
import webMarker.dao.source.PostgresSource;
import webMarker.model.Product;

import java.util.List;

public class ProductService {
    private final DaoResource productDao = ProductDaoFactory.getInstance(new PostgresSource());

    public Product selectOne(int id) {
        return productDao.selectOne(id);
    }

    public List<Product> selectAll() {
        return productDao.selectAll();
    }

    public void create(Product product) {
        productDao.create(product);
    }

    public void updateOne(int id, Product product) {
        productDao.updateOne(id, product);
    }

    public void delete(int id) {
        productDao.delete(id);
    }
}
