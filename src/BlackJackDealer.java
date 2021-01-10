import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    
    int rank(ArrayList<Card> cards){
        int sumCards = 0; 
        //TODO check that the cards are sorted in DESCENDING order.
        Comparator<Card> compareByRank = (Card c1, Card c2) -> c1.getRank() - c2.getRank();
        Collections.sort(cards,compareByRank);

        for (Card card : cards){
            int rank = card.getRank();
            if (rank == 11 || rank == 12 || rank == 13){
                sumCards += 10;
            }else if(rank == 1){
                if (sumCards + 11 <= 21){
                    sumCards += 11;
                }else{
                    sumCards += 1;
                }
            }else{
                sumCards += rank;
            }
        }
        return sumCards;
    }

    boolean hasBlackJack(ArrayList<Card> cards){
        return rank(cards) == 21;
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

    boolean isPlayerHandWon(ArrayList<Card> playerCards){
        if (rank(playerCards) > rank(dealerCards)){
            return true;
        }else{
            return false;
        }
    }

    void endTurn() {
        while(rank(dealerCards) < 17){
            dealerCards.add(getCard());
        }
    }

}
