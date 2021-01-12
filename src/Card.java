import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Card implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int DIAMOND = 1;
    public static final int HEART = 2;
    public static final int SPADE = 3;
    public static final int CLUB = 4;

    private int rank;
    private int suit;

    public Card(int rank, int suit) {
        if (rank < 1 || rank > 13 || suit < 1 || suit > 4)
        {
            throw new RuntimeException("Bad Card initialization value.");
        }
        this.rank = rank;
        this.suit = suit;
    }

    int getRank(){
        return rank;
    }

    int getSuit(){
        return suit;
    }

    public Card makeClone() {
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(this);
            
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream in = new ObjectInputStream(inputStream);
            Card copied = (Card) in.readObject();
            return copied;
        } catch (Exception e) {
            return null;
        }
    }
    
}