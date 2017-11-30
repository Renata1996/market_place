package servlets;

import database.DAO.item.ItemDAO;
import database.DAO.item.ItemDAOImpl;
import entities.Item;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EditItemServlet extends HttpServlet {

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String START_PRICE = "startPrice";
    private static final String CHECK_BOX = "checkBox";
    private static final String UID = "UID";
    private static final String ERROR = "error";
    private static final String TITLE_ERROR = "title must not be empty";
    private static final String EDIT_ITEM_JSP = "/editItem.jsp";
    private static final String PRICE_BID_ERROR = "price and bid increment must be > 0";
    private static final String FORMAT_DATE = "HH:mm";
    private static final String TIME = "time";
    private static final String TIME_ERROR = "time should be like hh:mm";
    private static final String INCREMENT = "increment";
    private static final String SHOW_ITEMS = "/showItems";

    private ItemDAO itemDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter(TITLE);
        String description = request.getParameter(DESCRIPTION);
        String startPrice = (request.getParameter(START_PRICE));

        String byItNow = request.getParameter(CHECK_BOX);//on if checked else null

        String uID = request.getParameter(UID);
        User loginedUser = Util.getLoginedUser(request.getSession());
        if (title == null || "".equals(title)) {
            request.setAttribute(ERROR, TITLE_ERROR);
            request.getRequestDispatcher(EDIT_ITEM_JSP).forward(request, response);
            return;
        }
        if (Integer.parseInt(startPrice) < 0 || startPrice == null) {
            request.setAttribute(ERROR, PRICE_BID_ERROR);
            request.getRequestDispatcher(EDIT_ITEM_JSP).forward(request, response);
            return;
        }

        Item item = new Item();
        item.setTitle(title);
        item.setDescription(description);
        if (byItNow == null) {
            Date date;
            try {
                date = new SimpleDateFormat(FORMAT_DATE).parse(request.getParameter(TIME));
                date.setTime(date.getTime() + new Date().getTime());
            } catch (ParseException e) {
                request.setAttribute(ERROR, TIME_ERROR);
                request.getRequestDispatcher(EDIT_ITEM_JSP).forward(request, response);
                return;
            }
            item.setByNowFlag(false);
            item.setTimeLeft(date.getTime());
            item.setStepRate(Integer.parseInt(request.getParameter(INCREMENT)));
        } else {
            item.setByNowFlag(true);
        }
        item.setStartPrice(Integer.parseInt(startPrice));
        item.setSellerID(loginedUser.getUserID());

        itemDAO = new ItemDAOImpl();
        if (uID == null) {
            itemDAO.createElement(item);
        } else {
            item.setItemID(Long.parseLong(uID));
            itemDAO.editItem(item);
        }
        response.sendRedirect(request.getContextPath() + SHOW_ITEMS);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(EDIT_ITEM_JSP).forward(request, response);
    }
}