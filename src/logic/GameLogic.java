package logic;

import java.util.ArrayList;

import action.BaseAction;
import application.Main;
import customException.InvalidValueException;
import monster.BaseMonster;
import monster.Dragon;
import monster.Goblin;
import monster.Skeleton;
import monster.Wizzard;
import player.*;
import scene.playingGame.PlayingGameRootPane;
import utility.Utility;

// TODO: implement game logic class
public class GameLogic {
	private static GameLogic instance = null;
	
	private ArrayList<BasePlayer> playersList;
	private int numberOfPlayer;
	private int numberOfTurn;
	private int currentTurn;
	private int currentPlayer;
	private Goblin goblin;
	private Skeleton skeleton;
	private Wizzard wizzard;
	private Dragon dragon;
	
	public GameLogic(ArrayList<String> playerNames, int numberTurn) {
		ArrayList<BasePlayer> playersList = new ArrayList<BasePlayer>();
		for (final String name : playerNames) {
			playersList.add(Utility.genRandomRole(name));
		}
		
		this.setPlayersList(playersList);
		this.setNumberOfTurn(numberTurn);
		this.setCurrentPlayer(0);
		this.setCurrentTurn(1);
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
	}
	
	private void endGame() {
		if (getNumberAlivePlayers() == 0) {
			PlayingGameRootPane.setExplanation("No one wins");
		}
		else {
			// TODO: (optional) change the condition who will win to make game balance
			int maxPossibleMoney = 0, maxPossibleMagicStats = 0, maxPossibleSwordStats = 0;
			for (BasePlayer player : playersList) {
				if (!player.isAlive()) {
					continue;
				}
				
				if (maxPossibleMoney < player.getMoney()) {
					maxPossibleMoney = player.getMoney();
				}
				if (maxPossibleMagicStats < player.getMagicStats()) {
					maxPossibleMagicStats = player.getMagicStats();
				}
				if (maxPossibleSwordStats < player.getSwordStats()) {
					maxPossibleSwordStats = player.getSwordStats(); 
				}
			}
			
			double maxPotential = -1;
			int maxMoney = -1, maxMagic = -1;
			String winner = "";
			for (BasePlayer player : playersList) {
				if (!player.isAlive()) {
					continue;
				}
				
				double potential = player.getMoney() * 50.0 / maxPossibleMoney + 
						           player.getSwordStats() * 25.0 / maxPossibleSwordStats + 
						           player.getMagicStats() * 25.0 / maxPossibleMagicStats;
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
				
				System.out.println(String.format("%s : %.2f/100.00", player.getName(), potential));
			}
			PlayingGameRootPane.setExplanation(String.format("%s wins!!! (score = %.2f/100.00)", winner, maxPotential));
		}
		PlayingGameRootPane.setActionBtn("Play Again");
	}
	
	public void terminateGame() {
		GameLogic.clearInstance();
		Main.changeState(true);
	}
	
	public void handleRandomAction() {
		BaseAction randomAction = Utility.genRandomAction(getCurrentPlayer());
		String action = "";
		try {
			action = randomAction.executeAction();
		}
		catch (InvalidValueException err) {
			System.out.println(String.format("Error in game logic, randomAction: %s", err.getMessage()));
		}
		
		PlayingGameRootPane.setExplanation(action);
		PlayingGameRootPane.updatePlayer();
	}

	public void handleChooseAction(int num) {
		String action = "";
		try {
			if (num == 0) {
				action = getCurrentPlayer().learnMagic(Utility.genMagicStats(getCurrentPlayer()));
			}
			else if (num == 1) {
				action = getCurrentPlayer().learnSword(Utility.genSwordStats(getCurrentPlayer()));
			}
			else if (num == 2) {
				action = getCurrentPlayer().earnMoney(Utility.genMoney(getCurrentPlayer()));
			}	
		}
		catch (InvalidValueException err) {
			System.out.println(String.format("Error in handle choose action: %s", err.getMessage()));
		}
		
		PlayingGameRootPane.setExplanation(action);
		PlayingGameRootPane.updatePlayer();
	}
	
	// TODO: change the formula of this function to make game balance
	private int extraBuff(int value) {
		return Utility.calculateExtraBuff(10 * value) - 10 * value;
	}
	
	// TODO: change monsters'stats
	public Goblin summonGoblin() {
		if (goblin == null) {
			goblin = new Goblin("Goblin", extraBuff(10), extraBuff(10), extraBuff(5));
		}
		return goblin;
	}
	
	public Skeleton summonSkeleton() {
		if (skeleton == null) {
			skeleton = new Skeleton("Skeleton", extraBuff(30), extraBuff(15), extraBuff(10));
		}
		return skeleton;
	}
	
	public Wizzard summonWizzard() {
		if (wizzard == null) {
			wizzard = new Wizzard("Wizzard", extraBuff(15), extraBuff(32), extraBuff(11));
		}
		return wizzard;
	}
	
	public Dragon summonDragon() {
		if (dragon == null) {
			dragon = new Dragon("Dragon", extraBuff(60), extraBuff(61), extraBuff(25));
		}
		return dragon;
	}
	
	public void killMonster(BaseMonster monster) {
		if (monster instanceof Goblin) {
			goblin = null;
		}
		else if (monster instanceof Skeleton) {
			skeleton = null;
		}
		else if (monster instanceof Wizzard) {
			wizzard = null;
		}
		else {
			dragon = null;
		}
	}
	
	public void handleFightMonster(BaseMonster monster) {
		String action = Utility.fightAgainst(getCurrentPlayer(), monster);
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

		if (this.getCurrentTurn() > this.getNumberOfTurn()) {
			endGame();
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
