package monster;

import customException.InvalidValueException;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Wizard, a magic monster
 */
public class Wizard extends BaseMonster implements Evolutionary {
	// TODO: change magic multiplier to make game balance (in percent)
	private static int level = 1;
	
	/**
	 * Create new monster
	 * @param name monster's name
	 * @param swordStats monster's sword stats
	 * @param magicStats monster's magic stats
	 * @param dropMoney monster's drop money
	 */
	public Wizard(String name, int swordStats, int magicStats, int dropMoney) {
		super(name, swordStats, magicStats, dropMoney);
	}
	
	public void evolve(int swordStats, int magicStats) {
		try {
			this.setSwordStats(this.getSwordStats() + swordStats / 7);
			this.setMagicStats(this.getMagicStats() + magicStats / 5);
			this.setDropMoney(this.getDropMoney() + (swordStats + magicStats) / 14);
		}
		catch (InvalidValueException e) {
			System.out.println("Wizzard evolved failed by " + e);
		}
	}
	
	public void respawn() {
		try {
			this.setSwordStats(this.getSwordStats() + 8 + 80 * level / 100);
			this.setMagicStats(this.getMagicStats() + 16 + 160 * level / 100);
			this.setDropMoney(this.getDropMoney() + 11 + 110 * level / 100);
			level++;
		}
		catch (InvalidValueException e) {
			System.out.println(e);
		}
	}
	
	public Image getPicture() {
		return new Image(getClass().getResource("../assets/witch.png").toExternalForm(), 1024, 720, false, true);
	}
	
	public Color getColor() {
		return Color.ROYALBLUE;
	}
	
	public int getLevel() {
		return level;
	}
}
