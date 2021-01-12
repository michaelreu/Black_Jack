package View;

import Observer.Observable;
import model.State;

public class LightGui extends Observable implements Igui {
	State state;

	public LightGui() {
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


