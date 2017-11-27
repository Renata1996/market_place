package servlets;

import database.DAO.item.ItemDAO;
import database.DAO.item.ItemDAOImpl;
import entities.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowItemsServlet extends HttpServlet {
    private ItemDAO itemDAO;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        itemDAO = new ItemDAOImpl();
        List<Item> items = itemDAO.getAllItems();
        request.setAttribute("items", items);
        request.getRequestDispatcher("/showItems.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
