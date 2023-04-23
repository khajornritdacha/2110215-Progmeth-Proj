package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.GoodAtSword;
import utility.Utility;

public class FindSwordMaster implements BaseAction{
	private BasePlayer p1;
	
	public FindSwordMaster(BasePlayer p1) {
		this.setP1(p1);
	}
	
	public String executeAction() throws InvalidValueException{
		return p1.learnSword(2 * Utility.genSwordStats());
	}
	
	public Color getColor() {
		return Color.GOLD;
	}

	public String toString() {
		return "Find Sword Master";
	}
	
	public String getDescription() {
		int minStats = Utility.calculateExtraBuff(2 * Utility.getMinSwordStats());
		int maxStats = Utility.calculateExtraBuff(2 * Utility.getMaxSwordStats());
		if (this.getP1() instanceof GoodAtSword) {
			minStats *= GoodAtSword.swordMultiplier;
			maxStats *= GoodAtSword.swordMultiplier;
		}
		return String.format("Increase Sword Stats for %d-%d Units", minStats, maxStats);
	}
	
	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}
}
