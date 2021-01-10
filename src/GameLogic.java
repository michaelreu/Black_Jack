import java.util.ArrayList;
import java.util.Map;

public class GameLogic extends Model {

    private BlackJackDealer dealer;

    public GameLogic(BlackJackDeckFactory deckFactory, ArrayList<Player> players) {

        this.dealer = new BlackJackDealer(deckFactory.CreateDeck());
        this.players = players;
        this.state = State.BETS;
    }

    public void prepareGame() {
        this.state = State.BETS;
        notifyObservers();
        dealer.shuffle();
        for (int i = 0; i < 2; i++) {
            for (Player player : this.players) {
                player.addCard(this.dealer.getCard());
            }
            this.dealer.addCard(dealer.getCard());
        }

    }

    @Override
    public void whoWin() {
        // TODO

    }

    @Override
    public void initGame() {
        // TODO

    }

    @Override
    public void playNextTurn(Map<String, Object> data) {
        // TODO Auto-generated method stub

    }
    
}
