package action;

import customException.InvalidValueException;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.GoodAtMagic;
import utility.Utility;

/**
 * Tremendously increase player magic stats, executed in random action phase 
 */
public class FindMageMaster extends BaseAction {	
	/**
	 * Create new FindMageMaster action 
	 * @param p1 player to increase magic stats
	 */
	public FindMageMaster(BasePlayer p1) {
		this.setP1(p1);
	}
	
	/**
	 * Tremendously increase player magic stats
	 * @return string explaining action
	 * @see Utility
	 */
	public String executeAction() throws InvalidValueException{
		return this.getP1().learnMagic(2 * Utility.genMagicStats());
	}
	
	public Color getColor() {
		return Color.MEDIUMPURPLE;
	}
	
	public String toString() {
		return "Find Mage Master";
	}
	
	public String getDescription() {
		int minStats = 2 * Utility.getMinMagicStats();
		int maxStats = 2 * Utility.getMaxMagicStats();
		if (this.getP1() instanceof GoodAtMagic) {
			minStats *= GoodAtMagic.magicMultiplier;
			maxStats *= GoodAtMagic.magicMultiplier;
		}
		minStats = Utility.calculateExtraBuff(minStats);
		maxStats = Utility.calculateExtraBuff(maxStats);
		return String.format("Increase magic stats for %d-%d units", minStats, maxStats);
	}
	
	public Image getPicture() {
		return new Image(ClassLoader.getSystemResource("enchantedBook.png").toString(), 1024, 720, false, true);
	}
}
