package controller;

import java.util.Map;

import Observer.Iobserver;
import Observer.Observable;
import View.Igui;
import controller.Controller;
import model.Model;

public class Controller implements Iobserver {

    Igui gui;
    Model gameLogic;

    public Controller(Igui ui, Model gl) {
        gui = ui;
        gameLogic = gl;
    }

    public void runGame(){
        gui.addPlayers();
    }


    @Override
    public void update(Observable obj) {
        switch (obj.state) {
            case INIT:
                gui.addPlayers();
                break;
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
                gameLogic.clearLastHand();
                break;
            default:
                break;
        }

    }
}
