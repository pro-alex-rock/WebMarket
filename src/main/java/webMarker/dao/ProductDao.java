package webMarker.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webMarker.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements DaoResource {
    private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);
    private final DataSource dataSource;
    private int id;

    public ProductDao(DataSource postgresSource) {
        this.dataSource = postgresSource;
        id = getLastId();
    }

    private int getLastId() {
        try(PreparedStatement statement = dataSource.getPrepareStatement("SELECT MAX(id) as id FROM products;");
            ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            try {
                id = resultSet.getInt("id");
            } catch (Exception e) {
                id = 1;
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn`t connect to db", e);
        }
    }

    @Override
    public Product selectOne(int id) {
        if (id <= 0) {
            logger.info("Inserted incorrect id.");
            throw new RuntimeException("Inserted incorrect id.");
        }
        Product product = new Product();
        try(PreparedStatement statement = dataSource.getPrepareStatement("SELECT name, price FROM products WHERE id=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            product.setId(id);
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getBigDecimal("price"));
            resultSet.close();
        } catch (SQLException e) {
            logger.info("Couldn`t select product {} from db", id);
            throw new RuntimeException("Couldn`t select product " + id + " from db", e);
        }
        logger.info("The product with id: {} selected", id);
        return product;
    }


    @Override
    public List<Product> selectAll() {
        List<Product> products = new ArrayList<>();
        try(PreparedStatement statement = dataSource.getPrepareStatement("SELECT id, name, price FROM products");
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            logger.info("Couldn`t select from db");
            throw new RuntimeException("Couldn`t select from db", e);
        }
        logger.info("All products selected.");
        return products;
    }

    @Override
    public void create(Product product) {
        try(PreparedStatement statement = dataSource.getPrepareStatement("INSERT INTO products VALUES (?, ?, ?)")) {
            statement.setInt(1, ++id);
            statement.setString(2, product.getName());
            statement.setBigDecimal(3, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Couldn`t create new Product.");
            throw new RuntimeException("Couldn`t create new Product.", e);
        }
        logger.info("The product {} created.", product);
    }

    @Override
    public void updateOne(int id, Product product) {
        try(PreparedStatement statement = dataSource.getPrepareStatement("UPDATE products SET name = ?, price = ? WHERE id = ?")) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setInt(3, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Couldn`t update by id: {} - {}", id, product);
            throw new RuntimeException("Couldn`t update by id: " + id + ", " + product, e);
        }
        logger.info("The product with id: {} updated", id);
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement statement = dataSource.getPrepareStatement("DELETE FROM products WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            --id;
        } catch (SQLException e) {
            logger.info("Couldn`t delete by id: {}", id);
            throw new RuntimeException("Couldn`t delete by id: " + id, e);
        }
        logger.info("The product with id: {} deleted", id);
    }
}
