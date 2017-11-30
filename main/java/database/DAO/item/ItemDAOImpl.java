package database.DAO.item;

import database.DAO.Util;
import entities.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private static final String SELECT_ALL_ITEMS = "SELECT * FROM Item";
    private static final String SELECT_BY_ID = "SELECT * FROM Item WHERE itemID=";
    private static final String SELECT_SUBSTR = "SELECT * FROM Item WHERE title LIKE '%";
    private static final String SELECT_SUBSTR_DESCRIPTION = "SELECT * FROM Item WHERE description LIKE '%";
    private static final String SELECT_ITEM_BY_SELLER = "SELECT * FROM Item INNER JOIN Users ON Users.userID = Item.sellerID WHERE Users.login LIKE '%";
    private static final String INSERT_INTO_ITEMS = "INSERT INTO Item (title,description,sellerID,startPrice,stepRate,timeLeft,buyItNow,isbuying) VALUES('";
    private static final String BUY_ITEM = "UPDATE Item SET isSold = 1 WHERE itemID=";
    private static final String UPDATE_ITEM_BY_ID = "UPDATE Item SET title ='";
    private static final String ITEM_ID = "itemID";
    private static final String TITLE = "title";
    private static final String SELLER_ID = "sellerID";
    private static final String START_PRICE = "startPrice";
    private static final String STEP_RATE = "stepRate";
    private static final String TIME_LEFT = "timeLeft";
    private static final String BUY_IT_NOW = "buyItNow";
    private static final String DESCRIPTION = "description";
    private static final String IS_BUYING = "isSold";
    private static final String DELETE_ITEM = "DELETE FROM Item WHERE itemID=";

    private Util util = new Util();

    public ItemDAOImpl() {
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try (ResultSet resultSet = util.getResultSet(SELECT_ALL_ITEMS)) {
            while (resultSet.next()) {
                items.add(makeItem(resultSet));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return items;
    }

    public List<Item> findItemByNameSubstr(String nameSubstr) {
        List<Item> items = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append(SELECT_SUBSTR).append(nameSubstr).append("%'");

        try (ResultSet resultSet = util.getResultSet(query.toString())) {
            while (resultSet.next()) {
                items.add(makeItem(resultSet));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return items;
    }

    public List<Item> findItemByDescriptionSubstr(String nameSubstr) {
        List<Item> items = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append(SELECT_SUBSTR_DESCRIPTION).append(nameSubstr).append("%'");

        try (ResultSet resultSet = util.getResultSet(query.toString())) {
            while (resultSet.next()) {
                items.add(makeItem(resultSet));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return items;
    }

    public Item getItemByID(long uID) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_BY_ID).append(uID);
        Item item = null;

        try (ResultSet resultSet = util.getResultSet(query.toString())) {
            while (resultSet.next()) {
                item = makeItem(resultSet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return item;
    }

    public List<Item> findItemBySeller(String userLogin) {
        List<Item> items = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append(SELECT_ITEM_BY_SELLER).append(userLogin).append("%'");

        try (ResultSet resultSet = util.getResultSet(query.toString())) {
            while (resultSet.next()) {
                items.add(makeItem(resultSet));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return items;
    }

    public void createElement(Object o) {
        Item item = (Item) o;
        StringBuilder query = new StringBuilder();
        query.append(INSERT_INTO_ITEMS).append(item.getTitle()).append("', '").append(item.getDescription()).append("', '").append(item.getSellerID())
                .append("', '").append(item.getStartPrice()).append("', '").append(item.getStepRate()).append("', '").append(item.getTimeLeft())
                .append("', '").append(!item.isByNowFlag() ? 0 : 1).append("', '").append(!item.isSold() ? 0 : 1).append("')");
        util.makeQuery(query.toString());
    }

    public void buyItem(Item item) {

        StringBuilder query = new StringBuilder();
        query.append(BUY_ITEM).append(item.getItemID());
        util.makeQuery(query.toString());
    }

    @Override
    public void editItem(Item item) {
        StringBuilder query = new StringBuilder();
        if(!item.isByNowFlag()){
            query.append(UPDATE_ITEM_BY_ID).append(item.getTitle()).append("', description = '").append(item.getDescription()).append("',startprice='")
                    .append(item.getStartPrice()).append("', timeLeft='").append(item.getTimeLeft()).append("',stepRate='").append(item.getStepRate()).append("',buyitnow='")
                    .append(item.isByNowFlag()?1:0).append("' WHERE itemId=").append(item.getItemID());
        }
       else{ query.append(UPDATE_ITEM_BY_ID).append(item.getTitle()).append("', description = '").append(item.getDescription()).append("',startprice='")
                .append(item.getStartPrice()).append("',buyitnow='")
                .append(item.isByNowFlag()?1:0).append("' WHERE itemId=").append(item.getItemID());
        }
        util.makeQuery(query.toString());

    }

    @Override
    public void delete(String itemID) {
        StringBuilder query = new StringBuilder();
        query.append(DELETE_ITEM).append(itemID);
        util.makeQuery(query.toString());
    }


    private Item makeItem(ResultSet resultSet) {
        Item item = null;
        try {
            item = new Item();
            item.setItemID(resultSet.getInt(ITEM_ID));
            item.setTitle(resultSet.getString(TITLE));
            item.setDescription(resultSet.getString(DESCRIPTION));
            item.setSellerID(resultSet.getInt(SELLER_ID));
            item.setStartPrice(resultSet.getInt(START_PRICE));
            item.setStepRate(resultSet.getInt(STEP_RATE));
            item.setTimeLeft(resultSet.getLong(TIME_LEFT));
            item.setByNowFlag(resultSet.getInt(BUY_IT_NOW) != 0);
            item.setSold(resultSet.getInt(IS_BUYING) != 0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return item;
    }

}
