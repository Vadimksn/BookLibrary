package service;

import utils.PropertiesHolder;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Vadim on 06.03.2017.
 */
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
