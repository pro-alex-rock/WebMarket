package webMarker.dao;

import webMarker.model.Product;
import webMarker.model.User;

import java.util.List;

public class UserDao implements DaoResource<User>{
    @Override
    public Product selectOne(int id) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public void updateOne(int id, User user) {

    }

    @Override
    public void delete(int id) {

    }
}
