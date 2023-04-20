package action;

import customException.InvalidValueException;
import player.BasePlayer;
import utility.Utility;

public class WinLottery implements BaseAction {
	private int randMoney;
	private BasePlayer p1;
	
	public WinLottery(BasePlayer p1) {
//		TODO: change amount of money to random based on passed turn (the more turns passed the more money drop)
		this.setP1(p1);
		this.setRandMoney(10);
	}

	public String executeAction() throws InvalidValueException{
		return p1.earnMoney(Utility.calculateExtraBuff(this.getRandMoney()));
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
