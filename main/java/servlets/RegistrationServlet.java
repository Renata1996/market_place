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

    private DAO userDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String reEnteredPassword = request.getParameter("reEnteredPassword");
        String buillingAddress = request.getParameter("buillingAddress");
        String name = request.getParameter("fullName");

        if ("".equals(login) || "".equals(password) || "".equals(reEnteredPassword) || "".equals(buillingAddress) || "".equals(name)) {
            request.setAttribute("error", "Fields must not be empty!");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
            return;
        }
        if (login.equals(name)) {
            request.setAttribute("error", "login and name shuld be different");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
            return;
        }
        if (password.length() < 6) {
            request.setAttribute("error", "password must be > 6");
            request.getRequestDispatcher("/registration").forward(request, response);
            return;
        }
        if (!password.equals(reEnteredPassword)) {
            request.setAttribute("error", "passwords must be equals");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
            return;
        }

        userDAO = new UserDAOImpl();
        User user = new User(name, buillingAddress, login, password);
        userDAO.createElement(user);
        request.setAttribute("loginName", login);
        request.getRequestDispatcher("/showItems").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}