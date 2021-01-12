package model;

public class BlackJackDeck extends AbstractDeck{

    public BlackJackDeck(Integer size){
        super(size);
        while (size > 0){
            for (int suit  = 1; suit <= 4; suit++){
                for (int rank = 1; rank <= 13; rank++){
                    Card card = new Card(rank, suit);
                    super.cards.add(card);
                }
            }
            size--;
        }
    }
}


