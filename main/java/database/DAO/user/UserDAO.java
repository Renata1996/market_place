package database.DAO.user;

import database.DAO.DAO;
import entities.User;

import java.util.List;

public interface UserDAO  extends DAO{

    List<User> getAllUsers();

    User getUser(String login);

    void changeInfo( User user);

    User getUserByID(long userID);

}
