import java.util.ArrayList;
import java.util.List;


abstract class AbstractDeck {
    
    protected List<Card> cards;
    protected Integer size;

    public AbstractDeck(Integer size){
        this.size = size;
        cards = new ArrayList<>();
    }

    List<Card> getCards(){
        return this.cards;
    }
}
