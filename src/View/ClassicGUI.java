package View;

import Observer.Observable;
import model.State;

public class ClassicGUI extends Observable implements Igui {
	State state;

	public ClassicGUI() {
		state = State.INIT;
	}

	@Override
	public void getUserCommand(Observable obj) {
        return;
	}

	@Override
	public void addPlayers() {
        return;
	}

	@Override
	public void makeBet(Observable obj) {
        return;
	}

	@Override
	public void declareWinners(Observable obj) {
        return;
	}
}


