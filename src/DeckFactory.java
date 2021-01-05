
public abstract class DeckFactory {
    public Deck getDeck(){
        Deck myDeck = this.CreateDeck();
        return myDeck;
    }

    public abstract Deck CreateDeck();

}


