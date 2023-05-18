package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;

/**
 * Action abstract class proceeding game state
 * @author JomnoiZ
 */
public abstract class BaseAction {
	private BasePlayer p1;
	
	/**
	 * Execute each action
	 * @return result string of each action
	 * @throws InvalidValueException
	 */
	public abstract String executeAction() throws InvalidValueException;
	
	/**
	 * Get the color particular to each action
	 * @return color particular to each action
	 */
	public abstract Color getColor();
	
	/**
	 * Return the string describing action
	 * @return string describing action
	 */
	public abstract String toString();
	
	/**
	 * string describing result of action
	 * @return string describing result of action
	 */
	public abstract String getDescription();
	
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
}
