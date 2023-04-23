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

public class Utility {
	// TODO: change these numbers to make game balance
	private static final int MIN_SWORD_STATS = 5, MAX_SWORD_STATS = 7;
	private static final int MIN_MAGIC_STATS = 5, MAX_MAGIC_STATS = 7;
	private static final int MIN_MONEY_STATS = 5, MAX_MONEY_STATS = 7;
	
	public static void sleep(int value) {
		value *= 1000;
		while (value >= 0) {
			value--;
		}
	}
	
	/**
	 * Random number in range [min, max] (both inclusive)
	 * @param min
	 * @param max
	 * @return a number in range [min, max] 
	 */
	public static int randomInteger(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
	
	/**
	 * Multiply the value with the ratio of current turn with number of turn
	 * @param value
	 * @return value + value * currentTurn / turn
	 */
	public static int calculateExtraBuff(int value) {
		return value + value * GameLogic.getInstance().getCurrentTurn() / GameLogic.getInstance().getNumberOfTurn();
	}
	
	// TODO: change the formula to make game balance
	public static double calculateWinRateStats(double playerStats, double monsterStats) {
		double x = Math.pow(100.0, 2.0 / monsterStats);
		double value = Math.min(playerStats - monsterStats / 2.0, monsterStats / 2.0);
		return Math.pow(x, value);
	}
	
	public static double calculateWinRate(BasePlayer p1, BaseMonster m1) {
		double winRateSword = calculateWinRateStats(p1.getSwordStats(), m1.getSwordStats());
		double winRateMagic = calculateWinRateStats(p1.getMagicStats(), m1.getMagicStats());
		return p1.calculateWinRate(winRateSword, winRateMagic);
	}
	
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
				monster = GameLogic.getInstance().summonWizzard();
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
	public static BaseAction genRandomAction(BasePlayer p1) {
		int rand = randomInteger(1, 100);
		BaseAction randAction = new WinLottery(p1);
		int probDragon = calculateExtraBuff(40) - 40;
		int range = (100 - 2 * probDragon) / 4;
		try {
			if (rand < 1 || rand > 100) {
				throw new InvalidValueException(String.format("Invalid random Action number: %d", rand));
			}
			else if (rand <= range) {
				randAction = new FindMageMaster(p1);
			}
			else if (rand <= range * 3) {
				randAction = new IsRobbed(p1);
			}
			else if (rand <= range * 3) {
				randAction = new WinLottery(p1);
			}
			else if (rand <= range * 4) {
				randAction = new FightMonster(p1, genRandomMonster(), true);
			}
			else if (rand <= range * 4 + probDragon) {
				randAction = new FindSwordMaster(p1);
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
	 * If player win then drop money, otherwise reduce player's money
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
			
			int dropMoney = m1.getDropMoney();
			if (p1 instanceof Rich) {
				dropMoney *= Rich.moneyMultiplier;
			}
			if (p1.getSwordStats() - m1.getSwordStats() / 2 < 0 && p1.getMagicStats() - m1.getMagicStats() / 2 < 0) {
				if (m1 instanceof Evolutionary) {
					((Evolutionary)m1).evolve(evolveSwordStats, evolveMagicStats);
				}
				p1.earnMoney(-m1.getDropMoney());
				return p1.getName() + " has been completely defeated by " + m1.getName() + " (lost " + dropMoney + " bahts) ";
			}
			else if (!isWon) {
				if (m1 instanceof Evolutionary) {
					((Evolutionary)m1).evolve(evolveSwordStats / 2, evolveMagicStats / 2);
				}
				p1.earnMoney(-m1.getDropMoney() / 2);
				return p1.getName() + " has been defeated by " + m1.getName() + " (lost " + dropMoney / 2 + " bahts) ";
			}
			
			m1.respawn();
			p1.earnMoney(m1.getDropMoney());
			return p1.getName() + " has defeated " + m1.getName() + " (recieved " + dropMoney + " bahts) ";			
		}
		catch (InvalidValueException err) {
			System.out.println(err);
			System.out.println(err.getMessage());
		}
		return null;
	}
	
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
			boolean isWon = (randomInteger(1, 100) <= winRate / 2);
			
			System.out.println(String.format("(%d, %d) vs %s(%d, %d) with win rate %.2f", playerSwordStats, playerMagicStats, boss.getName(), boss.getSwordStats(), boss.getMagicStats(), winRate));
			
			String action = "";
			int dropMoney = boss.getDropMoney() / n;
			if (!isWon) {	
				boss.evolve(evolveSwordStats, evolveMagicStats);
				action = String.join(", ", playersName) + "\n"
						+ "have been defeated by " + boss.getName() + " (lost (not include TheRich Buff) " + dropMoney + " bahts/player) ";
				dropMoney *= -1;
			}
			else {
				boss.respawn();
				action = String.join(", ", playersName) + "\n"
						+ "have defeated " + boss.getName() + " (recieved (not include TheRich Buff) " + dropMoney + " bahts/player) ";
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
	
	public static int genMagicStats() {
		return calculateExtraBuff(randomInteger(MIN_MAGIC_STATS, MAX_MAGIC_STATS));
	}
	
	public static int genSwordStats() {
		return calculateExtraBuff(randomInteger(MIN_SWORD_STATS, MAX_SWORD_STATS));
	}
	
	public static int genMoney() {
		return calculateExtraBuff(randomInteger(MIN_MONEY_STATS, MAX_MONEY_STATS));
	}

	public static int getMinSwordStats() {
		return MIN_SWORD_STATS;
	}

	public static int getMaxSwordStats() {
		return MAX_SWORD_STATS;
	}

	public static int getMinMagicStats() {
		return MIN_MAGIC_STATS;
	}

	public static int getMaxMagicStats() {
		return MAX_MAGIC_STATS;
	}

	public static int getMinMoneyStats() {
		return MIN_MONEY_STATS;
	}

	public static int getMaxMoneyStats() {
		return MAX_MONEY_STATS;
	}
}
