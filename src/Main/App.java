package Main;

import View.ConsoleGUI;
import controller.Board;
import model.BlackJackDeckFactory;
import model.BlackJackLogic;
import model.Model;



public class App {
    public static void main(String[] args) throws Exception {
        BlackJackDeckFactory deckFactory = new BlackJackDeckFactory();
        Model gameLogic = new BlackJackLogic(deckFactory);
        ConsoleGUI gui = new ConsoleGUI();
        Board board = new Board(gui, gameLogic);
        gameLogic.register(board);
        gui.register(board);
        try {
            board.runGame();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
