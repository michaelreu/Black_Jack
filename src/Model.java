import java.util.ArrayList;
import java.util.Map;




abstract class Model extends Observable {
    protected ArrayList<Player> players;
    protected ArrayList<Player> winnersRound;


    public abstract void initGame();
    public abstract void addPlayers(Map<String, String> data);
    public abstract void addBets(Map<String, String> data);
    public abstract void playNextTurn(Map<String, String> data);
    public abstract ArrayList<Player> getWinnersRound();

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
