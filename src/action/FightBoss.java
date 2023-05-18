package action;

import java.util.ArrayList;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import monster.Dragon;
import player.BasePlayer;
import utility.Utility;

/**
 * Executed when player fight with boss
 */
public class FightBoss implements BaseAction {
	/**
	 * Players to fight boss of this action
	 */
	private ArrayList<BasePlayer> players;
	
	/**
	 * Boss of this action
	 */
	private Dragon b1;
	
	/**
	 * Create new fight boss event with single player
	 * @param p1 player to fight boss
	 * @param b1 boss to fight
	 */
	public FightBoss(BasePlayer p1, Dragon b1) {
		this.setPlayers(new ArrayList<BasePlayer>());
		this.players.add(p1);
		this.setB1(b1);
	}
	
	/**
	 * Create new fight boss event with multiple players
	 * @param players list of players to fight boss
	 * @param b1 boss to fight
	 */
	public FightBoss(ArrayList<BasePlayer> players, Dragon b1) {
		this.setPlayers(players);
		this.setB1(b1);
	}
	
	/**
	 * Invoke fight boss event
	 * @return string explaining action
	 * @see Utility
	 */
	public String executeAction() throws InvalidValueException{
		return Utility.fightBoss(players, b1);
	}
	
	public Color getColor() {
		return Color.DARKRED;
	}
	
	public String toString() {
		return "Fight with the Boss";
	}
	
	public String getDescription() {
		return "";
	}

	public ArrayList<BasePlayer> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<BasePlayer> players) {
		this.players = players;
	}

	/**
	 * Get dragon involving this action
	 * @return dragon involves this action
	 */
	public Dragon getB1() {
		return b1;
	}

	/**
	 * Set dragon this action 
	 * @param b1 dragon involves this action
	 */
	public void setB1(Dragon b1) {
		this.b1 = b1;
	}
}
