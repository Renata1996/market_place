package database.DAO;

import database.connection.IConnection;
import database.connection.IConnectionImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Util {
    private IConnection iConnection;

    public Util() {
        iConnection = new IConnectionImpl();
    }

    public void makeQuery(String query) {
        if (iConnection.isConnected()) {
            try (Connection connection = iConnection.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeQuery(query);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet getResultSet(String query) {
        if (iConnection.isConnected()) {
            try {
                Connection connection = iConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                return resultSet;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
