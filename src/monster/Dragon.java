package monster;

import customException.InvalidValueException;
import javafx.scene.paint.Color;

/**
 * Dragon, a boss in this game
 */
public class Dragon extends BaseMonster implements Evolutionary {		
	/**
	 * Level of this boss
	 */
	public static int level = 1;
	
	/**
	 * Create new monster
	 * @param name monster's name
	 * @param swordStats monster's sword stats
	 * @param magicStats monster's magic stats
	 * @param dropMoney monster's drop money
	 */
	public Dragon(String name, int swordStats, int magicStats, int dropMoney) {
		super(name, swordStats, magicStats, dropMoney);
	}
	
	public void evolve(int swordStats, int magicStats) {
		try {
			this.setSwordStats(this.getSwordStats() + swordStats / 4);
			this.setMagicStats(this.getMagicStats() + magicStats / 4);
			this.setDropMoney(this.getDropMoney() + (swordStats + magicStats) / 8);
		}
		catch (InvalidValueException e) {
			System.out.println("Dragon evolved failed by " + e);
		}
	}
	
	public void respawn() {
		try {
			this.setSwordStats(this.getSwordStats() + 40 + 400 * level / 100);
			this.setMagicStats(this.getMagicStats() + 41 + 410 * level / 100);
			this.setDropMoney(this.getDropMoney() + 25 + 250 * level / 100);
			level++;
		}
		catch (InvalidValueException e) {
			System.out.println(e);
		}
	}
	
	public Color getColor() {
		return Color.DARKRED;
	}
	
	public int getLevel() {
		return level;
	}
}
