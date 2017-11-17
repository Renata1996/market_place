package database.DAO.user;

import database.DAO.Util;
import entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String SELECT_USERS = "SELECT * FROM Users";
    private static final String UPDATE_USER = "UPDATE Users SET login =";
    private static final String CREATE_USER = "INSERT INTO Users (name, address,login,password) VALUES('";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM Users WHERE login = '";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM Users WHERE userID = ";
    private static final String WHERE_EXP_FOR_UPDATE = " WHERE userID =";
    private static final String USERID = "userID";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private Util util = new Util();

    public UserDAOImpl() {
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String query = SELECT_USERS;
        ResultSet resultSet = util.getResultSet(query);
        try {
            while (resultSet.next()) {
                users.add(makeUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void createElement(Object o) {
        User user = (User) o;
        StringBuilder query = new StringBuilder();
        query.append(CREATE_USER).append(user.getName()).append("' , '").append(user.getAddress())
                .append("' , '").append(user.getLogin()).append("' , '").append(user.getPassword()).append("' )");
        util.makeQuery(query.toString());

    }

    public User getUser(String login) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_USER_BY_LOGIN).append(login).append("'");
        User user = null;

        try (ResultSet resultSet = util.getResultSet(query.toString())) {
            while (resultSet.next()) {
                user = makeUser(resultSet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (user == null) {
            throw new NullPointerException();
        }
        return user;
    }

    public User getUserByID(long userID) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_USER_BY_ID).append(userID);
        User user = null;

        try (ResultSet resultSet = util.getResultSet(query.toString())) {
            while (resultSet.next()) {
                user = makeUser(resultSet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (user == null) {
            throw new NullPointerException();
        }
        return user;
    }

    public void changeInfo(User changedUser) {
        User user = getUserByID(changedUser.getUserID());
        if (user != null) {
            StringBuilder query = new StringBuilder();
            query.append(UPDATE_USER).append("'").append(changedUser.getLogin()).append("',").append(PASSWORD)
                    .append(" ='").append(changedUser.getPassword()).append("',").append(NAME).append("='")
                    .append(changedUser.getName()).append("',").append(ADDRESS).append(" ='").append(changedUser.getAddress())
                    .append("'").append(WHERE_EXP_FOR_UPDATE).append(user.getUserID());

            util.makeQuery(query.toString());
        }
    }

    private User makeUser(ResultSet resultSet) {
        User user = null;
        try {
            long userID = resultSet.getInt(USERID);
            String userLogin = resultSet.getString(LOGIN);
            String password = resultSet.getString(PASSWORD);
            String address = resultSet.getString(ADDRESS);
            String name = resultSet.getString(NAME);
            user = new User(userID, name, address, userLogin, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
