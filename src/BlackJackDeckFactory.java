import java.util.ArrayList;

public class BlackJackDeckFactory extends DeckFactory {

    @Override
    public AbstractDeck CreateDeck() {
        BlackJackDeck blackJackDeck = new BlackJackDeck(5);
        return blackJackDeck;
    }
}

    
