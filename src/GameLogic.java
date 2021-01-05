import java.util.ArrayList;

public class GameLogic extends TemplateGameLogic {

    private BlackJackDealer dealer;

    public GameLogic(BlackJackDeckFactory deckFactory, ArrayList<Player> players){
        
        this.dealer = new BlackJackDealer(deckFactory.CreateDeck());
        this.players = players;
        this.state = new State(players.get(0).getName(), players);

    }



    @Override
    public State playNextTurn() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public State whoWin() {
        ArrayList<Player> winners = new ArrayList<Player>();
        if (dealer.hasBlackJack()){
            return new State("" ,winners) ;
        }
        for (Player player : this.players) {
            if ((player.rank() >= this.dealer.rank()) && (player.rank() <= 21)){
                winners.add(player);
            }
        } 
        return new State("" ,winners) ;
    }

    @Override
    public State initGame(){
       this.dealer

    }
    
}
