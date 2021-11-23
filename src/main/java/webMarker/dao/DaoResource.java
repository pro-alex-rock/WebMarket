package webMarker.dao;

import webMarker.model.Product;

import java.util.List;

public interface DaoResource<T> {
    Product selectOne(int id);

    List<T> selectAll();

    void create(T t);

    void updateOne(int id, T t);

    void delete(int id);
}
