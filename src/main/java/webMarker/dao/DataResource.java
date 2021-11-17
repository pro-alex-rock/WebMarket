package webMarker.dao;

import webMarker.model.Product;

import java.util.List;

public interface DataResource {
    Product selectOne(int id);

    List<Product> selectAll();

    void create(Product product);

    void update(int id, Product product);

    void delete(int id);
}
