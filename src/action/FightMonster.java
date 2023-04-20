package action;

import customException.InvalidValueException;
import monster.BaseMonster;
import player.BasePlayer;
import utility.Utility;

public class FightMonster implements BaseAction {
	private BasePlayer p1;
	private BaseMonster m1;
	
	public FightMonster(BasePlayer p1, BaseMonster m1) {
		this.setP1(p1);
		this.setM1(m1);
	}
	
	public String executeAction() throws InvalidValueException{
		return Utility.fightAgainst(p1, m1);
	}

	public BasePlayer getP1() {
		return p1;
	}

	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}

	public BaseMonster getM1() {
		return m1;
	}

	public void setM1(BaseMonster m1) {
		this.m1 = m1;
	}
}
