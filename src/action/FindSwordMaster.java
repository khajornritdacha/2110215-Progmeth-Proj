package action;

import customException.InvalidValueException;
import player.BasePlayer;
import utility.Utility;

public class FindSwordMaster implements BaseAction{
	private BasePlayer p1;
	private int randSwordStats;
	
	public FindSwordMaster(BasePlayer p1) {
//		TODO: change sword number
		this.setP1(p1);
		this.setRandSwordStats(10);
	}
	
	public String executeAction() throws InvalidValueException{
		return p1.learnSword(Utility.calculateExtraBuff(this.getRandSwordStats()));
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
