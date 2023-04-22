package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import monster.BaseMonster;
import player.BasePlayer;
import utility.Utility;

public class FightMonster implements BaseAction {
	private BasePlayer p1;
	private BaseMonster m1;
	private boolean showMonsterName;
	
	public FightMonster(BasePlayer p1, BaseMonster m1, boolean showMonsterName) {
		this.setP1(p1);
		this.setM1(m1);
		this.setShowMonsterName(showMonsterName);
	}

	public String executeAction() throws InvalidValueException{
		return Utility.fightAgainst(p1, m1);
	}
	
	public Color getColor() {
		return Color.RED;
	}
	
	public String toString() {
		return String.format("Fight with %s", (this.showMonsterName() ? m1.getName() : "Monsters"));
	}
	
	public String getDescription() {
		return "";
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
	
	public boolean isShowMonsterName() {
		return showMonsterName;
	}

	public void setShowMonsterName(boolean showMonsterName) {
		this.showMonsterName = showMonsterName;
	}
	
	public boolean showMonsterName() {
		return this.showMonsterName;
	}
}
