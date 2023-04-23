package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.Rich;
import utility.Utility;

public class ScrubFloor implements BaseAction {
	private BasePlayer p1;
	
	public ScrubFloor(BasePlayer p1) {
		this.setP1(p1);
	}

	public String executeAction() throws InvalidValueException {
		return p1.earnMoney(Utility.calculateExtraBuff(Utility.genMoney()));
	}
	
	public Color getColor() {
		return Color.GREEN;
	}
	
	public String toString() {
		return "Scrub the Floor";
	}
	
	public String getDescription() {
		int minStats = Utility.calculateExtraBuff(Utility.getMinStats());
		int maxStats = Utility.calculateExtraBuff(Utility.getMaxStats());
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
