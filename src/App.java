
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        BlackJackDeck deck = new BlackJackDeck(2);
        List<Card> cards = deck.getCards();
    }
}
