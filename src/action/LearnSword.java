package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import utility.Utility;

public class LearnSword implements BaseAction {
	private BasePlayer p1;
	
	public LearnSword(BasePlayer p1) {
		this.setP1(p1);
	}
	
	public String executeAction() throws InvalidValueException{
		return p1.learnSword(Utility.calculateExtraBuff(Utility.genSwordStats()));
	}
	
	public Color getColor() {
		return Color.GOLD;
	}

	public String toString() {
		return "Learn Sword";
	}
	
	public String getDescription() {
		return String.format("Increase Sword Stats for %d-%d Units", Utility.calculateExtraBuff(Utility.getMinStats()), Utility.calculateExtraBuff(Utility.getMaxStats()));
	}
	
	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}
}
