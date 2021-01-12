package model;

public abstract class DeckFactory {
    public AbstractDeck getDeck(){
        AbstractDeck myDeck = this.CreateDeck();
        return myDeck;
    }

    public abstract AbstractDeck CreateDeck();

}


