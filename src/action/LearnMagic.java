package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.GoodAtMagic;
import utility.Utility;

/**
 * Increase player magic stats when choosing action
 */
public class LearnMagic extends BaseAction {
	/**
	 * Player to increase magic stats
	 */
	private BasePlayer p1;
	
	/**
	 * Create new LearnMagic action
	 * @param p1 player to increase magic stats
	 */
	public LearnMagic(BasePlayer p1) {
		this.setP1(p1);
	}

	/**
	 * Randomly increase player magic stats
	 * @return string explaining action
	 * @see Utility
	 */
	public String executeAction() throws InvalidValueException{
		return p1.learnMagic(Utility.genMagicStats());
	}
	
	public Color getColor() {
		return Color.PURPLE;
	}
	
	public String toString() {
		return "Learn Magic";
	}
	
	public String getDescription() {
		int minStats = Utility.getMinMagicStats();
		int maxStats = Utility.getMaxMagicStats();
		if (this.getP1() instanceof GoodAtMagic) {
			minStats *= GoodAtMagic.magicMultiplier;
			maxStats *= GoodAtMagic.magicMultiplier;
		}
		minStats = Utility.calculateExtraBuff(minStats);
		maxStats = Utility.calculateExtraBuff(maxStats);
		return String.format("Increase magic stats for %d-%d units", minStats, maxStats);
	}
}
