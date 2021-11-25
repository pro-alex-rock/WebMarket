package webMarker.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webMarker.dao.source.DataSource;
import webMarker.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements DaoResource<User>{
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<User> getUser(String login, String passwordEncode) {
        try(PreparedStatement statement = dataSource.getPrepareStatement("SELECT id, username, password FROM users WHERE (username = ? AND password = ?)")) {
            statement.setString(1, login);
            statement.setString(2, passwordEncode);
            ResultSet resultSet = statement.executeQuery();
            //resultSet.next();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                logger.info("Success get user {} from db", user);
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            logger.info("Couldn`t get user from db because connection failed" + e);
            throw new RuntimeException("Couldn`t get user from db because connection failed", e);
        }
    }

    @Override
    public User selectOne(int id) {
        if (id <= 0) {
            logger.info("Inserted incorrect id.");
            throw new RuntimeException("Inserted incorrect id.");
        }
        User user = new User();
        try(PreparedStatement statement = dataSource.getPrepareStatement("SELECT username, password FROM users WHERE id=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user.setId(id);
            user.setName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            resultSet.close();
        } catch (SQLException e) {
            logger.info("Couldn`t select user with id {} from db" + e, id);
            throw new RuntimeException("Couldn`t select user with id " + id + " from db", e);
        }
        logger.info("The user with id: {} selected", id);
        return user;
    }

    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        try(PreparedStatement statement = dataSource.getPrepareStatement("SELECT id, username, password FROM users");
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.info("Couldn`t select users from db" + e);
            throw new RuntimeException("Couldn`t select users from db", e);
        }
        logger.info("All users selected.");
        return users;
    }

    @Override
    public void create(User user) {
        try(PreparedStatement statement = dataSource.getPrepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Couldn`t create new user." + e);
            throw new RuntimeException("Couldn`t create new user.", e);
        }
        logger.info("The user {} created.", user);
    }

    @Override
    public void updateOne(int id, User user) {}

    @Override
    public void delete(int id) {
        try(PreparedStatement statement = dataSource.getPrepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Couldn`t delete user by id: {}" + e, id);
            throw new RuntimeException("Couldn`t delete user by id: " + id, e);
        }
        logger.info("The user with id: {} deleted", id);
    }
}
