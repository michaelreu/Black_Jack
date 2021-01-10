import java.util.ArrayList;
import java.util.Map;


public class GameLogic extends Model {

    private BlackJackDealer dealer;
    private int init_amount;
    private ArrayList<Player> winnersRound;


    public GameLogic(BlackJackDeckFactory deckFactory) {

        dealer = new BlackJackDealer(deckFactory.CreateDeck());
        players = new ArrayList<Player>();
        winnersRound = new ArrayList<Player>();
        state = State.INIT;
        init_amount = 500;
    }

    @Override
    public void whoWin(Map<String, String> data) {
        dealer.endTurn();
        for (Player player : players) {
            if(dealer.PlayerHandWon()){
                winnersRound.add(player);
            }
        }
        return winnersRound;
    }


    private Boolean checkEndGame(){
        for (Player player : players) {
            if(!player.getStay()){
                return false;
            }
        }
        return true;
    }



    public Player getPlayerByName(String playerName){
        for (Player player : players) {
            if(player.getName() == playerName){
                return player;
            }
        }
        return null;
    }


    public void platOneTurn(Player player, String command){
        if(player.getStay()){
            return;
        }
        if (command.equals("stay")){
            player.setStay(true);
        }else{
            player.addCard(dealer.getCard());
            if (player.rank() > 21){
                player.setStay(true);
            }
        }
    }

    

    @Override
    public void initGame() {
        dealer.shuffle();
        for (int i = 0; i < 2; i++) {
            for (Player player : this.players) {
                player.addCard(this.dealer.getCard());
            }
            this.dealer.addCard(dealer.getCard());
        }
        this.state = State.PLAYTURN;
        notifyObservers();
    }

    @Override
    public void playNextTurn(Map<String, String> data) {
        Player player;
        String playerName;
        String command;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            playerName = entry.getKey();
            command = entry.getValue();
            player = getPlayerByName(playerName);
            if (player != null){
                platOneTurn(player, command);
            }
        }
        if(checkEndGame()){
            state = State.END_GAME;
        }
        notifyObservers();
    }

    @Override
    public void addPlayers(Map<String, String> data) {
        for (String playerName : data.keySet()) {
            players.add(new Player(playerName, init_amount));
        }
        this.state = State.BETS;
        notifyObservers();
    }

    @Override
    public void addBets(Map<String, String> data) {
        Player player;
        String playerName;
        int betSize;
        int playerAmount;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            playerName = entry.getKey();
            betSize = Integer.parseInt(entry.getValue());
            player = getPlayerByName(playerName);
            if (player != null){
                playerAmount = player.getAmount();
                player.setBetSize(betSize);
                player.setAmount(playerAmount - betSize);
                player.setstay(false);
            }
        }
        this.state = State.PLAYTURN;
        notifyObservers();
    }

}
