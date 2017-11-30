package servlets;

import database.DAO.user.UserDAO;
import database.DAO.user.UserDAOImpl;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/index"})
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login.equals("") || password.equals("")) {
            request.setAttribute("error", "password or userName should be > 0");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        userDAO = new UserDAOImpl();
        User user = userDAO.getUser(login);

        if (user == null) {
            request.setAttribute("error", "login or password are incorrect");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        if (user.getPassword().equals(password)) {
            Util.storeLoginedUser(request.getSession(), user);
            Util.storeUserCookie(response, user);
            response.sendRedirect(request.getContextPath() + "/showItems");
            return;
        } else {
            request.setAttribute("error", "login or password are incorrect");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}