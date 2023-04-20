package logic;

import java.util.ArrayList;

import action.BaseAction;
import application.Main;
import customExecption.InvalidValueExecption;
import monster.BaseMonster;
import player.*;
import scene.playingGame.PlayingGameRootPane;
import utility.Utility;

// TODO: implement this class
public class GameLogic {
	private static GameLogic instance = null;
	
	private ArrayList<BasePlayer> playersList;
	private int numberOfPlayer;
	private int numberOfTurn;
	private int currentTurn;
	private int currentPlayer;
	
	public GameLogic(ArrayList<String> playerNames, int numberTurn) {
		System.out.println("How the fuck you invoke this function?");
		ArrayList<BasePlayer> playersList = new ArrayList<BasePlayer>();
		for (final String name : playerNames) {
//			TODO: refactor this functionality to Utility?
			try {
				int classNum = Utility.randomInteger(0, 3);
				BasePlayer newPlayer;
				if (classNum == 0) {
					newPlayer = new Farmer(name); 
				}
				else if (classNum == 1) {
					newPlayer = new Mage(name); 	
				}
				else if (classNum == 2) {
					newPlayer = new SwordMan(name);
				}
				else if (classNum == 3) {
					newPlayer = new TheRich(name);
				}
				else {
					throw new InvalidValueExecption(String.format("In Constructor of Gamelogic, Class number is invalid with: %d", classNum));
				}
				
				playersList.add(newPlayer);
			}
			catch (InvalidValueExecption err) {
				System.out.println(err.getMessage());
			}
		}
		
		this.setPlayersList(playersList);
		this.setNumberOfTurn(numberTurn);
		this.setCurrentPlayer(0);
		this.setCurrentTurn(1);
//		this.startGame();
	}
	
	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic(new ArrayList<String>(), 0);
		}
		return instance;
	}
	
	public static GameLogic getInstance(ArrayList<String> playerNames, int numberTurn) {
		if (instance == null) {
			instance = new GameLogic(playerNames, numberTurn);
		}
		return instance;
	}
	
	public static void clearInstance() {
		instance = null;
	}

	public void startGame() {
		Main.changeState(false);
//		while (true) {
//			System.out.println(this.getCurrentPlayer());
//			this.handleEachTurn();
//			
////			In case that more than 1 people die at the same time and game ends
//			if (getNumberAlivePlayers() <= 1) {
//				break;
//			}
//			
//			this.goToNextPlayer();
//			
//			if (this.getCurrentTurn() > this.getNumberOfTurn()) {
//				break;
//			}
//		}
//		endGame();
	}
	
	// Maybe we don't need to use this function.
//	private void handleEachTurn() {
////		Some logic for each turn
////		1. random event
////		2. let player choose 2 events
////		2.1 check if player is dead(!isAlive), if yes then skip to next player
////		3. turn to next player
//		
//		BaseAction randomAction = Utility.genRandomAction(getCurrentPlayer());
//		try {
//			randomAction.executeAction();
//		}
//		catch (InvalidValueExecption err) {
//			System.out.println(String.format("Error in game logic, randomAction: %s", err.getMessage()));
//		}
//		
//		if (!getCurrentPlayer().isAlive()) {
//			return;
//		}
//		
////		let player choose 2 events
////		TODO: add GUI handler
//		for (int i = 0; i < 2; i++) {
//			handleChooseAction();
//			if (!getCurrentPlayer().isAlive()) {
//				return;
//			}
//		}
//	}
	
	public void handleRandomAction() {
		BaseAction randomAction = Utility.genRandomAction(getCurrentPlayer());
		try {
			randomAction.executeAction();
		}
		catch (InvalidValueExecption err) {
			System.out.println(String.format("Error in game logic, randomAction: %s", err.getMessage()));
		}
		
		PlayingGameRootPane.updatePlayer();
		PlayingGameRootPane.setExplanation(randomAction.toString());
	}

	public void handleChooseAction(int num) {
//		TODO: Get num from GUI
		String action = "";
		try {
			if (num == 0) {
				action = getCurrentPlayer().learnMagic(Utility.genMagicStats(getCurrentPlayer(), getCurrentTurn()));
			}
			else if (num == 1) {
				action = getCurrentPlayer().learnSword(Utility.genSwordStats(getCurrentPlayer(), getCurrentTurn()));
			}
			else if (num == 2) {
//			Be waiter for money
				action = getCurrentPlayer().earnMoney(Utility.genMoney(getCurrentPlayer(), getCurrentTurn()));
			}
			else if (num >= 3) {
//			handle fight monster
				action = handleFightMonster(num - 3);
			} 	
		}
		catch (InvalidValueExecption err) {
			System.out.println(String.format("Error in handle choose action: %s", err.getMessage()));
		}
		
		System.out.println(action);
		PlayingGameRootPane.updatePlayer();
		PlayingGameRootPane.setExplanation(action);
	}
	
	private String handleFightMonster(int num) {
//	TODO: implements this function	
//	TODO: change monsters' stats
		if (num == 0) {
			Utility.fightAgainst(getCurrentPlayer(), new BaseMonster("Jomzilla", 10, 10, 5));
		}
		else if (num == 1) {
			
		}
		else if (num == 2) {
			
		}
		else if (num == 3) {
//			TODO: handle boss case
		}
		return "";
	} 
	
	private void endGame() {
//		TODO: implements this function and change game endings?
		if (getNumberAlivePlayers() == 0) {
			PlayingGameRootPane.setExplanation("No one wins");
			System.out.println("No one wins");
		}
		else {
			PlayingGameRootPane.setExplanation("JomnoiZ always win!!!");
			System.out.println("JomnoiZ always win!!!");
		}
		PlayingGameRootPane.setActionBtn("Play Again");
	}
	
	public void terminateGame() {
		GameLogic.clearInstance();
		Main.changeState(true);
	}
	
	private int getNumberAlivePlayers() {
		int num = 0;
		for(final BasePlayer p : playersList) {
			if (p.isAlive()) {
				num++;
			}
		}
		return num;
	}
	
	public void goToNextPlayer() {
		while (true) {
			setCurrentPlayer(currentPlayer + 1);
			if (getCurrentPlayer().isAlive()) {
				PlayingGameRootPane.updatePlayer();
				break;
			}
		}
	}

	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}

	public void setNumberOfPlayer(int numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
	}

	public int getNumberOfTurn() {
		return numberOfTurn;
	}

	public void setNumberOfTurn(int numberOfTurn) {
		this.numberOfTurn = numberOfTurn;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		if (currentTurn > this.getNumberOfTurn()) {
			endGame();
		}
		this.currentTurn = currentTurn;
	}

	public void setPlayersList(ArrayList<BasePlayer> playersList) {
		this.playersList = playersList;
		this.numberOfPlayer = playersList.size();
	}
	
	public ArrayList<BasePlayer> getPlayersList() {
		return this.playersList;
	}

	public void setCurrentPlayer(int currentPlayer) {
		if (currentPlayer >= this.getPlayersList().size()) {
//			TODO: remove assertion
			assert (currentPlayer <= this.getPlayersList().size());
			
			this.setCurrentTurn(this.getCurrentTurn() + 1);
			PlayingGameRootPane.updateCurrentTurn();
			currentPlayer = 0;
		}
		this.currentPlayer = currentPlayer;
	}
	
	public BasePlayer getCurrentPlayer() {
		return playersList.get(currentPlayer);
	}
}
