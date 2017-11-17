package database.DAO.user;

import entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplTest {

    private List<User> userList;
    private UserDAO userDAO;
    private User user = new User(6, "name", "Moscow", "login", "passwrod");
    private static final String NOT_EXSIST_LOGIN = "a";

    @Test
    public void testCreateUser() throws Exception {
        userDAO = new UserDAOImpl();
        userDAO.createElement(user);
        Assert.assertTrue(userDAO.getAllUsers().contains(user));
    }

    @Test(expected = SQLIntegrityConstraintViolationException.class)
    public void testCreateExistUser() throws Exception {
        userDAO = new UserDAOImpl();
        userDAO.createElement(user);
    }

    @Test(expected = NullPointerException.class)
    public void testGetNotExistUser() throws Exception {
        userDAO = new UserDAOImpl();
        userDAO.getUser(NOT_EXSIST_LOGIN);
    }

    @Test
    public void testGetUser() throws Exception {
        userDAO = new UserDAOImpl();
        final String login = user.getLogin();
        Assert.assertTrue(user.equals(userDAO.getUser(login)));
    }

    @Test
    public void testChangeInfo() throws Exception {
        user.setLogin("login1");
        userDAO = new UserDAOImpl();
        userDAO.changeInfo(user);
        Assert.assertTrue(user.equals(userDAO.getUserByID(user.getUserID())));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        createUsers();
        userDAO = new UserDAOImpl();

        for (User user : userDAO.getAllUsers()) {
            Assert.assertTrue(userList.contains(user));
        }
    }

    private void createUsers() {
        userList = new ArrayList<>();
        User user = new User(1, "Renata Karimova", "Saratov", "renata", "1234");
        userList.add(user);
        user = new User(3, "Mary", "Saratov, ul.32 strelkovoy divizii", "masha", "masha");
        userList.add(user);
    }

}