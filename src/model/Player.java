package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    public enum PlayerStatus {
        PLAY,
        STAY,
        OUTOFRANGE
      }

    private static final long serialVersionUID = 1L;
    private String name;
    private int amount;
    private ArrayList<Card> hand;
    private int betSize;
    private PlayerStatus status;

    public Player(String name, int amount) {
        this.name = name;
        this.amount = amount;
        this.status = PlayerStatus.PLAY;
        this.hand = new ArrayList<Card>();

    }

    public void addCard(Card card) {
        this.hand.add(card);
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Player makeClone() {
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(this);
            
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream in = new ObjectInputStream(inputStream);
            Player copied = (Player) in.readObject();
            return copied;
        } catch (Exception e) {
            return null;
        }
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }
    

}