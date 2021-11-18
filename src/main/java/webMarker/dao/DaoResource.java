package webMarker.dao;

import webMarker.model.Product;

import java.util.List;

public interface DaoResource {
    Product selectOne(int id);

    List<Product> selectAll();

    void create(Product product);

    void updateOne(int id, Product product);

    void delete(int id);
}
