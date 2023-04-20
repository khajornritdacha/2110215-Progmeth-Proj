package action;

import customException.InvalidValueException;
import player.BasePlayer;
import utility.Utility;

public class FindMageMaster implements BaseAction{
	private BasePlayer p1;
	private int randMagicStats;
	
	public FindMageMaster(BasePlayer p1) {
//		TODO: change magic number
		this.setP1(p1);
		this.setRandMagicStats(10);
	}
	
	public String executeAction() throws InvalidValueException{
		return p1.learnMagic(Utility.calculateExtraBuff(this.getRandMagicStats()));
	}

	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}

	public int getRandMagicStats() {
		return randMagicStats;
	}

	public void setRandMagicStats(int randMagicStats) {
		this.randMagicStats = randMagicStats;
	}
}
