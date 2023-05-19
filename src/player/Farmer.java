package player;

import customException.InvalidValueException;

/**
 * Farmer, the most basic roles
 */
public class Farmer extends BasePlayer {	
	/**
	 * Create new player with farmer role 
	 * @param name player name
	 * @throws InvalidValueException
	 */
	public Farmer(String name) throws InvalidValueException{
		super(name);
	}
	
	// TODO: (optional) fix the formula to make game balance
	public double calculateWinRate(double winRateSword, double winRateMagic) {
		return (winRateSword + winRateMagic) / 2;
	}
}
