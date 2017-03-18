package service;

import utils.properties.PropertiesHolder;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionService {

    public static Connection createConnection() {
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(PropertiesHolder.getProperty("DB_URL"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
