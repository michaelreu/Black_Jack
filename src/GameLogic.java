import java.util.ArrayList;
import java.util.Map;


public class GameLogic extends Model {

    private BlackJackDealer dealer;
    private int init_amount;
 


    public GameLogic(BlackJackDeckFactory deckFactory) {

        dealer = new BlackJackDealer(deckFactory);
        players = new ArrayList<Player>();
        winnersRound = new ArrayList<Player>();
        state = State.INIT;
        init_amount = 500;
    }

    public void whoWin() {
        dealer.endTurn();
        for (Player player : players) {
            if(dealer.isPlayerHandWon(player.getHand())){
                winnersRound.add(player);
                player.setAmount(player.getBetSize() * 2);
            }
        }
    }

    private void clearLastHand(){
        for (Player player : players) {
            player.setBetSize(0);
            player.emptyHand();
            player.setStay(false);
        }
        dealer.emptyHand();
        winnersRound.removeAll(winnersRound);
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
            if (dealer.rank(player.getHand()) > 21){
                player.setStay(true);
            }
        }
    }

    

    @Override
    public void initGame() {
        clearLastHand();
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
            whoWin();
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
                player.setStay(false);
            }
        }
        this.state = State.PLAYTURN;
        initGame();
    }

    public ArrayList<Player> getWinnersRound() {
        return winnersRound;
    }

    @Override
    public Dealer getDealer() {
        return dealer;
    }
}
