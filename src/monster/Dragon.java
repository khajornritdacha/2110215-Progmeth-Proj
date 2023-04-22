package monster;

import customException.InvalidValueException;
import javafx.scene.paint.Color;

public class Dragon extends BaseMonster implements Evolutionary {
	// TODO: change sword multiplier to make game balance (in percent)
	private final int SWORD_MULTIPLIER = 81;
	private final int MAGIC_MULTIPLIER = 82;
	public static int level = 1;
	
	public Dragon(String name, int swordStats, int magicStats, int dropMoney) {
		super(name, swordStats, magicStats, dropMoney);
	}
	
	public void evolve(int swordStats, int magicStats) {
		try {
			this.setSwordStats(this.getSwordStats() + swordStats * SWORD_MULTIPLIER / 100);
			this.setMagicStats(this.getMagicStats() + magicStats * MAGIC_MULTIPLIER / 100);
			this.setDropMoney(this.getDropMoney() + (swordStats + magicStats) * (SWORD_MULTIPLIER + MAGIC_MULTIPLIER) / 400);
		}
		catch (InvalidValueException e) {
			System.out.println("Dragon evolved failed by " + e);
		}
	}
	
	public void respawn() {
		try {
			this.setSwordStats(this.getSwordStats() + SWORD_MULTIPLIER * level / 7);
			this.setMagicStats(this.getMagicStats() + MAGIC_MULTIPLIER * level / 7);
			this.setDropMoney(this.getDropMoney() + (SWORD_MULTIPLIER + MAGIC_MULTIPLIER) * level / 14);
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
