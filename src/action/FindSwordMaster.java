package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import utility.Utility;

public class FindSwordMaster implements BaseAction{
	private BasePlayer p1;
	
	public FindSwordMaster(BasePlayer p1) {
		this.setP1(p1);
	}
	
	public String executeAction() throws InvalidValueException{
		return p1.learnSword(Utility.calculateExtraBuff(2 * Utility.genSwordStats()));
	}
	
	public Color getColor() {
		return Color.GOLD;
	}

	public String toString() {
		return "Find Sword Master";
	}
	
	public String getDescription() {
		return String.format("Increase Sword Stats for %d-%d Units", Utility.calculateExtraBuff(2 * Utility.getMinStats()), Utility.calculateExtraBuff(2 * Utility.getMaxStats()));
	}
	
	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}
}
