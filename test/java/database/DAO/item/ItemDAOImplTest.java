package database.DAO.item;

import entities.Item;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemDAOImplTest {


    private List<Item> itemList;
    private ItemDAO itemDAO;

    @Test
    public void getAllItems() throws Exception {
        createItems();
        itemDAO = new ItemDAOImpl();
        for (Item item : itemDAO.getAllItems()) {
            if (itemList.contains(item)) Assert.assertTrue(true);
        }

    }

    @Test
    public void findItemByNameSubstr() throws Exception {
        itemDAO = new ItemDAOImpl();
        String substr = "o";
        for (Item item : itemDAO.findItemByNameSubstr(substr)) {
            if (item.getTitle().contains(substr)) Assert.assertTrue(true);
        }

    }

    @Test
    public void findItemByID() throws Exception {
        itemDAO = new ItemDAOImpl();
        createItems();
        for (Item item : itemList) {
            Assert.assertTrue(itemDAO.getItemByID(item.getItemID()).equals(item));
        }
    }

    @Test
    public void findItemBySeller() throws Exception {
        itemDAO = new ItemDAOImpl();
        String substr = "a";
        for (Item item : itemDAO.findItemBySeller(substr)) {
            if (item.getTitle().contains(substr)) Assert.assertTrue(true);
        }
    }

    @Test
    public void createNewItem() throws Exception {
        createItems();
        itemDAO = new ItemDAOImpl();
        itemDAO.createElement(itemList.get(0));
        Assert.assertTrue(itemDAO.getAllItems().contains(itemList.get(0)));
    }

    @Test
    public void buyItem() throws Exception {
        createItems();
        itemList.get(0).setSold(true);
        itemDAO = new ItemDAOImpl();
        itemDAO.buyItem(itemList.get(0));
        Assert.assertTrue(true);

    }


    private void createItems() {
        itemList = new ArrayList<>();
        Item item = new Item();
        item.setItemID(1);
        item.setSellerID(1);
        item.setTitle("Паровоз");
        item.setDescription("большой многотонный монстр");
        item.setStartPrice(100000);
        item.setStepRate(10);
        item.setTimeLeft(0);
        item.setSold(false);
        item.setByNowFlag(false);
        itemList.add(item);

    }

}