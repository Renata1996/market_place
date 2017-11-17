package entities;

public class Bid {

    private long betID;
    private long userID;
    private long itemID;
    private long bid;

    public Bid() {
    }

    public Bid(long betID, long userID, long itemID, long bid) {
        this.betID = betID;
        this.userID = userID;
        this.itemID = itemID;
        this.bid = bid;
    }

    public Bid(long userID, long itemID, long bid) {
        this.userID = userID;
        this.itemID = itemID;
        this.bid = bid;
    }

    public long getBetID() {
        return betID;
    }

    public void setBetID(long betID) {
        this.betID = betID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }
}
