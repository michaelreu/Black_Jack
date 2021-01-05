public class Card {
    public static final int DIAMOND = 1;
    public static final int HEART = 2;
    public static final int SPADE = 3;
    public static final int CLUB = 4;

    private int rank;
    private int suit;

    public Card(int rank, int suit) {
        if (rank < 1 || rank > 13 || suit < 1 || suit > 4)
        {
            throw new RuntimeException("Bad Card initialization value.");
        }
        this.rank = rank;
        this.suit = suit;
    }

    int getRank(){
        return rank;
    }

    int getSuit(){
        return suit;
    }
}