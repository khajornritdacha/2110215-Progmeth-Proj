package logic;

import java.util.ArrayList;

import action.BaseAction;
import customExecption.InvalidValueExecption;
import player.*;
import utility.Utility;

// TODO: implement this class
public class GameLogic {
	private ArrayList<BasePlayer> playersList;
	private int numberPlayer;
	private int numberTurn;
	private int currentTurn;
	private int currentPlayer;
	
	public GameLogic(ArrayList<String> playerNames, int numberTurn) {
		ArrayList<BasePlayer> playersList = new ArrayList<BasePlayer>();
		for (final String name : playerNames) {
//			TODO: refactor this functionality to Utility?
			try {
				int classNum = Utility.randomInteger(0, 3);
				BasePlayer newPlayer;
				if (classNum == 0) {
					newPlayer = new Farmer(name); 
				} else if (classNum == 1) {
					newPlayer = new Mage(name); 	
				} else if (classNum == 2) {
					newPlayer = new SwordMan(name);
				} else if (classNum == 3) {
					newPlayer = new TheRich(name);
				} else {
					throw new InvalidValueExecption(String.format("In Constructor of Gamelogic, Class number is invalid with: %d", classNum));
				}
				
				playersList.add(newPlayer);
			} catch (InvalidValueExecption err) {
				System.out.println(err.getMessage());
			}
		}
		this.currentTurn = 1;
		this.currentPlayer = 0;
		this.numberTurn = numberTurn;
		this.setPlayersList(playersList);
		this.startGame();
	}
	
	public void startGame() {
		System.out.println("Yeah! The game has started!");
//		while (true) {
//			handleEachTurn();
//			
//			goToNextPlayer();
//			
//			if (getCurrentTurn() > numberTurn) break;
//			if (getNumberAlivePlayers() <= 1) break;
//			
//		}
	}
	
	private void handleEachTurn() {
//		Some logic for each turn
//		1. random event
//		2. let player choose 2 events
//		2.1 check if player is dead(!isAlive), if yes then skip to next player
//		3. turn to next player
		
		BaseAction randomAction = Utility.genRandomAction(this.getCurrentPlayer());
		try {
			randomAction.executeAction();
		} catch (InvalidValueExecption err) {
			System.out.println(String.format("Error in game logic, randomAction: %s", err.getMessage()));
		}
		
		if (!getCurrentPlayer().isAlive()) {
			return;
		}
		
//		let player choose 2 events
//		TODO: add GUI handler
		for (int i = 0; i < 2; i++) {
			int num = getPlayerAction();
			handleChooseAction(num);
			if (!getCurrentPlayer().isAlive()) {
				return;
			}
		}
	}
	
	private int getPlayerAction() {
//		TODO: return user's choice from GUI
		return 0;
	}
	
	private void handleChooseAction(int num) {
//		TODO: change number
		try {
			if (num == 0) {
				getCurrentPlayer().learnMagic(1);
			} else if (num == 1) {
				getCurrentPlayer().learnSword(1);
			} else if (num == 2) {
//			Be waiter for money
				getCurrentPlayer().earnMoney(1);
			} else if (num == 3) {
//			handle fight monster
			} 			
		} catch (InvalidValueExecption err) {
			System.out.println(String.format("Error in handle choose action: %s", err.getMessage()));
		}
	}
	
	private int getNumberAlivePlayers() {
		int num = 0;
		for(final BasePlayer p : playersList) num += p.isAlive() ? 1 : 0;
		return num;
	}
	
	public void goToNextPlayer() {
		while (true) {
			setCurrentPlayer(currentPlayer + 1);
			if (getCurrentPlayer().isAlive()) break;
		}
	}
	
	public ArrayList<BasePlayer> getPlayersList() {
		return playersList;
	}
	
	public BasePlayer getCurrentPlayer() {
		return playersList.get(currentPlayer);
	}

	public void setPlayersList(ArrayList<BasePlayer> playersList) {
		this.numberPlayer = playersList.size();
		this.playersList = playersList;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public void setCurrentPlayer(int currentPlayer) {
		if (currentPlayer > playersList.size()) {
//			TODO: remove assertion
			assert (currentPlayer <= playersList.size());
			
			setCurrentTurn(getCurrentTurn() + 1); 
			currentPlayer = 0;
		}
		this.currentPlayer = currentPlayer;
	}
}