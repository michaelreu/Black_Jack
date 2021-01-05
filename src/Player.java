import java.util.ArrayList;


public class Player {
    private String name;
    private int amount;
    private Boolean status;
    private ArrayList<Card> hand;
    private int betSize;

    public void addCard(Card card){
        this.hand.add(card);
    }

    public void betSize(int bet){
        if (bet > this.amount){
            this.betSize = this.amount;
        }
        else{
            this.betSize = bet;
        }
    }

    public String getName() {
        return name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
    }

    public void lost_round() {
        this.amount -= this.betSize;
    }
}