package monster;

import customException.InvalidValueException;
import javafx.scene.paint.Color;

/**
 * Skeleton, a melee monster
 */
public class Skeleton extends BaseMonster implements Evolutionary {
	// TODO: change sword multiplier to make game balance (in percent)
	private final int MULTIPLIER = 35;
	private static int level = 1;
	
	/**
	 * Create new monster
	 * @param name monster's name
	 * @param swordStats monster's sword stats
	 * @param magicStats monster's magic stats
	 * @param dropMoney monster's drop money
	 */
	public Skeleton(String name, int swordStats, int magicStats, int dropMoney) {
		super(name, swordStats, magicStats, dropMoney);
	}
	
	public void evolve(int swordStats, int magicStats) {
		try {
			this.setSwordStats(this.getSwordStats() + swordStats * MULTIPLIER / 100);
			this.setDropMoney(this.getDropMoney() + swordStats * MULTIPLIER / 100);
		}
		catch (InvalidValueException e) {
			System.out.println("Skeleton evolved failed by " + e);
		}
	}
	
	public void respawn() {
		try {
			this.setSwordStats(this.getSwordStats() + MULTIPLIER * level / 7);
			this.setMagicStats(this.getMagicStats() + 15 * level / 7);
			this.setDropMoney(this.getDropMoney() + MULTIPLIER * level / 7);
			level++;
		}
		catch (InvalidValueException e) {
			System.out.println(e);
		}
	}
	
	public Color getColor() {
		return Color.LIGHTGRAY;
	}
	
	public int getLevel() {
		return level;
	}
}
