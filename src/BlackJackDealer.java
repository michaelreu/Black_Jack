import java.util.ArrayList;
import java.util.Collections;

public class BlackJackDealer implements Dealer {

    private AbstractDeck deck; 
    private ArrayList<Card> dealerCards;

    public BlackJackDealer(AbstractDeck deck){
        this.deck = deck;
        this.dealerCards = new ArrayList<Card>();
    }
    
    @Override
    public Card getCard() {
        Card nextCard;
        if (this.deck.isEmpty()){
            throw new RuntimeException("No more cards in the deck");
        }
        else{
            nextCard = this.deck.pop();
            return nextCard;
        }

    }

    @Override
    public void shuffle() {
        this.deck.shuffle();
    }
    
    int rank(){
        //TODO
    }

    boolean hasBlackJack(){
        //TODO asdasd
    }

    public void addCard(Card card){
        this.dealerCards.add(card);
    }

    public ArrayList<Card> getHand() {
        return dealerCards;
    }

    public void emptyHand() {
        this.dealerCards.removeAll(this.dealerCards);
    }

}
