import java.util.Map;

public class Board implements Iobserver {

    Igui gui;
    Model gameLogic;

    public Board(Igui ui, Model gl) {
        gui = ui;
        gameLogic = gl;
    }

    @Override
    public void update(Observable obj) {
            switch (obj.state) {
                case BETS:
                    
                    break;
                case PLAYTURN:
                    
                    break;
                case END_GAME:

                    break;
                default:
                    break;
            }
    }

    @Override
    public void update(Observable obj, Map<String, Object> data) {
        switch (obj.state) {
            case BETS:
                gui.makeBet((Model) obj);
                break;
            case PLAYTURN:
                gui.getUserCommand((Model) obj);
                break;
            case END_GAME:

                break;
            default:
                break;
        }

    }
}
