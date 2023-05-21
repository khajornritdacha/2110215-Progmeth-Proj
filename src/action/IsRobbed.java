package action;

import customException.InvalidValueException;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import player.BasePlayer;
import utility.Utility;

/**
 * Rob player money and stats, executed in random phase
 */
public class IsRobbed extends BaseAction {
	/**
	 * Minimum percentage of money and stats to be robbed
	 */
	static final int MIN_ROB_PERCENT = 5;
	
	/**
	 * Maximum percentage of money and stats to be robbed
	 */
	static final int MAX_ROB_PERCENT = 15;

	/**
	 * Create new IsRobbed action
	 * @param p1 player to be robbed
	 */
	public IsRobbed(BasePlayer p1) {
		this.setP1(p1);
	}
	
	/**
	 * Randomly decrease player stats or money
	 * @return string explaining stats being decreased 
	 * @see Utility
	 */
	public String executeAction() {
		try {
			int rand = Utility.randomInteger(0, 2);
			String action = this.getP1().getName();
			if (rand == 0) {
				int robbedMoney = Utility.calculateExtraBuff(Utility.randomInteger(MIN_ROB_PERCENT, MAX_ROB_PERCENT) * this.getP1().getMoney() / 100);
				if (robbedMoney > 0) {
					this.getP1().setMoney(this.getP1().getMoney() - robbedMoney);
					action += " has been robbed money for " + robbedMoney + " bahts";
				}
			}
			else if (rand == 1) {
				int robbedSword = Utility.calculateExtraBuff(Utility.randomInteger(MIN_ROB_PERCENT, MAX_ROB_PERCENT) * this.getP1().getSwordStats() / 100);
				if (robbedSword > 0) {
					this.getP1().setSwordStats(this.getP1().getSwordStats() - robbedSword);
					action += " has been robbed sword stat for " + robbedSword + " units";
				}
			}
			else if (rand == 2) {
				int robbedMagic = Utility.calculateExtraBuff(Utility.randomInteger(MIN_ROB_PERCENT, MAX_ROB_PERCENT) * this.getP1().getMagicStats() / 100);
				if (robbedMagic > 0) {
					this.getP1().setMagicStats(this.getP1().getMagicStats() - robbedMagic);
					action += " has been robbed magic stat for " + robbedMagic + " units";
				}
			}
			if (action.equals(this.getP1().getName())) {
				action += " has not been robbed any stats";
			}
			return action;
		}
		catch (InvalidValueException e) {
			System.out.println(e);
		}
		return "Nothing";
	}
	
	public Color getColor() {
		return Color.WHITE;
	}
	
	public String toString() {
		return "Be Robbed by a Robber";
	}
	
	public String getDescription() {
		return String.format("Decrease one of the stats for %d-%d%% units", Utility.calculateExtraBuff(MIN_ROB_PERCENT), Utility.calculateExtraBuff(MAX_ROB_PERCENT));
	}
	
	public Image getPicture() {		
		return new Image(ClassLoader.getSystemResource("robber.png").toString(), 1024, 720, false, true);
	}
}
