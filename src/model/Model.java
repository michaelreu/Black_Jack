package model;

import java.util.ArrayList;
import java.util.Map;

import Observer.Observable;


public abstract class Model extends Observable {
    protected ArrayList<Player> players;
    protected ArrayList<Player> winnersRound;

    public abstract Dealer getDealer();
    public abstract void clearLastHand();
    public abstract void addPlayers(Map<String, String> data);
    public abstract void addBets(Map<String, String> data);
    public abstract void playNextTurn(Map<String, String> data);
    public abstract ArrayList<Player> getWinnersRound();

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
