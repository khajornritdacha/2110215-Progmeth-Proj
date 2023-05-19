package monster;

import customException.InvalidValueException;
import javafx.scene.paint.Color;

/**
 * Goblin, weakest monster in this game
 */
public class Goblin extends BaseMonster {
	private static int level = 1;
	
	/**
	 * Create new monster
	 * @param name monster's name
	 * @param swordStats monster's sword stats
	 * @param magicStats monster's magic stats
	 * @param dropMoney monster's drop money
	 */
	public Goblin(String name, int swordStats, int magicStats, int dropMoney) {
		super(name, swordStats, magicStats, dropMoney);
	}
	
	public void respawn() {
		try {
			this.setSwordStats(this.getSwordStats() + 10 * level / 7);
			this.setMagicStats(this.getMagicStats() + 10 * level / 7);
			this.setDropMoney(this.getDropMoney() + 10 * level / 7);
			level++;
		}
		catch (InvalidValueException e) {
			System.out.println(e);
		}
	}
	
	public Color getColor() {
		return Color.DARKGREEN;
	}
	
	public int getLevel() {
		return level;
	}
}
