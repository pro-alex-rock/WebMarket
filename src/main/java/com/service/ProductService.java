package com.service;

import com.configuration.ProductDaoFactory;
import com.dao.DaoResource;
import com.dao.source.PostgresSource;
import com.model.Product;

import java.util.List;

public class ProductService implements DefaultService<Product> {
    private final DaoResource productDao = ProductDaoFactory.getInstance(new PostgresSource());

    @Override
    public Product selectOne(int id) {
        return (Product) productDao.selectOne(id);
    }

    @Override
    public List<Product> selectAll() {
        return productDao.selectAll();
    }

    @Override
    public void create(Product product) {
        productDao.create(product);
    }

    @Override
    public void updateOne(int id, Product product) {
        productDao.updateOne(id, product);
    }

    @Override
    public void delete(int id) {
        productDao.delete(id);
    }
}
