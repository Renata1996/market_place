package database.connection;

import java.sql.Connection;

public interface IConnection {

    boolean isConnected();

    Connection getConnection();

    void closeConnection();
}
