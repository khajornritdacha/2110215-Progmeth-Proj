package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;
import player.BasePlayer;

/**
 * Action interface proceeding game state
 * @author JomnoiZ
 */
public interface BaseAction {
	/**
	 * Execute each action
	 * @return result string of each action
	 * @throws InvalidValueException
	 */
	String executeAction() throws InvalidValueException;
	
	/**
	 * Get the color particular to each action
	 * @return color particular to each action
	 */
	Color getColor();
	
	/**
	 * Return the string describing action
	 * @return string describing action
	 */
	String toString();
	
	/**
	 * string describing result of action
	 * @return string describing result of action
	 */
	String getDescription();
}
