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
	
	public void executeAction() throws InvalidValueExecption{
		p1.learnSword(randMagicStats);
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
