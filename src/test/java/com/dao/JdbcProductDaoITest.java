package com.dao;

import com.configuration.UserDaoFactory;
import com.dao.source.PostgresSource;
import com.model.User;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcUserDaoITest {

    private final DataSource postgresSource = new PostgresSource();
    private final DaoResource jdbcUserDao = UserDaoFactory.getInstance(new PostgresSource());

    @Test
    void shouldCreateNewUserAndTrue() {
        User expectedUser = new User(1, "NameTest", "PasswordTest");

        jdbcUserDao.create(expectedUser);
        User actualUser = (User) jdbcUserDao.selectOne(1);
        assertEquals(expectedUser.getName(), actualUser.getName());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
    }
}