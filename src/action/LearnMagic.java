package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.GoodAtMagic;
import utility.Utility;

public class LearnMagic implements BaseAction {
private BasePlayer p1;
	
	public LearnMagic(BasePlayer p1) {
		this.setP1(p1);
	}
	
	public String executeAction() throws InvalidValueException{
		return p1.learnMagic(Utility.genMagicStats());
	}
	
	public Color getColor() {
		return Color.PURPLE;
	}
	
	public String toString() {
		return "Learn Magic";
	}
	
	public String getDescription() {
		int minStats = Utility.calculateExtraBuff(Utility.getMinMagicStats());
		int maxStats = Utility.calculateExtraBuff(Utility.getMaxMagicStats());
		if (this.getP1() instanceof GoodAtMagic) {
			minStats *= GoodAtMagic.magicMultiplier;
			maxStats *= GoodAtMagic.magicMultiplier;
		}
		return String.format("Increase Magic Stats for %d-%d Units", minStats, maxStats);
	}

	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}
}
