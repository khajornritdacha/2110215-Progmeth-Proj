package action;

import customExecption.InvalidValueExecption;
import player.BasePlayer;

public class WinLottery implements BaseAction {
	private int randMoney;
	private BasePlayer p1;
	
	public WinLottery(BasePlayer p1) {
//		TODO: change amount of money to random based on passed turn (the more turns passed the more money drop)
		this.randMoney = 10;
		this.p1 = p1;
	}

	public String executeAction() throws InvalidValueExecption{
		return p1.earnMoney(randMoney);
	}
	
	public String toString() {
		return this.getP1().getName() + " has earned " + this.getRandMoney() + " bahts.";
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
