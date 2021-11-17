package webMarker.dao;

import webMarker.model.Product;
import webMarker.service.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements DataResource {
    private DataSource dataSource;

    public ProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Product selectOne(int id) {
        if (id <= 0) {
            throw new RuntimeException("Insert correct id.");
        }
        Product product = new Product();
        try(PreparedStatement statement = dataSource.getPrepareStatement("SELECT * FROM products WHERE id=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            product.setId(id);
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getBigDecimal("price"));
        } catch (SQLException e) {
            throw new RuntimeException("Couldn`t select product " + id + " from db", e);
        }
        return product;
    }


    @Override
    public List<Product> selectAll() {
        List<Product> products = new ArrayList<>();
        try(PreparedStatement statement = dataSource.getPrepareStatement("SELECT * FROM products")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Couldn`t select from db", e);
        }
        return products;
    }

    @Override
    public void create(Product product) {
        try(PreparedStatement statement = dataSource.getPrepareStatement("INSERT INTO products VALUES (1, ?, ?)")) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn`t create new Product.", e);
        }
    }

    @Override
    public void update(int id, Product product) {
        try(PreparedStatement statement = dataSource.getPrepareStatement("UPDATE products SET name = ?, price = ? WHERE id = ?")) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn`t update to db", e);
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement statement = dataSource.getPrepareStatement("DELETE FROM products WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn`t delete.", e);
        }
    }
}
