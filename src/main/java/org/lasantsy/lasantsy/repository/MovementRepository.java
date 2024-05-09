package org.lasantsy.lasantsy.repository;

import org.lasantsy.lasantsy.db.DBConnection;
import org.lasantsy.lasantsy.models.Movement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovementRepository implements GenericRepository<Movement, Long> {

    private Connection connection;

    public MovementRepository() {
        this.connection = DBConnection.get_connection();
    }

    @Override
    public Movement save(Movement movement) {
        String sql = "insert into movement (id_station, id_product, value, type) VALUES (?,?,?,?);";
        try (PreparedStatement prepared = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepared.setLong(1, movement.getIdStation());
            prepared.setLong(2, movement.getIdProduct());
            prepared.setDouble(3, movement.getValue());
            prepared.setString(4, movement.getType());
            prepared.executeUpdate();

            ResultSet generatedKeys = prepared.getGeneratedKeys();
            if (generatedKeys.next()) {
                movement.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Save failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movement;
    }

    @Override
    public List<Movement> findAll() {
        String sql = "select * from movement;";
        List<Movement> resultList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    resultList.add(new Movement(
                            result.getLong("id"),
                            result.getLong("id_station"),
                            result.getLong("id_product"),
                            result.getDouble("value"),
                            result.getString("type")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public Movement findById(Long id) {
        String sql = "select * from movement where id =?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Movement(
                            result.getLong("id"),
                            result.getLong("id_station"),
                            result.getLong("id_product"),
                            result.getDouble("value"),
                            result.getString("type")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void delete(Movement movement) {
        String sql = "delete from product where id =?;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.setLong(1, movement.getId());
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
