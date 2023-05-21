package monster;

import customException.InvalidValueException;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Skeleton, a melee monster
 */
public class Skeleton extends BaseMonster implements Evolutionary {
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
			this.setSwordStats(this.getSwordStats() + swordStats / 5);
			this.setMagicStats(this.getMagicStats() + magicStats / 7);
			this.setDropMoney(this.getDropMoney() + (swordStats + magicStats) / 14);
		}
		catch (InvalidValueException e) {
			System.out.println("Skeleton evolved failed by " + e);
		}
	}
	
	public void respawn() {
		try {
			this.setSwordStats(this.getSwordStats() + 15 + 150 * level / 100);
			this.setMagicStats(this.getMagicStats() + 7 + 70 * level / 100);
			this.setDropMoney(this.getDropMoney() + 10 + 100 * level / 100);
			level++;
		}
		catch (InvalidValueException e) {
			System.out.println(e);
		}
	}
	
	public Image getPicture() {
		return new Image(ClassLoader.getSystemResource("witherSkeleton.png").toString(), 1024, 720, false, true);
	}
	
	public Color getColor() {
		return Color.LIGHTGRAY;
	}
	
	public int getLevel() {
		return level;
	}
}
