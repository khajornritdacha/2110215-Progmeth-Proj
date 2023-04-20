package action;

import customException.InvalidValueException;
import monster.Dragon;
import player.BasePlayer;
import utility.Utility;

public class FightBoss implements BaseAction{
	private BasePlayer p1;
	private Dragon b1;
	
	public FightBoss(BasePlayer p1, Dragon b1) {
		this.setP1(p1);
		this.setB1(b1);
	}
	
	public String executeAction() throws InvalidValueException{
		return Utility.fightAgainst(p1, b1);
	}

	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}

	public Dragon getB1() {
		return b1;
	}

	public void setB1(Dragon b1) {
		this.b1 = b1;
	}
}
