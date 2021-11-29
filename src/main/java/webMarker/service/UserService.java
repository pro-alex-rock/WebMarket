package webMarker.service;

import webMarker.configuration.UserDaoFactory;
import webMarker.dao.JdbcUserDao;
import webMarker.dao.source.PostgresSource;
import webMarker.model.User;

import java.util.List;

public class UserService implements DefaultService<User> {
    private final JdbcUserDao jdbcUserDao = (JdbcUserDao) UserDaoFactory.getInstance(new PostgresSource());

    public boolean isExistUser(String login, String passwordEncode) {
        return jdbcUserDao.getUser(login, new SecurityService().getPasswordEncode(login, passwordEncode)).isPresent();
    }

    @Override
    public User selectOne(int id) {
        return jdbcUserDao.selectOne(id);
    }

    @Override
    public List<User> selectAll() {
        return jdbcUserDao.selectAll();
    }

    @Override
    public void create(User user) {
        jdbcUserDao.create(user);
    }

    @Override
    public void updateOne(int id, User user) {

    }

    @Override
    public void delete(int id) {
        jdbcUserDao.delete(id);
    }
}
