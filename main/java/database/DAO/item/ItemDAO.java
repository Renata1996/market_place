package database.DAO.item;

import database.DAO.DAO;
import entities.Item;

import java.util.List;

public interface ItemDAO extends DAO {

    List<Item> getAllItems();

    List<Item> findItemByNameSubstr(String nameSubstr);

    Item getItemByID(long uID);

    List<Item> findItemBySeller(String userLogin);

    void buyItem(Item item);


}
