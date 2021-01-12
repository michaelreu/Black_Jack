import java.util.ArrayList;

public class Player implements Cloneable {
    private String name;
    private int amount;
    private Boolean stay;
    private ArrayList<Card> hand;
    private int betSize;
    private int currTotalRank;

    public Player(String name, int amount) {
        this.name = name;
        this.amount = amount;
        this.stay = false;
        this.hand = new ArrayList<Card>();
        this.currTotalRank = 0;

    }

    public void addCard(Card card) {
        this.hand.add(card);
        currTotalRank += card.getRank();
    }

    public int getTotalRank() {
        return currTotalRank;
    }

    public void betSize(int bet) {
        if (bet > this.amount) {
            this.betSize = this.amount;
        } else {
            this.betSize = bet;
        }
    }

    public String getName() {
        return name;
    }

    public Boolean getStay() {
        return stay;
    }

    public void setStay(Boolean stay) {
        this.stay = stay;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getBetSize() {
        return betSize;
    }

    public void setBetSize(int betSize) {
        this.betSize = betSize;
    }

    public void emptyHand() {
        this.hand.removeAll(this.hand);
        currTotalRank = 0;
    }

    public void lost_round() {
        this.amount -= this.betSize;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}