package database.DAO.bid;

import database.DAO.Util;
import entities.Bid;
import entities.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BidDAOImpl implements BidDAO {

    private static final String SELECT_BY_ITEMID = "SELECT * FROM Bid WHERE itemID=";
    private static final String CREATE_BID = "INSERT INTO Bid (userID, itemID,bid) VALUES (";
    private static final String SELECT_BEST_BID = "SELECT * FROM Bid WHERE Bid.itemID =";
    private static final String ORDER_BY_BEST_BID = " ORDER BY bid DESC";
    private static final String ITEMID = "itemID";
    private static final String BIDID = "bidID";
    private static final String USERID = "userID";
    private static final String BID = "bid";

    private Util util = new Util();

    public BidDAOImpl() {
    }

    public List<Bid> getBids(Item item) {
        List<Bid> bids = new ArrayList<>();
        String query = SELECT_BY_ITEMID + item.getItemID();

        try (ResultSet resultSet = util.getResultSet(query)) {
            while (resultSet.next()) {
                bids.add(makeBid(resultSet));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bids;
    }


    public Bid getBestBid(Item item) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_BEST_BID).append(item.getItemID()).append(ORDER_BY_BEST_BID);
        Bid bid = null;
        try (ResultSet resultSet = util.getResultSet(query.toString())) {
            resultSet.next();
            bid = makeBid(resultSet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bid;
    }

    public void createElement(Object o) {
        Bid bid = (Bid) o;
        StringBuilder query = new StringBuilder();
        query.append(CREATE_BID).append(bid.getUserID())
                .append(",").append(bid.getItemID())
                .append(",").append(bid.getBid()).append(")");
        util.makeQuery(query.toString());

    }

    private Bid makeBid(ResultSet resultSet) {
        Bid bid = null;
        try {
            bid = new Bid();
            bid.setBetID(resultSet.getInt(BIDID));
            bid.setUserID(resultSet.getInt(USERID));
            bid.setItemID(resultSet.getInt(ITEMID));
            bid.setBid(resultSet.getInt(BID));
        } catch (SQLException e) {

        }
        return bid;
    }
}
