import java.util.ArrayList;

abstract class TemplateGameLogic extends Observable {
    protected State state;
    protected ArrayList<Player> players;

    public abstract State initGame();
    public abstract State playNextTurn();
    public abstract State whoWin();

}
