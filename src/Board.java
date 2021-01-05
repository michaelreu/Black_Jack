public class Board implements Iobserver{

    Igui gui;
    TemplateGameLogic gameLogic;

    public Board(Igui ui, TemplateGameLogic gl){
        gui = ui;
        gameLogic = gl;
    }

    @Override
    public void update(Observable obj) {
        if (obj == gui){
            String input = gui.getUserCommand();
        }
        else if (obj == gameLogic){
            gui.draw(gameLogic.getState());
        }
        else{
            //TODO throw something..
        }
    }
}
