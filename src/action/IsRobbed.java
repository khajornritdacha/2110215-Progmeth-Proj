package action;

import player.BasePlayer;

public class IsRobbed implements BaseAction {
	private int randMoney;
	private BasePlayer p1;
	
	public IsRobbed(BasePlayer p1) {
//		TODO: change amount of money to random based on passed turn (the more turns passed the more money drop)
		this.randMoney = 10;
		this.p1 = p1;
	}
	
	public String executeAction() {
//		Don't use p1.earnMoney as the effect will be multiplied
		p1.setMoney(p1.getMoney() - randMoney);
		return this.toString();
	}
	
	public String toString() {
		return this.getP1().getName() + " has been robbed " + this.getRandMoney() + " bahts.";
	}
	
	public int getRandMoney() {
		return randMoney;
	}

	public void setRandMoney(int randMoney) {
		if (randMoney < 1) randMoney = 1;
		this.randMoney = randMoney;
	}

	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}
}
