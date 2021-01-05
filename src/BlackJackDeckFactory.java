public class BlackJackDeckFactory extends DeckFactory{

    @Override
    public Deck CreateDeck() {
        Deck blackJackDeck = new BlackJackDeck();
        return blackJackDeck;
    }
}
    
