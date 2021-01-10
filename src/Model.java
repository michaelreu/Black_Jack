import java.util.ArrayList;
import java.util.Map;




abstract class Model extends Observable {
    protected ArrayList<Player> players;


    public abstract void initGame();
    public abstract void playNextTurn(Map<String, Object> data);
    public abstract void whoWin();

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
