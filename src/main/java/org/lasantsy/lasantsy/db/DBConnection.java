package org.lasantsy.lasantsy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection get_connection(){
        if (connection == null){
            try {
                connection = DriverManager.getConnection(ConnectionParams.URL, ConnectionParams.USER, ConnectionParams.PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}