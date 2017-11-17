package database.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MakeConnectionImpl implements MakeConnection {

    private static final String PROPERTIES_PATH = "src/main/resources/config.properties";
    private Properties properties;
    private Connection connection = null;

    public MakeConnectionImpl() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(PROPERTIES_PATH));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public boolean isConnected() {
        try {
            Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.login"),properties.getProperty("db.password"));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }
}
