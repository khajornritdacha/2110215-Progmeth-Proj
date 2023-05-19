package action;

import customException.InvalidValueException;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.Rich;
import utility.Utility;

/**
 * Randomly add player's money, executed in random action phase
 */
public class WinLottery extends BaseAction {
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
		return this.getP1().earnMoney(2 * Utility.genMoney());
	}
	
	public Color getColor() {
		return Color.GREEN;
	}
	
	public String toString() {
		return "Win Lottery";
	}
	
	public String getDescription() {
		int minStats = 2 * Utility.getMinMoneyStats();
		int maxStats = 2 * Utility.getMaxMoneyStats();
		if (this.getP1() instanceof Rich) {
			minStats *= Rich.moneyMultiplier;
			maxStats *= Rich.moneyMultiplier;
		}
		minStats = Utility.calculateExtraBuff(minStats);
		maxStats = Utility.calculateExtraBuff(maxStats);
		return String.format("Increase money for %d-%d bahts", minStats, maxStats);
	}
	
	public Image getPicture() {
		return new Image(getClass().getResource("../assets/money.png").toExternalForm(), 1024, 720, false, true);
	}
}
