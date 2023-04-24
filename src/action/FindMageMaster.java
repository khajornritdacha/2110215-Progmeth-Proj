package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import player.GoodAtMagic;
import utility.Utility;

public class FindMageMaster implements BaseAction {
	private BasePlayer p1;
	
	public FindMageMaster(BasePlayer p1) {
		this.setP1(p1);
	}
	
	public String executeAction() throws InvalidValueException{
		return p1.learnMagic(2 * Utility.genMagicStats());
	}
	
	public Color getColor() {
		return Color.PURPLE;
	}
	
	public String toString() {
		return "Find Mage Master";
	}
	
	public String getDescription() {
		int minStats = Utility.calculateExtraBuff(2 * Utility.getMinMagicStats());
		int maxStats = Utility.calculateExtraBuff(2 * Utility.getMaxMagicStats());
		if (this.getP1() instanceof GoodAtMagic) {
			minStats *= GoodAtMagic.magicMultiplier;
			maxStats *= GoodAtMagic.magicMultiplier;
		}
		return String.format("Increase magic stats for %d-%d units", minStats, maxStats);
	}

	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}
}
