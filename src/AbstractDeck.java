import java.util.Collections;
import java.util.Stack; 


abstract class AbstractDeck {
    
    protected Stack<Card> cards;
    protected Integer size;

    public AbstractDeck(Integer size){
        this.size = size;
        cards = new Stack<>();
    }

    Stack<Card> getCards(){
        return this.cards;
    }

<<<<<<< HEAD
    void shuffle(){
        Collections.shuffle(cards);
    }

    Card pop(){
        return cards.pop();
    }
=======
	public boolean isEmpty() {
		return false;
	}
>>>>>>> bla bla
}
