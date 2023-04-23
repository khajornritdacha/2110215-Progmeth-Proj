package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.Rich;
import utility.Utility;

public class WinLottery implements BaseAction {
private BasePlayer p1;
	
	public WinLottery(BasePlayer p1) {
		this.setP1(p1);
	}

	public String executeAction() throws InvalidValueException {
		return p1.earnMoney(Utility.calculateExtraBuff(2 * Utility.genMoney()));
	}
	
	public Color getColor() {
		return Color.GREEN;
	}
	
	public String toString() {
		return "Win Lottery";
	}
	
	public String getDescription() {
		int minStats = Utility.calculateExtraBuff(2 * Utility.getMinStats());
		int maxStats = Utility.calculateExtraBuff(2 * Utility.getMaxStats());
		if (this.getP1() instanceof Rich) {
			minStats *= Rich.moneyMultiplier;
			maxStats *= Rich.moneyMultiplier;
		}
		return String.format("Increase Money for %d-%d Bahts", minStats, maxStats);
	}
	
	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}	
}
