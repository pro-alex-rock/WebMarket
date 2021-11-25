package webMarker.service;

import webMarker.configuration.UserDaoFactory;
import webMarker.dao.DaoResource;
import webMarker.dao.source.PostgresSource;
import webMarker.model.Product;
import webMarker.model.User;

import java.util.List;

public class UserService {
    private final DaoResource userDao = UserDaoFactory.getInstance(new PostgresSource());

    public Product selectOne(int id) {
        return userDao.selectOne(id);
    }

    public List<Product> selectAll() {
        return userDao.selectAll();
    }

    public void create(User user) {
        userDao.create(user);
    }

    public void updateOne(int id, User user) {
        userDao.updateOne(id, user);
    }

    public void delete(int id) {
        userDao.delete(id);
    }
}
