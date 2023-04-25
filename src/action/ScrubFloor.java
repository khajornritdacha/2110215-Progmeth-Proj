package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.Rich;
import utility.Utility;

/**
 * Add player money when choosing action
 */
public class ScrubFloor extends BaseAction {
	/**
	 * Player to add money
	 */
	private BasePlayer p1;
	
	/**
	 * Create new ScrubFloor action
	 * @param p1 player to add money
	 */
	public ScrubFloor(BasePlayer p1) {
		this.setP1(p1);
	}

	/**
	 * Increase player's money
	 * @return string explaining action
	 * @throws InvalidValueException throw error when value is invalid
	 */
	public String executeAction() throws InvalidValueException {
		return p1.earnMoney(Utility.genMoney());
	}
	
	public Color getColor() {
		return Color.GREEN;
	}
	
	public String toString() {
		return "Scrub the Floor";
	}
	
	public String getDescription() {
		int minStats = Utility.getMinMoneyStats();
		int maxStats = Utility.getMaxMoneyStats();
		if (this.getP1() instanceof Rich) {
			minStats *= Rich.moneyMultiplier;
			maxStats *= Rich.moneyMultiplier;
		}
		minStats = Utility.calculateExtraBuff(minStats);
		maxStats = Utility.calculateExtraBuff(maxStats);
		return String.format("Increase money for %d-%d bahts", minStats, maxStats);
	}
}
