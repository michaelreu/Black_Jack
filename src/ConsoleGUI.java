import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleGUI extends Observable implements Igui {
	Scanner sc = new Scanner(System.in);

    @Override
    public void draw() {}

	@Override
	public void getUserCommand(Observable obj) {
		Model myModel = (Model) obj;
		this.state =  State.PLAYTURN;
		Map<String, Object> data = new HashMap<String, Object>();
		int userCommand;
		ArrayList<Card> hand;
		for (Player playerTurn : myModel.players){
			if (playerTurn.getStay()){
				continue;
			}
			hand = playerTurn.getHand();
			System.out.println(playerTurn.getName() + ": your hand:");
			for (Card card : hand) {
				System.out.println(face(card.getRank(), card.getSuit()));
			}
			do {
				System.out.println("press '1' to take one more card or '0' to stay");
				while (!sc.hasNextInt()) {
					System.out.println("That's not a number!");
					sc.next(); // this is important!
				}
				userCommand = sc.nextInt();
			} while (userCommand != 0 || userCommand != 1);

			if (userCommand == 0){
				System.out.println("you decided to stay, please wait to the other players bets");
			}else{
				System.out.println("you choose to get one more card");
			}
			data.put(playerTurn.getName(), userCommand);
		}
		notifyObservers(data);
	}

	@Override
	public void startGame() {
		int index = 1;
		Map<String, Object> data = new HashMap<String, Object>();
		System.out.println("\nWelcome to theBlack Jack game!");
		System.out.print("Enter player " + index + "name");
		String name = System.console().readLine().trim();
		while(true){
			data.put(name, name);
			System.out.print("Enter player " + index + "name or '0' to start to play");
			name = System.console().readLine().trim();
			if (name == "0"){
				break;
			}
		}
		notifyObservers(data);
	}

	@Override
	public void makeBet(Observable obj) {
		Model myModel = (Model) obj;
		Map<String, Object> data = new HashMap<String, Object>();
		int betSize = 0;
		for (Player player : myModel.getPlayers()) {	
			do {
				System.out.print(player.getName() + " your balance is: " + player.getAmount() +"\n please enter your bet");
				while (!sc.hasNextInt()) {
					System.out.println("That's not a number!");
					sc.next(); // this is important!
				}
				betSize = sc.nextInt();
			} while (betSize <= 0);
			data.put(player.getName(), betSize);
			System.out.print(player.getName() + "your bet is: " + player.getBetSize() +"good luck!");
		}   
		notifyObservers(data);
	}

	private String face(int value, int suit) {
        String face = "";
        face += ("+-----------+" + "\n");
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 3; j++) {
                if(j == 0 && i != 3) {
                    face += ("|  " + " ");
                } else if(j == 1 && i == 1) {
                    if(value >= 10) {
                        face += (" " + value + " ");
                    } else {
                        face += ("  " + value + " ");
                    }
                } else if (i != 3) {
                    face += ("   " + " ");
                }

				if (j == 0 && i == 3) {
					if(suit == 1) {
						face += ("| DIAMONDS  ");
					} else if(suit == 2) {
						face += ("|   SPADES  ");
					} else if(suit == 3) {
						face += ("|   HEARTS  ");
					} else if(suit == 4) {
						face += ("|   CLUBS   ");
					}
				}
            }
            face += ("|");
            face += ("\n");
        }
        face += ("+-----------+"  + "\n");
        return face;
    }

}


