package org.lasantsy.lasantsy.repository;

import org.lasantsy.lasantsy.db.DBConnection;
import org.lasantsy.lasantsy.models.Movement;
import org.lasantsy.lasantsy.models.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockRepository implements GenericRepository<Stock, Long> {

    private Connection connection;

    public StockRepository() {
        this.connection = DBConnection.get_connection();
    }

    @Override
    public Stock save(Stock stock) {
        String sql = "insert into stock (id_station, value) VALUES (?,?);";
        try (PreparedStatement prepared = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepared.setLong(1, stock.getIdStation());
            prepared.setDouble(2, stock.getValue());
            prepared.executeUpdate();

            ResultSet generatedKeys = prepared.getGeneratedKeys();
            if (generatedKeys.next()) {
                stock.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Save failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stock;
    }

    @Override
    public List<Stock> findAll() {
        String sql = "select * from movement;";
        List<Stock> resultList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    resultList.add(new Stock(
                            result.getLong("id"),
                            result.getLong("id_station"),
                            result.getDouble("value")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public Stock findById(Long id) {
        String sql = "select * from movement where id =?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Stock(
                            result.getLong("id"),
                            result.getLong("id_station"),
                            result.getDouble("value")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void delete(Stock stock) {
        String sql = "delete from product where id =?;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.setLong(1, stock.getId());
            prepared.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from movement where id =?;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.setLong(1, id);
            prepared.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
