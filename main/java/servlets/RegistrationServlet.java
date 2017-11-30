package servlets;

import database.DAO.DAO;
import database.DAO.user.UserDAOImpl;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String RE_ENTERED_PASSWORD = "reEnteredPassword";
    private static final String BUILLING_ADDRESS = "buillingAddress";
    private static final String FULL_NAME = "fullName";
    private static final String ERROR = "error";
    private static final String EMPTY_FIELDS_ERROR = "Fields must not be empty!";
    private static final String REGISTRATION_REDIRECT = "/registration.jsp";
    private static final String SHOW_ITEMS_REDIRECT = "/showItems";
    private static final String LOGIN_ERROR = "login and name shuld be different";
    private static final String PASSWORD_ERROR = "password must be > 6";
    private static final String EQUALS_PASSWORD_ERROR = "passwords must be equals";
    private static final int MIN_PASSWORD_LENGTH = 6;

    private DAO userDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String reEnteredPassword = request.getParameter(RE_ENTERED_PASSWORD);
        String buillingAddress = request.getParameter(BUILLING_ADDRESS);
        String name = request.getParameter(FULL_NAME);

        if ("".equals(login) || "".equals(password) || "".equals(reEnteredPassword) || "".equals(buillingAddress) || "".equals(name)) {
            request.setAttribute(ERROR, EMPTY_FIELDS_ERROR);
            request.getRequestDispatcher(REGISTRATION_REDIRECT).forward(request, response);
            return;
        }
        if (login.equals(name)) {
            request.setAttribute(ERROR, LOGIN_ERROR);
            request.getRequestDispatcher(REGISTRATION_REDIRECT).forward(request, response);
            return;
        }
        if (password.length() < MIN_PASSWORD_LENGTH) {
            request.setAttribute(ERROR, PASSWORD_ERROR);
            request.getRequestDispatcher(REGISTRATION_REDIRECT).forward(request, response);
            return;
        }
        if (!password.equals(reEnteredPassword)) {
            request.setAttribute(ERROR, EQUALS_PASSWORD_ERROR);
            request.getRequestDispatcher(REGISTRATION_REDIRECT).forward(request, response);
            return;
        }
        userDAO = new UserDAOImpl();
        User user = new User(name, buillingAddress, login, password);
        userDAO.createElement(user);
        Util.storeLoginedUser(request.getSession(), user);
        Util.storeUserCookie(response, user);
        response.sendRedirect(request.getContextPath() + SHOW_ITEMS_REDIRECT);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(REGISTRATION_REDIRECT).forward(request, response);
    }
}