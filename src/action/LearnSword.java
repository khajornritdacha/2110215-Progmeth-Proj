package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.GoodAtSword;
import utility.Utility;

/**
 * Increase player magic stats when choosing action
 */
public class LearnSword implements BaseAction {
	/**
	 * Player to increase sword stats
	 */
	private BasePlayer p1;
	
	/**
	 * Create new LearnSword action
	 * @param p1 player to increase sword stats
	 */
	public LearnSword(BasePlayer p1) {
		this.setP1(p1);
	}
	
	/**
	 * Randomly increase player sword stats
	 * @return string explaining action
	 * @see Utility
	 */
	public String executeAction() throws InvalidValueException{
		return p1.learnSword(Utility.genSwordStats());
	}
	
	public Color getColor() {
		return Color.GOLD;
	}

	public String toString() {
		return "Learn Sword";
	}
	
	public String getDescription() {
		int minStats = Utility.calculateExtraBuff(Utility.getMinSwordStats());
		int maxStats = Utility.calculateExtraBuff(Utility.getMaxSwordStats());
		if (this.getP1() instanceof GoodAtSword) {
			minStats *= GoodAtSword.swordMultiplier;
			maxStats *= GoodAtSword.swordMultiplier;
		}
		return String.format("Increase sword stats for %d-%d units", minStats, maxStats);
	}
	
	/**
	 * Get player involving this action
	 * @return player involves this action
	 */
	public BasePlayer getP1() {
		return p1;
	}

	/**
	 * Set player involving this action
	 * @param p1 player involves this action
	 */
	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}
}
