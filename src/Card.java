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

    public String face(int value, String suit) {
        String face = "";
        face += ("+-----------+" + "\n");
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 3; j++) {
                if(j == 0 && i != 3) {
                    face += ("|  " + " ");
                } else if(j == 1 && i == 1) {
                    if(value >= 10) {
                        face += (" " + value + " ");
                    } else {
                        face += ("  " + value + " ");
                    }
                } else if (i != 3) {
                    face += ("   " + " ");
                }

                if (j == 0 && i == 3) {
                    if(suit.equals("Clubs")) {
                        face += ("|   CLUBS   ");
                    } else if(suit.equals("Diamonds")) {
                        face += ("| DIAMONDS  ");
                    } else if(suit.equals("Hearts")) {
                        face += ("|   HEARTS  ");
                    } else if(suit.equals("Spades")) {
                        face += ("|   SPADES  ");
                    }
                }
            }
            face += ("|");
            face += ("\n");
        }
        face += ("+-----------+"  + "\n");
        return face;
    }
}