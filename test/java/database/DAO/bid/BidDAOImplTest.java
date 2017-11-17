package database.DAO.bid;

import entities.Bid;
import entities.Item;
import junit.framework.TestCase;

import java.util.List;

public class BidDAOImplTest extends TestCase {

    private  BidDAO bidDAO = new BidDAOImpl();
    private Item item = new Item(2);
    private Bid bid = new Bid(1,1,50);


    public void testGetBids() throws Exception {
        bidDAO.createElement(bid);
        List<Bid> bids = bidDAO.getBids(item);
        for (Bid bid1 : bids)
            if(bid1.getUserID() == bid.getUserID() &&
                    bid1.getItemID() == bid.getItemID() && bid.getBid() == bid1.getBid()){
                assertTrue(true);
            }
    }


    public void testCreateBid() throws Exception {
        bidDAO.createElement(bid);
        List<Bid> bids = bidDAO.getBids(item);
        for (Bid bid1 : bids)
            if(bid1.getUserID() == bid.getUserID() &&
                    bid1.getItemID() == bid.getItemID() && bid.getBid() == bid1.getBid()){
                assertTrue(true);

            }
    }

    public void testGetBestBid() throws Exception {
            BidDAO bidDAO = new BidDAOImpl();
            bidDAO.createElement(bid);
            assertTrue(bid.getBid()==bidDAO.getBestBid(item).getBid());
    }


}