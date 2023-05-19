package monster;

import customException.InvalidValueException;
import javafx.scene.image.Image;
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
			this.setSwordStats(this.getSwordStats() + 4 + 40 * level / 100);
			this.setMagicStats(this.getMagicStats() + 4 + 40 * level / 100);
			this.setDropMoney(this.getDropMoney() + 3 + 30 * level / 100);
			level++;
		}
		catch (InvalidValueException e) {
			System.out.println(e);
		}
	}
	
	public Image getPicture() {
		return new Image(getClass().getResource("../assets/zombie.png").toExternalForm(), 1024, 720, false, true);
	}
	
	public Color getColor() {
		return Color.DARKOLIVEGREEN;
	}
	
	public int getLevel() {
		return level;
	}
}
