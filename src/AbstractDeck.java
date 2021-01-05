import java.util.ArrayList;
import java.util.List;


abstract class Deck {
    
    protected List<Card> cards;
    protected Integer size;

    public Deck(Integer size){
        this.size = size;
        cards = new ArrayList<>();
    }

    List<Card> getCards(){
        return this.cards;
    }
}
