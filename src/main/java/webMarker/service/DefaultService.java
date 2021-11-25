package webMarker.service;

import java.util.List;

public interface DefaultService<T> {
    T selectOne(int id);

    List<T> selectAll();

    void create(T t);

    void updateOne(int id, T t);

    void delete(int id);
}
