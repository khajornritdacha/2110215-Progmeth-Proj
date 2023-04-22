package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import utility.Utility;

public class LearnMagic implements BaseAction {
private BasePlayer p1;
	
	public LearnMagic(BasePlayer p1) {
		this.setP1(p1);
	}
	
	public String executeAction() throws InvalidValueException{
		return p1.learnMagic(Utility.calculateExtraBuff(Utility.genMagicStats()));
	}
	
	public Color getColor() {
		return Color.PURPLE;
	}
	
	public String toString() {
		return "Learn Magic";
	}
	
	public String getDescription() {
		return String.format("Increase Magic Stats for %d-%d Units", Utility.calculateExtraBuff(Utility.getMinStats()), Utility.calculateExtraBuff(Utility.getMaxStats()));
	}

	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}
}
