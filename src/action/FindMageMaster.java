package action;

import customExecption.InvalidValueExecption;
import player.BasePlayer;

public class FindMageMaster implements BaseAction{
	private BasePlayer p1;
	private int randMagicStats;
	
	public FindMageMaster(BasePlayer p1) {
//		TODO: change magic number
		this.p1 = p1;
		this.randMagicStats = 10;
	}
	
	public String executeAction() throws InvalidValueExecption{
		return p1.learnSword(randMagicStats);
	}
	
	public String toString() {
		return this.getP1().getName() + " has learnt magic for " + this.getRandMagicStats() + " units.";
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
