package monster;

import customException.InvalidValueException;
import javafx.scene.paint.Color;

/**
 * Base class of monster
 */
public abstract class BaseMonster {
	private String name;
	private int swordStats;
	private int magicStats;	
	private int dropMoney;

	/**
	 * Create new monster
	 * @param name monster name
	 * @param swordStats monster sword stats
	 * @param magicStats monster magic stats
	 * @param dropMoney amount of money money drop when defeated
	 */
	public BaseMonster(String name, int swordStats, int magicStats, int dropMoney) {
		try {
			this.setName(name);
			this.setSwordStats(swordStats);
			this.setMagicStats(magicStats);
			this.setDropMoney(dropMoney);
		}
		catch (InvalidValueException err) {
			System.out.println(String.format("Error occured with monster %s: %s", name, err.getMessage()));
		}
	}
	
	/**
	 * Get the color particular to each action
	 * @return color particular to each action
	 */
	public abstract Color getColor();
	
	/**
	 * Get level of the monster
	 * @return level of the monster
	 */
	public abstract int getLevel();
	
	/**
	 * Respawn monster
	 */
	public abstract void respawn();

	/**
	 * Get drop money
	 * @return drop money
	 */
	public int getDropMoney() {
		return dropMoney;
	}

	/**
	 * Set amount of drop money
	 * @param dropMoney new amount of drop money
	 */
	public void setDropMoney(int dropMoney) {
		if (dropMoney < 0) dropMoney = 0;
		this.dropMoney = dropMoney;
	}

	/**
	 * Get the name of this monster
	 * @return name of this monster
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of this monster
	 * @param name new name of this monster
	 */
	public void setName(String name) {
		if (name.isEmpty()) {
			name = this.getClass().getSimpleName();
		}
		this.name = name;
	}

	/**
	 * Get monster's sword stats
	 * @return monster's sword stats
	 */
	public int getSwordStats() {
		return swordStats;
	}

	/**
	 * Set monster's sword stats
	 * @param swordStats new monster's sword stats
	 * @throws InvalidValueException throw error when value is invalid
	 */
	public void setSwordStats(int swordStats) throws InvalidValueException {
		if (swordStats <= 0) {
			throw new InvalidValueException("Invalid sword stats for monster");
		}
		this.swordStats = swordStats;
	}

	/**
	 * Get monster's magic stats
	 * @return monster's magic stats
	 */
	public int getMagicStats() {
		return magicStats;
	}

	/**
	 * Set monster's magic stats
	 * @param magicStats monster's magic stats
	 * @throws InvalidValueException throw error when value is invalid
	 */
	public void setMagicStats(int magicStats) throws InvalidValueException {
		if (magicStats <= 0) {
			throw new InvalidValueException("Invalid magic stats for monster");
		}
		this.magicStats = magicStats;
	}
}
