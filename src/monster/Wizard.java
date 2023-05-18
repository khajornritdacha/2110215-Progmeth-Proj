package monster;

import customException.InvalidValueException;
import javafx.scene.paint.Color;

public class Wizard extends BaseMonster implements Evolutionary {
	// TODO: change magic multiplier to make game balance (in percent)
	private final int MULTIPLIER = 38;
	private static int level = 1;
	
	public Wizard(String name, int swordStats, int magicStats, int dropMoney) {
		super(name, swordStats, magicStats, dropMoney);
	}
	
	public void evolve(int swordStats, int magicStats) {
		try {
			this.setMagicStats(this.getMagicStats() + magicStats * MULTIPLIER / 100);
			this.setDropMoney(this.getDropMoney() + magicStats * MULTIPLIER / 100);
		}
		catch (InvalidValueException e) {
			System.out.println("Wizzard evolved failed by " + e);
		}
	}
	
	public void respawn() {
		try {
			this.setSwordStats(this.getSwordStats() + 16 * level / 7);
			this.setMagicStats(this.getMagicStats() + MULTIPLIER * level / 7);
			this.setDropMoney(this.getDropMoney() + MULTIPLIER * level / 7);
			level++;
		}
		catch (InvalidValueException e) {
			System.out.println(e);
		}
	}
	
	public Color getColor() {
		return Color.ROYALBLUE;
	}
	
	public int getLevel() {
		return level;
	}
}
