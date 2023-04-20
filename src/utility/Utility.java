package utility;

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
import player.BasePlayer;
import player.Farmer;
import player.Mage;
import player.SwordMan;
import player.TheRich;

public class Utility {		
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
	
	public static double calculateXForWinRate(double stat) {
		return Math.pow(20.0, 1.0 / stat);
	}
	
	public static double calculateWinRate(double x, int value) {
		return Math.pow(x, value);
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
				throw new InvalidValueException(String.format("Invalid random Monster number: %d", rand));
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
	
	public static BaseAction genRandomAction(BasePlayer p1) {
//		TODO: add action for monster and boss
		int rand = randomInteger(1, 100);
		BaseAction randAction = new WinLottery(p1);
		try {
			if (rand < 1 || rand > 100) {
				throw new InvalidValueException(String.format("Invalid random Action number: %d", rand));
			}
			else if (rand <= 19) {
				randAction = new FindMageMaster(p1);
			}
			else if (rand <= 38) {
				randAction = new FindSwordMaster(p1);
			}
			else if (rand <= 57) {
				randAction = new IsRobbed(p1);
			}
			else if (rand <= 76) {
				randAction = new WinLottery(p1);
			}
			else if (rand <= 95) {
				randAction = new FightMonster(p1, genRandomMonster());
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
//		TODO: implements fightAgainst function
		try {
			double xForMagic = calculateXForWinRate(m1.getMagicStats() / 2);
			double xForSword = calculateXForWinRate(m1.getSwordStats() / 2);
			int magicDiff = Math.min(p1.getMagicStats() - m1.getMagicStats() / 2, m1.getMagicStats() / 2);
			int swordDiff = Math.min(p1.getSwordStats() - m1.getMagicStats() / 2, m1.getSwordStats() / 2);
			double winRateMagic = calculateWinRate(xForMagic, magicDiff);
			double winRateSword = calculateWinRate(xForSword, swordDiff);
			// TODO: add more ability for SwordMan and Mage to increase their win rate
			boolean isWon = (randomInteger(1, 20) <= (winRateMagic + winRateSword) / 2);
			
			System.out.println(String.format("%s(%d, %d) vs %s(%d, %d) with win rate sword(%.2f) and magic(%.2f)", p1.getName(), p1.getSwordStats(), p1.getMagicStats(), m1.getName(), m1.getSwordStats(), m1.getMagicStats(), winRateSword, winRateMagic));
			System.out.println(String.format("x1=%.2f x2=%.2f", xForMagic, xForSword));
			
			if (magicDiff < 0 && swordDiff < 0) {
				m1.evolution(m1.getSwordStats() - p1.getSwordStats(), m1.getMagicStats() - p1.getMagicStats());
				p1.earnMoney(-m1.getDropMoney());
				return p1.getName() + " has extremely lost " + m1.getName() + " (lost " + m1.getDropMoney() + " bahts) ";
			}
			else if (!isWon ) {
				m1.evolution((m1.getSwordStats() - p1.getSwordStats()) / 2, (m1.getMagicStats() - p1.getMagicStats()) / 2);
				p1.earnMoney(-m1.getDropMoney() / 2);
				return p1.getName() + " has lost " + m1.getName() + " (lost " + m1.getDropMoney() / 2 + " bahts) ";
			}
			
			GameLogic.getInstance().killMonster(m1);
			p1.earnMoney(m1.getDropMoney());
			return p1.getName() + " has defeated " + m1.getName() + " (recieve " + m1.getDropMoney() + " bahts) ";			
		}
		catch (InvalidValueException err) {
			System.out.println(err);
			System.out.println(err.getMessage());
		}
		return null;
	}
	
//	TODO: change magic number to make game balance
	public static int genMagicStats(BasePlayer p) {
		return calculateExtraBuff(randomInteger(5, 7));
	}
	
//	TODO: change sword number to make game balance
	public static int genSwordStats(BasePlayer p) {
		return calculateExtraBuff(randomInteger(5, 7));
	}
	
//	TODO: change money number to make game balance
	public static int genMoney(BasePlayer p) {
		return calculateExtraBuff(randomInteger(5, 7));
	}
}
