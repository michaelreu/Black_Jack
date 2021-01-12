package Main;

import View.ConsoleGUI;
import controller.Controller;
import model.BlackJackDeckFactory;
import model.BlackJackLogic;
import model.Model;



public class App {
    public static void main(String[] args) throws Exception {
        BlackJackDeckFactory deckFactory = new BlackJackDeckFactory();
        Model gameLogic = new BlackJackLogic(deckFactory);
        ConsoleGUI gui = new ConsoleGUI();
        Controller controller = new Controller(gui, gameLogic);
        gameLogic.register(controller);
        gui.register(controller);
        try {
            controller.runGame();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
