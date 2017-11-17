package database.connection;

import java.sql.Connection;

public interface MakeConnection {

    boolean isConnected();

    Connection getConnection();

    void closeConnection();
}
