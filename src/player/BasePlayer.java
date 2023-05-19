package player;

import customException.InvalidValueException;

/**
 * Base class of player
 */
public abstract class BasePlayer {
	private int money;
	private int swordStats;
	private int magicStats;
	private String name;
	private boolean isAlive;
	
	//	TODO: change these starting number for game balance
	private int START_MONEY = 100;
	private int START_MAGICSTATS = 10;
	private int START_SWORDSTATS = 10;
	
	/**
	 * Create new player with fixed starting money and stats
	 * @param name player name
	 * @throws InvalidValueException
	 */
	public BasePlayer(String name) throws InvalidValueException{
		this.setMoney(START_MONEY);
		this.setMagicStats(START_MAGICSTATS);
		this.setSwordStats(START_SWORDSTATS);
		this.setName(name);
		this.setAlive(true);			
	}
	
	/**
	 * Increase player magic stats, tremendously increase if player is {@link GoodAtMagic GoodAtMagic}
	 * @param magicStats magic stats to increase
	 * @return string describing amount of increased magic stats
	 * @throws InvalidValueException
	 */
	public String learnMagic(int magicStats) throws InvalidValueException {
		if (this instanceof GoodAtMagic) {
			magicStats *= GoodAtMagic.magicMultiplier;
		}
		int before = this.getMagicStats();
		this.setMagicStats(before + magicStats);
		return String.format("%s has learnt magic for %d units (%d->%d)", this.getName(), magicStats, before, before + magicStats);
	}
	
	/**
	 * Increase player sword stats, tremendously increase if player is {@link GoodAtSword}
	 * @param swordStats sword stats to increase
	 * @return string describing amount of increased sword stats
	 * @throws InvalidValueException
	 */
	public String learnSword(int swordStats) throws InvalidValueException {
		if (this instanceof GoodAtSword) {
			swordStats *= GoodAtSword.swordMultiplier;
		}
		int before = this.getSwordStats();
		this.setSwordStats(before + swordStats);
		return String.format("%s has learnt sword for %d units (%d->%d)", this.getName(), swordStats, before, before + swordStats);
	}
	
	/**
	 * Increase player's money, tremendously increase if player is {@link Rich}
	 * @param money amount of money to increase
	 * @return string describing the amount of money to increase
	 * @throws InvalidValueException
	 */
	public String earnMoney(int money) throws InvalidValueException {
		if (this instanceof Rich) {
			money *= Rich.moneyMultiplier;
		}
		int before = this.getMoney();
		this.setMoney(before + money);
		return String.format("%s has earned money for %d bahts (%d->%d)", this.getName(), money, before, before + money);
	}
	
	/**
	 * Calculate win rate depending on each class
	 * @param winRateSword win rate by comparing sword stats
	 * @param winRateMagic win rate by comparing magic stats
	 * @return probability of winning
	 */
	public abstract double calculateWinRate(double winRateSword, double winRateMagic);
	
	/**
	 * Get name of this player
	 * @return name of this player 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of this player
	 * @param name new name of this player, "Untitled" if empty string is passed
	 */
	public void setName(String name) {
		if (name.isEmpty()) name = "Untitled";
		this.name = name;
	}
	
	/**
	 * Get money of this player
	 * @return money of this player
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Set money of this player, player dies when money is less than or equal to 0
	 * @param money the new amount of money
	 */
	public void setMoney(int money) {
//		Player dies if money is less than or equal to 0 
		if (money <= 0) {
			this.setAlive(false);
			money = 0;
		}
		this.money = money;
	}

	/**
	 * Get sword stats of this player
	 * @return sword stats of this player
	 */
	public int getSwordStats() {
		return swordStats;
	}

	/**
	 * Set sword stats of this player
	 * @param swordStats new sword stats of this player
	 * @throws InvalidValueException
	 */
	public void setSwordStats(int swordStats) throws InvalidValueException{
		if (swordStats <= 0) {
			throw new InvalidValueException("Invalid sword stats for player");
		}
		this.swordStats = swordStats;
	}

	/**
	 * Get magic stats of this player
	 * @return magic stats of this player
	 */
	public int getMagicStats() {
		return magicStats;
	}

	/**
	 * Set magic stats of this player
	 * @param magicStats new magic stats of this player
	 * @throws InvalidValueException
	 */
	public void setMagicStats(int magicStats) throws InvalidValueException{
		if (magicStats <= 0) {
			throw new InvalidValueException("Invalid magic stats for player");
		}
		this.magicStats = magicStats;
	}	
	
	/**
	 * True if player is alive, otherwise false
	 * @return boolean denoting player is alive or dead
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Set player alive status
	 * @param isAlive new player alive status
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
