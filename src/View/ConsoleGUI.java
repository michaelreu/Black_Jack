package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Observer.*;
import model.*;


public class ConsoleGUI extends Observable implements Igui {
	Scanner sc;

	public ConsoleGUI() {
		this.sc = new Scanner(System.in);
		state = State.INIT;
	}

	@Override
	public void getUserCommand(Observable obj) {
		BlackJackLogic myModel = (BlackJackLogic) obj;
		this.state = State.PLAYTURN;
		Map<String, String> data = new HashMap<String, String>();
		int userCommand;
		String command;
		ArrayList<Card> hand;
		for (Player playerTurn : myModel.getOnlinePlayers()) {
			hand = playerTurn.getHand();
			if (hand.isEmpty() || playerTurn.getStatus() == Player.PlayerStatus.STAY) {
				continue;
			}
			printHand(playerTurn.getName(), hand);
			if (playerTurn.getStatus() == Player.PlayerStatus.OUTOFRANGE) {
				System.out.println(playerTurn.getName() + ", you lost!! better luck next time...");
				continue;
			}

			do {
				System.out.println(playerTurn.getName() + ", press '1' to take one more card or '0' to stay");
				while (!sc.hasNextInt()) {
					System.out.println("That's not a number!");
					sc.next(); // this is important!
				}
				userCommand = sc.nextInt();
			} while (userCommand != 0 && userCommand != 1);

			if (userCommand == 0) {
				System.out
						.println(playerTurn.getName() + ", you decided to stay, please wait to the other players bets");
				command = "stay";
			} else {
				System.out.println(playerTurn.getName() + ", you choose to get one more card");
				command = "oneMore";
			}
			data.put(playerTurn.getName(), command);
			state = State.PLAYTURN;
			notifyObservers(data);

		}
	}

	@Override
	public void addPlayers() {
		int index = 1;
		Map<String, String> data = new HashMap<String, String>();
		startGame();
		System.out.println("Enter player " + index + " name ");
		String name = System.console().readLine().trim();
		while (true) {
			if(name != null && !name.trim().isEmpty() && !name.equals("0")){ 
				data.put(name, name);
				++index;
			}
			System.out.println("Enter player " + index + " name or '0' to start to play(at least 1 player)");
			name = System.console().readLine().trim();
			if((name.equals("0") && index > 1)){
				break;
			}
		}
		state = State.INIT;
		notifyObservers(data);
	}

	@Override
	public void makeBet(Observable obj) {
		Model myModel = (Model) obj;
		Map<String, String> data = new HashMap<String, String>();
		int betSize = 0;
		betMessage();
		for (Player player : myModel.getPlayers()) {
			do {
				if (player.getAmount() == 0) {
					System.out.println(player.getName() + ", Your Balance is Zero, please come back with money\n");
					betSize = 0;
					break;
				}
				System.out.println(
						player.getName() + ", your balance is: " + player.getAmount() + "\nplease enter your bet: ");
				while (!sc.hasNextInt()) {
					System.out.println("That's not a number!");
					sc.next();
				}
				betSize = sc.nextInt();
				if (betSize > player.getAmount()) {
					System.out.println(player.getName() + ",your bet is higher than your credit, please bet again!\n");
					betSize = 0;
				}
			} while (betSize <= 0);
			if (betSize > 0) {
				System.out.println(player.getName() + ", your bet is: " + betSize + " good luck!\n");
			}
			data.put(player.getName(), String.valueOf(betSize));
		}
		state = State.BETS;
		notifyObservers(data);
	}

	@Override
	public void declareWinners(Observable obj) {
		Map<String, String> data = new HashMap<String, String>();
		Model myModel = (Model) obj;
		BlackJackDealer dealer = (BlackJackDealer) myModel.getDealer();
		roundOverMessage();
		printHand("dealer", dealer.getHand());
		for (Player player : myModel.getPlayers()) {
			printHand(player.getName(), player.getHand());
			if (myModel.getWinnersRound().contains(player)) {
				System.out.println(player.getName() + " congratulations!!! you won " + player.getBetSize() * 2);
			} else {
				System.out.println(player.getName() + " you lost this round..\n");
			}
		}
		state = State.END_GAME;
		notifyObservers(data);
	}

	private void printHand(String name, ArrayList<Card> hand) {
		if (hand.isEmpty()) {
			return;
		}
		System.out.println(name + " Cards: ");
		for (Card card : hand) {
			System.out.println(face(card.getRank(), card.getSuit()));
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {}
		}
		System.out.println("------------------------------");
	} 

	private String face(int value, int suit) {
	    String strVal;
        String face = "";
		if(value == 11){
			strVal = "J";
		}else if(value == 12){
			strVal = "Q";
		}else if(value == 13){
			strVal = "K";
		}else if(value == 1){
			strVal = "A";
		}else{
			strVal = String.valueOf(value);
		}
        face += ("+----------+" + "\n");
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                if(j == 0 && i != 1) {
                    face += ("|  " + "  ");
                } else if(j == 1 && i == 0) {
                    if(value == 10) {
                        face += ("" + strVal + " ");
                    } else {
                    face += ("" + strVal + "  ");
                    }
                } else if (i != 1) {
                    face += ("  " + " ");
                }

				if (j == 0 && i == 1) {
					if(suit == 1) {
						face += ("| DIAMONDS ");
					} else if(suit == 2) {
						face += ("|  SPADES  ");
					} else if(suit == 3) {
						face += ("|  HEARTS  ");
					} else if(suit == 4) {
						face += ("|  CLUBS   ");
					}
				}
            }
            face += ("|");
            face += ("\n");
        }
        face += ("+----------+"  + "\n");
        return face;
    }

	private void startGame() {
		System.out.println("\n**********************************");
		System.out.println("*                                *");
		System.out.println("* Welcome to Black Jack 21!!!    *");
		System.out.println("*                                *");
		System.out.println("**********************************\n");
	}

	private void betMessage() {
		System.out.println("\n**********************************");
		System.out.println("*                                *");
		System.out.println("*           Bets time!!!         *");
		System.out.println("*                                *");
		System.out.println("**********************************\n");
	}

	private void roundOverMessage() {
		System.out.println("\n**********************************");
		System.out.println("*                                *");
		System.out.println("*         Round is over!!        *");
		System.out.println("*                                *");
		System.out.println("**********************************\n");
	}
}


