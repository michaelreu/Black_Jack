import java.util.ArrayList;

public class BlackJackDealer implements Dealer {

    private AbstractDeck deck; 
    private ArrayList<Card> dealerCards;

    public BlackJackDealer(BlackJackDeckFactory deckFactory){
        this.deck = deckFactory.CreateDeck();
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
