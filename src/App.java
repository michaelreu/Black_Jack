import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        BlackJackDeckFactory deckFactory = new BlackJackDeckFactory();
        Model gameLogic = new GameLogic(deckFactory);
        ConsoleGUI gui = new ConsoleGUI();
        Board board = new Board(gui, gameLogic);
        gameLogic.register(board);
        gui.register(board);
        board.runGame();
    }
}
