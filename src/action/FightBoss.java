package action;

import java.util.ArrayList;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import monster.Dragon;
import player.BasePlayer;
import utility.Utility;

public class FightBoss implements BaseAction {
	private ArrayList<BasePlayer> players;
	private Dragon b1;
	
	public FightBoss(BasePlayer p1, Dragon b1) {
		this.setPlayers(new ArrayList<BasePlayer>());
		this.players.add(p1);
		this.setB1(b1);
	}
	
	public FightBoss(ArrayList<BasePlayer> players, Dragon b1) {
		this.setPlayers(players);
		this.setB1(b1);
	}
	
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

	public Dragon getB1() {
		return b1;
	}

	public void setB1(Dragon b1) {
		this.b1 = b1;
	}
}
