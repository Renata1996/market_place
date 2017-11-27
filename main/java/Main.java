import database.DAO.user.UserDAO;
import database.DAO.user.UserDAOImpl;
import entities.User;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAOImpl();
        User user= userDAO.getUser("renata");

    }
}
