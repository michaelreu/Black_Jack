import java.util.Map;

public class Board implements Iobserver {

    Igui gui;
    Model gameLogic;

    public Board(Igui ui, Model gl) {
        gui = ui;
        gameLogic = gl;
    }

    public void runGame(){
        gui.addPlayers();
    }


    @Override
    public void update(Observable obj) {
        switch (obj.state) {
            case BETS:
                gui.makeBet(obj);
                break;
            case PLAYTURN:
                gui.getUserCommand(obj);
                break;
            case END_GAME:
                gui.declareWinners(obj);
                break;
            default:
                break;
        }
    }

    @Override
    public void update(Observable obj, Map<String, String> data) {
        switch (obj.state) {
            case INIT:
                gameLogic.addPlayers(data);
                break;
            case BETS:
                gameLogic.addBets(data);
                break;
            case PLAYTURN:
                gameLogic.playNextTurn(data);
                break;
            case END_GAME:

                break;
            default:
                break;
        }

    }
}
