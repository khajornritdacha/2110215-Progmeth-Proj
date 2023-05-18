package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.Rich;
import utility.Utility;

/**
 * Randomly add player's money, executed in random action phase
 */
public class WinLottery implements BaseAction {
	/**
	 * Player to add money
	 */
	private BasePlayer p1;
	
	/**
	 * Create new WinLottery action
	 * @param p1 player to add money
	 */
	public WinLottery(BasePlayer p1) {
		this.setP1(p1);
	}

	/**
	 * Tremendously add player's money
	 * @return string explaining action
	 * @see Utility
	 */
	public String executeAction() throws InvalidValueException {
		return p1.earnMoney(2 * Utility.genMoney());
	}
	
	public Color getColor() {
		return Color.GREEN;
	}
	
	public String toString() {
		return "Win Lottery";
	}
	
	public String getDescription() {
		int minStats = Utility.calculateExtraBuff(2 * Utility.getMinMoneyStats());
		int maxStats = Utility.calculateExtraBuff(2 * Utility.getMaxMoneyStats());
		if (this.getP1() instanceof Rich) {
			minStats *= Rich.moneyMultiplier;
			maxStats *= Rich.moneyMultiplier;
		}
		return String.format("Increase money for %d-%d bahts", minStats, maxStats);
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
