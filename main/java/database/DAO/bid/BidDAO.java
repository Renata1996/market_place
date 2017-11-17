package database.DAO.bid;

import database.DAO.DAO;
import entities.Bid;
import entities.Item;

import java.util.List;

public interface BidDAO extends DAO {

    List<Bid> getBids(Item item);


    Bid getBestBid(Item item);
}
