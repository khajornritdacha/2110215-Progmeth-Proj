package utility;

import java.util.ArrayList;
import java.util.Random;

import action.BaseAction;
import action.FightBoss;
import action.FightMonster;
import action.FindMageMaster;
import action.FindSwordMaster;
import action.IsRobbed;
import action.WinLottery;
import customException.InvalidValueException;
import logic.GameLogic;
import monster.BaseMonster;
import monster.Dragon;
import monster.Evolutionary;
import player.BasePlayer;
import player.Farmer;
import player.Mage;
import player.Rich;
import player.SwordMan;
import player.TheRich;

/**
 * Contain utility method for this game
 */
public class Utility {
	// TODO: change these numbers to make game balance
	private static final int MIN_SWORD_STATS = 8, MAX_SWORD_STATS = 14;
	private static final int MIN_MAGIC_STATS = 8, MAX_MAGIC_STATS = 14;
	private static final int MIN_MONEY_STATS = 12, MAX_MONEY_STATS = 20;
	
	/**
	 * Random number in range [min, max] (both inclusive)
	 * @param min minimum number
	 * @param max maximum number
	 * @return a number in range [min, max] 
	 */
	public static int randomInteger(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
	
	/**
	 * Multiply the value with the ratio of current turn with number of turn
	 * @param value base value
	 * @return value + value * currentTurn / turn
	 */
	public static int calculateExtraBuff(int value) {
		return value + value * GameLogic.getInstance().getCurrentTurn() / GameLogic.getInstance().getNumberOfTurn();
	}
	
	// TODO: change the formula to make game balance
	/**
	 * Calculate win rate base on difference of player stats and monster stats
	 * @param playerStats player stats
	 * @param monsterStats monster stats
	 * @return probability of winning
	 */
	public static double calculateWinRateStats(double playerStats, double monsterStats) {
		double x = Math.pow(100.0, 2.0 / monsterStats);
		double value = Math.min(playerStats - monsterStats / 2.0, monsterStats / 2.0);
		return Math.pow(x, value);
	}
	
	// TODO: add comments
	public static double calculateProbDragon() {
		return Math.max(8, calculateExtraBuff(20) - 20);
	}
	
	/**
	 * Calculate net win rate 
	 * @param p1 player
	 * @param m1 monster
	 * @return winrate
	 */
	public static double calculateWinRate(BasePlayer p1, BaseMonster m1) {
		double winRateSword = calculateWinRateStats(p1.getSwordStats(), m1.getSwordStats());
		double winRateMagic = calculateWinRateStats(p1.getMagicStats(), m1.getMagicStats());
		return p1.calculateWinRate(winRateSword, winRateMagic);
	}
	
	/**
	 * Calculate win rate when fighting boss
	 * @param players list of players
	 * @param boss a boss
	 * @return win rate of fighting boss
	 */
	public static double calculateWinRateBoss(ArrayList<BasePlayer> players, Dragon boss) {
		int playerSwordStats = 0, playerMagicStats = 0;
		for (BasePlayer player : players) {
			playerSwordStats += player.getSwordStats();
			playerMagicStats += player.getMagicStats();
		}
		double winRateSword = calculateWinRateStats(playerSwordStats, boss.getSwordStats());
		double winRateMagic = calculateWinRateStats(playerMagicStats, boss.getMagicStats());
		return (winRateSword + winRateMagic) / 2;
	}
	
	/**
	 * Random player role
	 * @param name player name
	 * @return new player and their role
	 */
	public static BasePlayer genRandomRole(String name) {
		try {
			BasePlayer player = new Farmer(name);
			int rand = randomInteger(0, 3);
			if (rand == 0) {
				player = new Farmer(name); 
			}
			else if (rand == 1) {
				player = new Mage(name); 	
			}
			else if (rand == 2) {
				player = new SwordMan(name);
			}
			else if (rand == 3) {
				player = new TheRich(name);
			}
			else {
				throw new InvalidValueException(String.format("Invalid random Role number: %d", rand));
			}
			return player;
		}
		catch (InvalidValueException err) {
			System.out.println(err);
			System.out.println(err.getMessage());
		}
		return null;
	}
	
	/**
	 * Random monster
	 * @return new monster and their role
	 */
	public static BaseMonster genRandomMonster() {
		BaseMonster monster = GameLogic.getInstance().summonGoblin();
		int rand = randomInteger(0, 2);
		try {
			if (rand == 0) {
				monster = GameLogic.getInstance().summonGoblin();
			}
			else if (rand == 1) {
				monster = GameLogic.getInstance().summonSkeleton();
			}
			else if (rand == 2) {
				monster = GameLogic.getInstance().summonWizard();
			}
			else {
				throw new InvalidValueException(String.format("Invalid random Monster number: %d", rand));
			}
		}
		catch (InvalidValueException err) {
			System.out.println(err);
			System.out.println(err.getMessage());
		}
		return monster;
	}
	
	// TODO: (optional) change the percent late to random the actions to make game balance
	/**
	 * Random action
	 * @param p1 player
	 * @return new random action
	 */
	public static BaseAction genRandomAction(BasePlayer p1) {
		int rand = randomInteger(1, 100);
		BaseAction randAction = new WinLottery(p1);
		double probDragon = calculateProbDragon();
		double range = (100 - 2 * probDragon) / 4;
		try {
			if (rand < 1 || rand > 100) {
				throw new InvalidValueException(String.format("Invalid random Action number: %d", rand));
			}
			else if (rand <= range) {
				randAction = new FindMageMaster(p1);
			}
			else if (rand <= range * 2) {
				randAction = new WinLottery(p1);
			}
			else if (rand <= range * 3) {
				randAction = new FindSwordMaster(p1);
			}
			else if (rand <= range * 4) {
				randAction = new FightMonster(p1, genRandomMonster(), true);
			}
			else if (rand <= range * 4 + probDragon) {
				randAction = new IsRobbed(p1);
			}
			else {
				randAction = new FightBoss(p1, GameLogic.getInstance().summonDragon());
			}
		}
		catch (InvalidValueException err) {
			System.out.println(err);
			System.out.println(err.getMessage());
		}
		return randAction;
	}
	
	/**
	 * If player wins then drop money, otherwise reduce player's money
	 * @param p1 player
	 * @param m1 monster
	 * @return string that refer to the result of that fight
	 */
	public static String fightAgainst(BasePlayer p1, BaseMonster m1) {
		try {
			int evolveSwordStats = Math.max(0, m1.getSwordStats() - p1.getSwordStats());
			int evolveMagicStats = Math.max(0, m1.getMagicStats() - p1.getMagicStats());
			double winRate = calculateWinRate(p1, m1);
			boolean isWon = (randomInteger(1, 100) <= winRate);
			
			System.out.println(String.format("%s(%d, %d) vs %s(%d, %d) with win rate %.2f%%", p1.getName(), p1.getSwordStats(), p1.getMagicStats(), m1.getName(), m1.getSwordStats(), m1.getMagicStats(), winRate));
			
			String action = p1.getName();
			int dropMoney = m1.getDropMoney();
			
			boolean isHalf = false;
			if (p1.getSwordStats() - m1.getSwordStats() / 2 < 0 && p1.getMagicStats() - m1.getMagicStats() / 2 < 0) {
				if (m1 instanceof Evolutionary) {
					((Evolutionary)m1).evolve(evolveSwordStats, evolveMagicStats);
				}
				dropMoney *= -1;
				p1.earnMoney(dropMoney);
				action += " has been completely defeated by " + m1.getName() + " (lost ";
			}
			else if (!isWon) {
				if (m1 instanceof Evolutionary) {
					((Evolutionary)m1).evolve(evolveSwordStats / 2, evolveMagicStats / 2);
				}
				isHalf = true;
				p1.lostHalfMoney(dropMoney);
				action += " has been defeated by " + m1.getName() + " (lost ";
			}
			else {
				m1.respawn();
				p1.earnMoney(dropMoney);			
				action += " has defeated " + m1.getName() + " (recieved ";
			}
			
			if (p1 instanceof Rich) {
				dropMoney *= Rich.moneyMultiplier;
			}
			dropMoney = calculateExtraBuff(dropMoney);
			if (isHalf) {
				dropMoney /= 2;
			}
			return action + Math.abs(dropMoney) + " bahts)";
		}
		catch (InvalidValueException err) {
			System.out.println(err);
			System.out.println(err.getMessage());
		}
		return null;
	}
	
	/**
	 * If player win then drop money, otherwise reduce players' money
	 * @param players list of players
	 * @param boss boss
	 * @return string that refer to the result of that fight
	 */
	public static String fightBoss(ArrayList<BasePlayer> players, Dragon boss) {
		try {
			int n = players.size();
			int playerSwordStats = 0, playerMagicStats = 0;
			ArrayList<String> playersName = new ArrayList<String>();
			for (BasePlayer player : players) {
				playerSwordStats += player.getSwordStats();
				playerMagicStats += player.getMagicStats();
				playersName.add(player.getName());
			}
			
			int evolveSwordStats = Math.max(0, boss.getSwordStats() - playerSwordStats);
			int evolveMagicStats = Math.max(0, boss.getMagicStats() - playerMagicStats);
			double winRate = calculateWinRateBoss(players, boss); 
			boolean isWon = (randomInteger(1, 100) <= winRate);
			
			System.out.println(String.format("(%d, %d) vs %s(%d, %d) with win rate %.2f", playerSwordStats, playerMagicStats, boss.getName(), boss.getSwordStats(), boss.getMagicStats(), winRate));
			
			String action = "";
			int dropMoney = boss.getDropMoney() / n;
			int dropMoneyForShow = calculateExtraBuff(dropMoney);
			if (!isWon) {	
				boss.evolve(evolveSwordStats, evolveMagicStats);
				action = String.join(", ", playersName) + "\n"
						+ "have been defeated by " + boss.getName() + " (lost (not include TheRich Buff) " + dropMoneyForShow + " bahts/player) ";
				dropMoney *= -1;
			}
			else {
				boss.respawn();
				action = String.join(", ", playersName) + "\n"
						+ "have defeated " + boss.getName() + " (recieved (not include TheRich Buff) " + dropMoneyForShow + " bahts/player) ";
			}
			
			for (BasePlayer player : players) {
				player.earnMoney(dropMoney);
			}
			return action;
		}
		catch (InvalidValueException err) {
			System.out.println(err);
			System.out.println(err.getMessage());
		}
		return null;
	}
	
	/**
	 * Random magic stats
	 * @return random magic stats
	 */
	public static int genMagicStats() {
		return randomInteger(MIN_MAGIC_STATS, MAX_MAGIC_STATS);
	}
	
	/**
	 * Random sword stats
	 * @return random sword stats
	 */
	public static int genSwordStats() {
		return randomInteger(MIN_SWORD_STATS, MAX_SWORD_STATS);
	}
	
	/**
	 * Random money
	 * @return random money
	 */
	public static int genMoney() {
		return randomInteger(MIN_MONEY_STATS, MAX_MONEY_STATS);
	}

	/**
	 * Get minimum possible sword stats
	 * @return Minimum possible sword stats
	 */
	public static int getMinSwordStats() {
		return MIN_SWORD_STATS;
	}
	/**
	 * Get maximum possible sword stats
	 * @return Maximum possible sword stats
	 */
	public static int getMaxSwordStats() {
		return MAX_SWORD_STATS;
	}

	/**
	 * Get minimum possible magic stats
	 * @return Minimum possible magic stats
	 */
	public static int getMinMagicStats() {
		return MIN_MAGIC_STATS;
	}

	/**
	 * Get maximum possible magic stats
	 * @return Maximum possible magic stats
	 */
	public static int getMaxMagicStats() {
		return MAX_MAGIC_STATS;
	}

	/**
	 * Get minimum possible money
	 * @return Minimum possible money
	 */
	public static int getMinMoneyStats() {
		return MIN_MONEY_STATS;
	}

	/**
	 * Get maximum possible money
	 * @return Maximum possible money
	 */
	public static int getMaxMoneyStats() {
		return MAX_MONEY_STATS;
	}
}
