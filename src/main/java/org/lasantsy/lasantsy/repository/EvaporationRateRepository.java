package org.lasantsy.lasantsy.repository;

import org.lasantsy.lasantsy.db.DBConnection;
import org.lasantsy.lasantsy.models.EvaporationRate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EvaporationRateRepository implements GenericRepository<EvaporationRate, Long> {

    private Connection connection;

    public EvaporationRateRepository() {
        this.connection = DBConnection.get_connection();
    }

    @Override
    public EvaporationRate save(EvaporationRate evaporationRate) {
        String sql = "insert into evaporation_rate (id_station, value) VALUES (?,?);";
        try (PreparedStatement prepared = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepared.setLong(1, evaporationRate.getStation());
            prepared.setDouble(2, evaporationRate.getValue());
            prepared.executeUpdate();

            ResultSet generatedKeys = prepared.getGeneratedKeys();
            if (generatedKeys.next()) {
                evaporationRate.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Save failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return evaporationRate;
    }

    @Override
    public List<EvaporationRate> findAll() {
        String sql = "select * from evaporation_rate;";
        List<EvaporationRate> resultList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    resultList.add(new EvaporationRate(
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
    public EvaporationRate findById(Long id) {
        String sql = "select * from evaporation_rate where id =?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new EvaporationRate(
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
    public void delete(EvaporationRate evaporationRate) {
        String sql = "delete from evaporation_rate where id =?;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.setLong(1, evaporationRate.getId());
            prepared.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from evaporation_rate where id =?;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.setLong(1, id);
            prepared.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
