package action;

import java.util.ArrayList;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;
import utility.Utility;

public class IsRobbed implements BaseAction {
	private BasePlayer p1;
	
	public IsRobbed(BasePlayer p1) {
		this.setP1(p1);
	}

	public String executeAction() {
		// Don't use p1.earnMoney as the effect will be multiplied
		try {
			int rand = Utility.randomInteger(1, 7);
			String action = this.getP1().getName() + " has been robbed : ";
			ArrayList<String> robbedThings = new ArrayList<String>();
			if ((rand & 1) > 0) {
				int robbedMoney = Utility.randomInteger(14, 49) * p1.getMoney() / 100;
				p1.setMoney(p1.getMoney() - Utility.calculateExtraBuff(robbedMoney));
				robbedThings.add("Money for " + robbedMoney + " Bahts");
			}
			if ((rand & 2) > 0) {
				int robbedSword = Utility.randomInteger(14, 49) * p1.getSwordStats() / 100;
				p1.setSwordStats(p1.getSwordStats() - Utility.calculateExtraBuff(robbedSword));
				robbedThings.add("Sword Stats for " + robbedSword + " Units");
			}
			if ((rand & 4) > 0) {
				int robbedMagic = Utility.randomInteger(14, 49) * p1.getMagicStats() / 100;
				p1.setMagicStats(p1.getMagicStats() - Utility.calculateExtraBuff(robbedMagic));
				robbedThings.add("Magic Stats for " + robbedMagic + " Units");
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
		return String.format("Decrease One of the Stats for %d-%d%% Units", Utility.calculateExtraBuff(14), Utility.calculateExtraBuff(49));
	}
	
	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}
}
