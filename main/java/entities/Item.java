package entities;

public class Item {
    private long itemID;
    private String title;
    private String description;
    private long sellerID;
    private int startPrice;
    private int stepRate;
    private long timeLeft;
    private boolean byNowFlag;
    private boolean isSold;

    public Item() {
    }

    public Item(long itemID, String title, String description, long sellerID, int startPrice,
                int stepRate, long timeLeft, boolean byNowFlag, boolean isSold) {
        this.itemID = itemID;
        this.title = title;
        this.description = description;
        this.sellerID = sellerID;
        this.startPrice = startPrice;
        this.stepRate = stepRate;
        this.timeLeft = timeLeft;
        this.byNowFlag = byNowFlag;
        this.isSold = isSold;
    }

    public Item(long itemID) {
        this.itemID = itemID;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getStepRate() {
        return stepRate;
    }

    public void setStepRate(int stepRate) {
        this.stepRate = stepRate;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(long date) {
        this.timeLeft = date;
    }

    public boolean isByNowFlag() {
        return byNowFlag;
    }

    public void setByNowFlag(boolean byNowFlag) {
        this.byNowFlag = byNowFlag;
    }

    public long getSellerID() {
        return sellerID;
    }

    public void setSellerID(long sellerID) {
        this.sellerID = sellerID;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (itemID != item.itemID) return false;
        if (sellerID != item.sellerID) return false;
        if (startPrice != item.startPrice) return false;
        if (stepRate != item.stepRate) return false;
        if (timeLeft != item.timeLeft) return false;
        if (byNowFlag != item.byNowFlag) return false;
        if (isSold != item.isSold) return false;
        if (title != null ? !title.equals(item.title) : item.title != null) return false;
        return description != null ? description.equals(item.description) : item.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (itemID ^ (itemID >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (sellerID ^ (sellerID >>> 32));
        result = 31 * result + startPrice;
        result = 31 * result + stepRate;
        result = 31 * result + (int) (timeLeft ^ (timeLeft >>> 32));
        result = 31 * result + (byNowFlag ? 1 : 0);
        result = 31 * result + (isSold ? 1 : 0);
        return result;
    }
}
