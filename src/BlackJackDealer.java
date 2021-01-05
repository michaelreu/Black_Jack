import java.util.ArrayList;
import java.util.Collections;

public class BlackJackDealer implements Dealer {

    private ArrayList<Card> deck;
    private int index;

    public BlackJackDealer(ArrayList<Card> deck){
        this.deck = deck;
    }
    
    @Override
    public Card getCard() {
        Card nextCard;
        if (this.deck.isEmpty()){
            throw new RuntimeException("No more cards in the deck");
        }
        else{
            nextCard = this.deck.get(this.index);
            this.index++;
            return nextCard;
        }

    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.deck);
        this.index = 0;
    }
    
}
