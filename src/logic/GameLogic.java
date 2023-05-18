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
import monster.Wizzard;
import player.*;
import scene.PlayingGameRootPane;
import scene.components.ActionFrame;
import scene.components.MonsterFrame;
import utility.GameState;
import utility.Utility;

public class GameLogic {
	private static GameLogic instance = null;
	private static int numberOfTurn;
	private ArrayList<BasePlayer> playersList;
	private int numberOfPlayer;
	private int currentTurn;
	private int currentPlayer;
	private Goblin goblin;
	private Skeleton skeleton;
	private Wizzard wizzard;
	private Dragon dragon;
	
	static {
		numberOfTurn = 0;
	}
	
	public GameLogic(ArrayList<String> playerNames) {
		ArrayList<BasePlayer> playersList = new ArrayList<BasePlayer>();
		for (final String name : playerNames) {
			playersList.add(Utility.genRandomRole(name));
		}
		
		goblin = new Goblin("", 10, 10, 5);
		skeleton = new Skeleton("", 30, 15, 11);
		wizzard = new Wizzard("", 15, 31, 12);
		dragon = new Dragon("", 60, 61, 27);
		this.setPlayersList(playersList);
		this.setCurrentPlayer(0);
		this.setCurrentTurn(1);
	}
	
	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic(new ArrayList<String>());
		}
		return instance;
	}
	
	public static GameLogic getInstance(ArrayList<String> playerNames) {
		if (instance == null) {
			instance = new GameLogic(playerNames);
		}
		return instance;
	}
	
	public static void clearInstance() {
		instance = null;
	}

	public void startGame() {
		Main.changeState(GameState.PLAYING_SCENE);
	}
	
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
	
	public void terminateGame() {
		GameLogic.clearInstance();
		Main.changeState(GameState.WELCOME_SCENE);
	}
	
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
	
	public Goblin summonGoblin() {
		return goblin;
	}
	
	public Skeleton summonSkeleton() {
		return skeleton;
	}
	
	public Wizzard summonWizzard() {
		return wizzard;
	}
	
	public Dragon summonDragon() {
		return dragon;
	}
	
	public void handleFightMonster(BaseMonster monster) {
		String action = Utility.fightAgainst(getCurrentPlayer(), monster);
		System.out.println(action);
		PlayingGameRootPane.setExplanation(action);
		PlayingGameRootPane.setIsWaiting(false);
		PlayingGameRootPane.updatePlayer();
	}
	
	public void handleFightBoss(ArrayList<BasePlayer> players) {
		String action = Utility.fightBoss(players, summonDragon());
		System.out.println(action);
		PlayingGameRootPane.clearTempPlayers();
		PlayingGameRootPane.setIsFightBoss(false);
		PlayingGameRootPane.setIsWaiting(false);
		PlayingGameRootPane.setExplanation(action);
		PlayingGameRootPane.updatePlayer();
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
		if (this.getNumberAlivePlayers() <= 1) {
			endGame();
			return;
		}
		
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
	}

	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}

	public void setNumberOfPlayer(int numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
	}

	public static int getNumberOfTurn() {
		return numberOfTurn;
	}

	public static void setNumberOfTurn(int newNumberOfTurn) {
		numberOfTurn = newNumberOfTurn;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
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
