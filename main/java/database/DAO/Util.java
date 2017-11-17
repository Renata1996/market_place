package database.DAO;

import database.connection.MakeConnection;
import database.connection.MakeConnectionImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Util {
    private MakeConnection makeConnection;

    public Util() {
        makeConnection = new MakeConnectionImpl();
    }

    public void makeQuery(String query) {
        if (makeConnection.isConnected()) {
            try (Connection connection = makeConnection.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeQuery(query);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet getResultSet(String query) {
        if (makeConnection.isConnected()) {
            try {
                Connection connection = makeConnection.getConnection();
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
