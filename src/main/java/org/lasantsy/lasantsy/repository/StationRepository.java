package org.lasantsy.lasantsy.repository;

import org.lasantsy.lasantsy.db.DBConnection;
import org.lasantsy.lasantsy.models.Station;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationRepository implements GenericRepository<Station, Long> {

    private Connection connection;

    public StationRepository() {
        this.connection = DBConnection.get_connection();
    }

    @Override
    public Station save(Station station) {
        String sql = "insert into station (name, longitude, latitude, employee_number) VALUES (?,?,?,?);";
        try (PreparedStatement prepared = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepared.setString(1, station.getName());
            prepared.setString(2, station.getLongitude());
            prepared.setString(3, station.getLatitude());
            prepared.setInt(4, station.getEmployeeNumber());
            prepared.executeUpdate();

            ResultSet generatedKeys = prepared.getGeneratedKeys();
            if (generatedKeys.next()) {
                station.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Save failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return station;
    }

    @Override
    public List<Station> findAll() {
        String sql = "select * from station;";
        List<Station> resultList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    resultList.add(new Station(
                            result.getLong("id"),
                            result.getString("name"),
                            result.getString("longitude"),
                            result.getString("latitude"),
                            result.getInt("employee_number")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public Station findById(Long id) {
        String sql = "select * from station where id =?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Station(
                            result.getLong("id"),
                            result.getString("name"),
                            result.getString("longitude"),
                            result.getString("latitude"),
                            result.getInt("employee_number")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void delete(Station station) {
        String sql = "delete from station where id =?;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.setLong(1, station.getId());
            prepared.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from station where id =?;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.setLong(1, id);
            prepared.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
