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

}
