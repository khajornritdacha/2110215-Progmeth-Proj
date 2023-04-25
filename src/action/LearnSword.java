package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.GoodAtMagic;
import player.GoodAtSword;
import utility.Utility;

/**
 * Increase player magic stats when choosing action
 */
public class LearnSword extends BaseAction {
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
		int minStats = Utility.getMinSwordStats();
		int maxStats = Utility.getMaxSwordStats();
		if (this.getP1() instanceof GoodAtMagic) {
			minStats *= GoodAtSword.swordMultiplier;
			maxStats *= GoodAtSword.swordMultiplier;
		}
		minStats = Utility.calculateExtraBuff(minStats);
		maxStats = Utility.calculateExtraBuff(maxStats);
		return String.format("Increase sword stats for %d-%d units", minStats, maxStats);
	}
}
