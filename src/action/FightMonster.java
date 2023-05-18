package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import monster.BaseMonster;
import player.BasePlayer;
import utility.Utility;

/**
 * Executed when player fight with monster
 */
public class FightMonster implements BaseAction {
	/**
	 * Player to fight monster of this action
	 */
	private BasePlayer p1;
	
	/**
	 * Monster of this action
	 */
	private BaseMonster m1;
	
	/**
	 * Boolean whether to show monster name or not
	 */
	private boolean showMonsterName;
	
	/**
	 * Creating new fight monster action
	 * @param p1 Player to fight with monster
	 * @param m1 Monster to fight with
	 * @param showMonsterName Boolean to show monster name
	 */
	public FightMonster(BasePlayer p1, BaseMonster m1, boolean showMonsterName) {
		this.setP1(p1);
		this.setM1(m1);
		this.setShowMonsterName(showMonsterName);
	}

	/**
	 * Invoke fight monster event
	 * @return string explaining action
	 * @see Utility
	 */
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

	/**
	 * Get player involving this action
	 * @return player involves this action
	 */
	public BasePlayer getP1() {
		return p1;
	}

	/**
	 * Set player involving this action
	 * @param p1 player involves this action
	 */
	public void setP1(BasePlayer p1) {
		this.p1 = p1;
	}

	/**
	 * Get monster involving this action
	 * @return monster involves this action
	 */
	public BaseMonster getM1() {
		return m1;
	}

	/**
	 * Set monster involving this action
	 * @param m1 monster involves this action
	 */
	public void setM1(BaseMonster m1) {
		this.m1 = m1;
	}

	/**
	 * Set ShowMonsterName field to either true or false
	 * @param showMonsterName new value of ShowMonsterName
	 */
	public void setShowMonsterName(boolean showMonsterName) {
		this.showMonsterName = showMonsterName;
	}
	
	/**
	 * Return true if this action is to show monster name, otherwise false
	 * @return showMonsterName field
	 */
	public boolean showMonsterName() {
		return this.showMonsterName;
	}
}
