package org.lasantsy.lasantsy.repository;

import org.lasantsy.lasantsy.db.DBConnection;
import org.lasantsy.lasantsy.models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements GenericRepository<Product, Long> {

    private Connection connection;

    public ProductRepository() {
        this.connection = DBConnection.get_connection();
    }

    @Override
    public Product save(Product product) {
        String sql = "insert into product (name, longitude, latitude, employee_number) VALUES (?,?,?,?);";
        try (PreparedStatement prepared = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepared.setString(1, product.getName());
            prepared.setDouble(2, product.getPrice());
            prepared.executeUpdate();

            ResultSet generatedKeys = prepared.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Save failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        String sql = "select * from product;";
        List<Product> resultList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    resultList.add(new Product(
                            result.getLong("id"),
                            result.getString("name"),
                            result.getDouble("price")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public Product findById(Long id) {
        String sql = "select * from product where id =?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Product(
                            result.getLong("id"),
                            result.getString("name"),
                            result.getDouble("price")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void delete(Product product) {
        String sql = "delete from product where id =?;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.setLong(1, product.getId());
            prepared.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from product where id =?;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.setLong(1, id);
            prepared.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
