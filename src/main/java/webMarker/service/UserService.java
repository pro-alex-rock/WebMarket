package webMarker.service;

import webMarker.configuration.UserDaoFactory;
import webMarker.dao.UserDao;
import webMarker.dao.source.PostgresSource;
import webMarker.model.User;

import java.util.List;

public class UserService implements DefaultService<User> {
    private final UserDao userDao = (UserDao) UserDaoFactory.getInstance(new PostgresSource());

    public boolean isExistUser(String login, String passwordEncode) {
        return userDao.getUser(login, new SecurityService().getPasswordEncode(login, passwordEncode)).isPresent();
    }

    @Override
    public User selectOne(int id) {
        return userDao.selectOne(id);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void updateOne(int id, User user) {

    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
