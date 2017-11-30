package servlets;

import database.DAO.bid.BidDAO;
import database.DAO.bid.BidDAOImpl;
import database.DAO.item.ItemDAO;
import database.DAO.item.ItemDAOImpl;
import entities.Bid;
import entities.Item;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowItemsServlet extends HttpServlet {

    private static final String ITEMS = "items";
    private static final String USER = "user";
    private static final String TIME_NOW = "timeNow";
    private static final String LOGIN_NAME = "loginName";
    private static final String SUBMIT = "submit";
    private static final String BUY = "buy";
    private static final String BID = "bid";
    private static final String BID_TEXT = "bidText";
    private static final String START_PRICE = "startPrice";
    private static final String ITEM_ID = "itemID";
    private static final String SEARCH_FIELD = "searchField";
    private static final String SELECTION = "selection";
    private static final String SHOW_ITEMS_JSP = "/showItems.jsp";
    private static final String UID = "UID";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String MIN_BID_INC = "minBidInc";

    private ItemDAO itemDAO = new ItemDAOImpl();
    private BidDAO bidDAO = new BidDAOImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loginedUser = Util.getLoginedUser(request.getSession());
        request.setAttribute(USER, loginedUser);
        request.setAttribute(TIME_NOW, new Date().getTime());

        if (loginedUser == null) {
            request.setAttribute(LOGIN_NAME, null);
        } else {
            request.setAttribute(LOGIN_NAME, loginedUser.getLogin());
        }

        Boolean buyButtonPressed = SUBMIT.equals(request.getParameter(BUY));
        Boolean bidButtonPressed = SUBMIT.equals(request.getParameter(BID));

        if (bidButtonPressed && loginedUser != null) {
            long startPrce = Long.parseLong(request.getParameter(START_PRICE));
            long bid = Long.parseLong(request.getParameter(BID_TEXT));
            long minBidInc = Long.parseLong(request.getParameter(MIN_BID_INC));
           /* if (bid < minBidInc) {
                Map<Item, Bid> elemets = new HashMap<>();
                List<Item> items = itemDAO.getAllItems();

                for (Item item : items) {
                    elemets.put(item, bidDAO.getBestBid(item));
                }
                request.setAttribute(ITEMS, elemets);
                request.getRequestDispatcher(SHOW_ITEMS_JSP).forward(request, response);
                return;
            }*/
            Long price = startPrce + bid;
            makeBid(request.getParameter(ITEM_ID), loginedUser.getUserID(), price);
        }
        if (buyButtonPressed && loginedUser != null) {
            Long price = Long.parseLong(request.getParameter(START_PRICE));
            makeBid(request.getParameter(ITEM_ID), loginedUser.getUserID(), price);
        }

        String searchField = request.getParameter(SEARCH_FIELD);
        try {
            if (searchField != null && !"".equals(searchField)) {
                Map<Item, Bid> elements = search(request.getParameter(SELECTION), searchField);
                request.setAttribute(ITEMS, elements);
                request.getRequestDispatcher(SHOW_ITEMS_JSP).forward(request, response);
                return;
            }
            Map<Item, Bid> elemets = new HashMap<>();
            List<Item> items = itemDAO.getAllItems();

            for (Item item : items) {
                elemets.put(item, bidDAO.getBestBid(item));
            }
            request.setAttribute(ITEMS, elemets);
            request.getRequestDispatcher(SHOW_ITEMS_JSP).forward(request, response);

        } catch (Exception ex) {
            request.getRequestDispatcher(SHOW_ITEMS_JSP).forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(TIME_NOW, new Date().getTime());
        doGet(request, response);
    }

    private void makeBid(String itemID, long userID, long value) {
        bidDAO = new BidDAOImpl();
        Bid bid = new Bid();
        bid.setItemID(Long.parseLong(itemID));
        bid.setBid(value);
        bid.setUserID(userID);

        bidDAO.createElement(bid);
    }

    private Map<Item, Bid> search(String selection, String searchField) {

        Map<Item, Bid> elemets = new HashMap<>();
        if (UID.equals(selection)) {
            Item item = itemDAO.getItemByID(Long.parseLong(searchField));
            if (item != null)
                elemets.put(item, bidDAO.getBestBid(item));
        } else if (TITLE.equals(selection)) {
            List<Item> items = itemDAO.findItemByNameSubstr(searchField);
            for (Item item : items) {
                elemets.put(item, bidDAO.getBestBid(item));
            }
        } else if (DESCRIPTION.equals(selection)) {
            List<Item> items = itemDAO.findItemByDescriptionSubstr(searchField);
            for (Item item : items) {
                elemets.put(item, bidDAO.getBestBid(item));
            }
        }
        return elemets;
    }

}
