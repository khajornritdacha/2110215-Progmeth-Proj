package action;

import java.util.ArrayList;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import utility.Utility;

/**
 * Rob player money and stats, executed in random phase
 */
public class IsRobbed implements BaseAction {
	/**
	 * Player to be robbed
	 */
	private BasePlayer p1;
	
	/**
	 * Minimum percentage of money and stats to be robbed
	 */
	static final int MIN_ROB_PERCENT = 14;
	
	/**
	 * Maximum percentage of money and stats to be robbed
	 */
	static final int MAX_ROB_PERCENT = 49;

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
			int rand = Utility.randomInteger(1, 7);
			String action = this.getP1().getName() + " has been robbed : ";
			ArrayList<String> robbedThings = new ArrayList<String>();
			if ((rand & 1) > 0) {
				int robbedMoney = Utility.calculateExtraBuff(Utility.randomInteger(MIN_ROB_PERCENT, MAX_ROB_PERCENT) * p1.getMoney() / 100);
				if (robbedMoney > 0) {
					p1.setMoney(p1.getMoney() - robbedMoney);
					robbedThings.add("Money(" + robbedMoney + ")");
				}
			}
			if ((rand & 2) > 0) {
				int robbedSword = Utility.calculateExtraBuff(Utility.randomInteger(MIN_ROB_PERCENT, MAX_ROB_PERCENT) * p1.getSwordStats() / 100);
				if (robbedSword > 0) {
					p1.setSwordStats(p1.getSwordStats() - robbedSword);
					robbedThings.add("Sword(" + robbedSword + ")");
				}
			}
			if ((rand & 4) > 0) {
				int robbedMagic = Utility.calculateExtraBuff(Utility.randomInteger(MIN_ROB_PERCENT, MAX_ROB_PERCENT) * p1.getMagicStats() / 100);
				if (robbedMagic > 0) {
					p1.setMagicStats(p1.getMagicStats() - robbedMagic);
					robbedThings.add("Magic(" + robbedMagic + ")");
				}
			}
			return action + String.join(" | ", robbedThings);
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
