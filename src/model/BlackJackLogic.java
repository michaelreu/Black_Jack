package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;


public class BlackJackLogic extends Model {

    private BlackJackDealer dealer;
    private int init_amount;

    public BlackJackLogic(BlackJackDeckFactory deckFactory) {

        dealer = new BlackJackDealer(deckFactory);
        players = new ArrayList<Player>();
        winnersRound = new ArrayList<Player>();
        state = State.INIT;
        init_amount = 500;
    }

    public void whoWin() {
        endTurn();
        for (Player player : players) {
            if (isPlayerHandWon(player.getHand())) {
                winnersRound.add(player);
                player.setAmount(player.getAmount() + (player.getBetSize() * 2));
            }
        }
    }

    public void clearLastHand() {
        
        dealer.emptyHand();
        winnersRound.removeAll(winnersRound);
        if(noPlayersChecker()){
            players.clear();
            this.state = State.INIT;
        }else{
            for (Player player : players) {
                player.setBetSize(0);
                player.emptyHand();
                player.setStatus(Player.PlayerStatus.PLAY);
            }
            this.state = State.BETS;
        }
        
        notifyObservers();
    }


    private Boolean noPlayersChecker(){
        for (Player player : players) {
            if(player.getAmount() > 0){
                return false;
            }
        }
        return true;
    }


    private Boolean checkEndGame() {
        for (Player player : players) {
            if (player.getStatus() == Player.PlayerStatus.PLAY) {
                return false;
            }
        }
        return true;
    }

    public Player getPlayerByName(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    public void playOneTurn(Player player, String command) {
        if (player.getStatus() == Player.PlayerStatus.OUTOFRANGE || player.getStatus() == Player.PlayerStatus.STAY) {
            return;
        }
        if (command.equals("stay")) {
            player.setStatus(Player.PlayerStatus.STAY);
        } else {
            player.addCard(dealer.getCard());
            if (rank(player.getHand()) > 21){
                player.setStatus(Player.PlayerStatus.OUTOFRANGE);
            }
        }
    }

    public void dealFirstCards() {
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
            if (player != null) {
                playOneTurn(player, command);
            }
        }
        if (checkEndGame()) {
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
        if(data.isEmpty()){
            return;
        }
        for (Map.Entry<String, String> entry : data.entrySet()) {
            playerName = entry.getKey();
            betSize = Integer.parseInt(entry.getValue());
            player = getPlayerByName(playerName);
            if (player != null) {
                playerAmount = player.getAmount();
                if (betSize == 0) {
                    players.remove(player);
                    if (players.isEmpty()) {
                        return;
                    }
                    continue;
                }
                player.setBetSize(betSize);
                player.setAmount(playerAmount - betSize);
                player.setStatus(Player.PlayerStatus.PLAY);
            }
        }
        dealFirstCards();
    }

    public ArrayList<Player> getWinnersRound() {
        return winnersRound;
    }

    @Override
    public Dealer getDealer() {
        return dealer;
    }


    public ArrayList<Player> getOnlinePlayers(){
        ArrayList<Player> currPlayers = new ArrayList<Player>();
        Player curr;
        for (Player player : players) {
            try{
                curr = player.makeClone();
            }catch(Exception ex){
                curr = player;
            }
            if (rank(player.getHand()) > 21){
                player.emptyHand();
                player.setStatus(Player.PlayerStatus.OUTOFRANGE);
                curr.setStatus(Player.PlayerStatus.OUTOFRANGE);  

            }
            currPlayers.add(curr);
        }
        return currPlayers;
    }

    private boolean isPlayerHandWon(ArrayList<Card> playerCards){
        int playerRank = rank(playerCards);
        int dealerRank = rank(dealer.getHand());

        if (playerRank > 21 || playerRank == 0){
            return false;
        }else if((dealerRank > 21) || (playerRank > dealerRank)){
            return true;
        }else{
            return false;
        }
    }

    void endTurn() {
        while(rank(dealer.getHand()) < 17){
            dealer.addCard(dealer.getCard());
        }
    }

    int rank(ArrayList<Card> cards){
        int sumCards = 0; 
        Comparator<Card> compareByRank = (Card c1, Card c2) -> c2.getRank() - c1.getRank();
        Collections.sort(cards,compareByRank);

        for (Card card : cards){
            int rank = card.getRank();
            if (rank == 11 || rank == 12 || rank == 13){
                sumCards += 10;
            }else if(rank == 1){
                if (sumCards + 11 <= 21){
                    sumCards += 11;
                }else{
                    sumCards += 1;
                }
            }else{
                sumCards += rank;
            }
        }
        return sumCards;
    }
}
