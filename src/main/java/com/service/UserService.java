package com.service;

import com.configuration.UserDaoFactory;
import com.dao.JdbcUserDao;
import com.dao.source.PostgresSource;
import com.model.User;

import java.util.List;
import java.util.Optional;

public class UserService implements DefaultService<User> {
    private final JdbcUserDao jdbcUserDao = (JdbcUserDao) UserDaoFactory.getInstance(new PostgresSource());

    public boolean isExistUser(String login) {
        return jdbcUserDao.getByName(login).isPresent();
    }

    public Optional<User> getByName(String login) {
        return jdbcUserDao.getByName(login);
    }

    public void update(User user) {
        jdbcUserDao.updateOne(user);
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
