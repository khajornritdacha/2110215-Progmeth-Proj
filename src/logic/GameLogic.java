package logic;

import java.util.ArrayList;

import action.BaseAction;
import action.FightBoss;
import action.FightMonster;
import application.Main;
import customException.InvalidValueException;
import monster.BaseMonster;
import monster.Dragon;
import monster.Goblin;
import monster.Skeleton;
import monster.Wizard;
import player.BasePlayer;
import scene.PlayingGameRootPane;
import scene.components.ActionFrame;
import scene.components.MonsterFrame;
import utility.GameState;
import utility.Utility;

/**
 * System logic of this game
 */
public class GameLogic {
	private static GameLogic instance = null;
	private static int numberOfTurn;
	private ArrayList<BasePlayer> playersList;
	private int numberOfPlayer;
	private int currentTurn;
	private int currentPlayer;
	private Goblin goblin;
	private Skeleton skeleton;
	private Wizard wizard;
	private Dragon dragon;
	
	static {
		numberOfTurn = 0;
	}
	
	/**
	 * Create new GameLogic instance
	 * @param playerNames list of player names
	 */
	public GameLogic(ArrayList<String> playerNames) {
		ArrayList<BasePlayer> playersList = new ArrayList<BasePlayer>();
		for (final String name : playerNames) {
			playersList.add(Utility.genRandomRole(name));
		}
		
		goblin = new Goblin("", 10, 10, 5);
		skeleton = new Skeleton("", 30, 15, 11);
		wizard = new Wizard("", 15, 31, 12);
		dragon = new Dragon("", 60, 61, 27);
		this.setPlayersList(playersList);
		this.setCurrentPlayer(0);
		this.setCurrentTurn(1);
	}
	
	/**
	 * Return instance of game logic
	 * @return current GameLogic instance
	 */
	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic(new ArrayList<String>());
		}
		return instance;
	}
	
	/**
	 * Return instance of game logic
	 * @param playerNames list of player names
	 * @return current GameLogic instance
	 */
	public static GameLogic getInstance(ArrayList<String> playerNames) {
		if (instance == null) {
			instance = new GameLogic(playerNames);
		}
		return instance;
	}
	
	/**
	 * Remove current GameLogic instance 
	 */
	public static void clearInstance() {
		instance = null;
	}

	/**
	 * Start this game by setting scene to PLAYING_SCENE
	 * @see GameState
	 */
	public void startGame() {
		Main.changeState(GameState.PLAYING_SCENE);
	}
	
	/**
	 * Logic for handling game ending including finding winners and set UI
	 */
	private void endGame() {
		if (getNumberAlivePlayers() == 0) {
			PlayingGameRootPane.setExplanation("No one wins");
		}
		else {
			// TODO: (optional) change the condition who will win to make game balance
			double maxPotential = -1;
			int maxMoney = -1, maxMagic = -1;
			String winner = "";
			for (BasePlayer player : playersList) {
				if (!player.isAlive()) {
					continue;
				}
				
				double potential = player.getMoney() * 50.0 + player.getSwordStats() * 24.0 + player.getMagicStats() * 26.0;
				if (maxPotential < potential) {
					maxPotential = potential;
					maxMoney = player.getMoney();
					winner = player.getName();
				}
				else if (maxPotential == potential && maxMoney < player.getMoney()) {
					maxMoney = player.getMoney();
					winner = player.getName();
				}
				else if (maxPotential == potential && maxMoney == player.getMoney() && maxMagic < player.getMagicStats()) {
					maxMagic = player.getMagicStats();
					winner = player.getName();
				}
				
				System.out.println(String.format("%s : %.2f", player.getName(), potential));
			}
			PlayingGameRootPane.setExplanation(String.format("%s wins!!!", winner));
		}
		PlayingGameRootPane.setActionBtn("Play again");
	}
	
	/**
	 * Remove current GameLogic instance and change scene to WELCOME_SCENE
	 * @see GameState
	 */
	public void terminateGame() {
		GameLogic.clearInstance();
		Main.changeState(GameState.WELCOME_SCENE);
	}
	
	/**
	 * Handle random action when at the beginning of each player's turn
	 */
	public void handleRandomAction() {
		PlayingGameRootPane.getActionBtn().setDisable(true);
		BaseAction randomAction = Utility.genRandomAction(getCurrentPlayer());
		if (randomAction instanceof FightMonster) {
			PlayingGameRootPane.showExecutingAction(new MonsterFrame(((FightMonster)randomAction).getM1()));
		}
		else if (randomAction instanceof FightBoss) {
			PlayingGameRootPane.showExecutingAction(new MonsterFrame(((FightBoss)randomAction).getB1()));
		}
		else {
			PlayingGameRootPane.showExecutingAction(new ActionFrame(randomAction, false));
		}
	}

	/**
	 * Handle action players choose in each turn
	 * @param chooseAction action to be executed
	 */
	public void handleAction(BaseAction chooseAction) {
		String action = "";
		try {
			action = chooseAction.executeAction();	
		}
		catch (InvalidValueException err) {
			System.out.println(String.format("Error in handle choose action: %s", err.getMessage()));
		}
		
		PlayingGameRootPane.setExplanation(action);
		System.out.println(action);
		PlayingGameRootPane.setIsWaiting(false);
		PlayingGameRootPane.updatePlayer();
	}
	
	/**
	 * Summon goblin
	 * @return goblin
	 */
	public Goblin summonGoblin() {
		return goblin;
	}
	
	/**
	 * Summon skeleton
	 * @return skeleton
	 */
	public Skeleton summonSkeleton() {
		return skeleton;
	}
	
	/**
	 * Summon wizard
	 * @return wizard
	 */
	public Wizard summonWizard() {
		return wizard; 
		
	}
	
	/**
	 * Summon dragon
	 * @return dragon
	 */
	public Dragon summonDragon() {
		return dragon;
	}
	
	/**
	 * Handle fighting monster logic
	 * @param monster monster to fight
	 */
	public void handleFightMonster(BaseMonster monster) {
		String action = Utility.fightAgainst(getCurrentPlayer(), monster);
		System.out.println(action);
		PlayingGameRootPane.setExplanation(action);
		PlayingGameRootPane.setIsWaiting(false);
		PlayingGameRootPane.updatePlayer();
	}
	
	/**
	 * Handle fight boss logic
	 * @param players players to fight with 
	 */
	public void handleFightBoss(ArrayList<BasePlayer> players) {
		String action = Utility.fightBoss(players, summonDragon());
		System.out.println(action);
		PlayingGameRootPane.clearTempPlayers();
		PlayingGameRootPane.addTempPlayer(getCurrentPlayer());
		PlayingGameRootPane.setIsFightBoss(false);
		PlayingGameRootPane.setIsWaiting(false);
		PlayingGameRootPane.setExplanation(action);
		PlayingGameRootPane.updatePlayer();
	}
	
	/**
	 * Get the number of alive players
	 * @return The number of alive players
	 */
	private int getNumberAlivePlayers() {
		int num = 0;
		for(final BasePlayer p : playersList) {
			if (p.isAlive()) {
				num++;
			}
		}
		return num;
	}
	
	/**
	 * Change current player to next player
	 */
	public void goToNextPlayer() {
		if (this.getNumberAlivePlayers() <= 1) {
			endGame();
			return;
		}
		
		PlayingGameRootPane.removeTempPlayer(getCurrentPlayer());
		while (true) {
			this.setCurrentPlayer(currentPlayer + 1);
			if (this.getCurrentPlayer().isAlive()) {
				PlayingGameRootPane.setExplanation("It's now " + GameLogic.getInstance().getCurrentPlayer().getName() + "'s turn");
				PlayingGameRootPane.updatePlayer();
				break;
			}
		}

		if (this.getCurrentTurn() > getNumberOfTurn()) {
			endGame();
		}
		PlayingGameRootPane.addTempPlayer(getCurrentPlayer());
	}

	/**
	 * Get the total number of player
	 * @return total number of player
	 */
	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}

	/**
	 * Set the number of player
	 * @param numberOfPlayer new number of player
	 */
	public void setNumberOfPlayer(int numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
	}

	/**
	 * Get the number of turn
	 * @return the number of turn
	 */
	public static int getNumberOfTurn() {
		return numberOfTurn;
	}

	/**
	 * Set the number of turn
	 * @param newNumberOfTurn new number of turn
	 */
	public static void setNumberOfTurn(int newNumberOfTurn) {
		numberOfTurn = newNumberOfTurn;
	}

	/**
	 * Get current turn number
	 * @return current turn number
	 */
	public int getCurrentTurn() {
		return currentTurn;
	}

	/**
	 * Set current turn
	 * @param currentTurn new number of current turn
	 */
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	/**
	 * Set the list of players
	 * @param playersList new list of players
	 */
	public void setPlayersList(ArrayList<BasePlayer> playersList) {
		this.playersList = playersList;
		this.numberOfPlayer = playersList.size();
	}
	
	/**
	 * Get the list of players
	 * @return list of players 
	 */
	public ArrayList<BasePlayer> getPlayersList() {
		return this.playersList;
	}

	/**
	 * Set current player
	 * @param currentPlayer new current player
	 */
	public void setCurrentPlayer(int currentPlayer) {
		if (currentPlayer >= this.getPlayersList().size()) {
			this.setCurrentTurn(this.getCurrentTurn() + 1);
			PlayingGameRootPane.updateCurrentTurn();
			goblin.evolveByTurn();
			skeleton.evolveByTurn();
			wizzard.evolveByTurn();
			dragon.evolveByTurn();
			currentPlayer = 0;
		}
		this.currentPlayer = currentPlayer;
	}
	
	/**
	 * Get current player
	 * @return current player
	 */
	public BasePlayer getCurrentPlayer() {
		return playersList.get(currentPlayer);
	}
}
