package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.GoodAtSword;
import utility.Utility;

/**
 * Tremendously increase player sword stats, executed in random action phase 
 */
public class FindSwordMaster extends BaseAction{
	/**
	 * Player to increase sword stats
	 */
	private BasePlayer p1;
	
	/**
	 * Create new FindSwordMaster action
	 * @param p1 player to increase sword stats
	 */
	public FindSwordMaster(BasePlayer p1) {
		this.setP1(p1);
	}
	
	/**
	 * Tremendously increase player sword stats
	 * @return string explaining action
	 * @see Utility
	 */
	public String executeAction() throws InvalidValueException{
		return p1.learnSword(2 * Utility.genSwordStats());
	}
	
	public Color getColor() {
		return Color.GOLD;
	}

	public String toString() {
		return "Find Sword Master";
	}
	
	public String getDescription() {
		int minStats = Utility.calculateExtraBuff(2 * Utility.getMinSwordStats());
		int maxStats = Utility.calculateExtraBuff(2 * Utility.getMaxSwordStats());
		if (this.getP1() instanceof GoodAtSword) {
			minStats *= GoodAtSword.swordMultiplier;
			maxStats *= GoodAtSword.swordMultiplier;
		}
		return String.format("Increase sword stats for %d-%d units", minStats, maxStats);
	}
}
