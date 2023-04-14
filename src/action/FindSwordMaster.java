package action;

import player.BasePlayer;

public class FindSwordMaster implements BaseAction{
	private BasePlayer p1;
	private int randSwordStats;
	
	public FindSwordMaster(BasePlayer p1) {
//		TODO: change magic number
		this.p1 = p1;
		this.randSwordStats = 10;
	}
	
	public void executeAction() {
		p1.learnSword(randSwordStats);
	}

	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}

	public int getRandSwordStats() {
		return randSwordStats;
	}

	public void setRandSwordStats(int randSwordStats) {
		this.randSwordStats = randSwordStats;
	}
}
