package player;

import customException.InvalidValueException;

/**
 * Mage, expert in magic 
 */
public class Mage extends BasePlayer implements GoodAtMagic {	
	/**
	 * Create new player with Mage role
	 * @param name player name
	 * @throws InvalidValueException throw error when value is invalid
	 */
	public Mage(String name) throws InvalidValueException{
		super(name);
	}
	
	// TODO: (optional) fix the formula to make game balance
	public double calculateWinRate(double winRateSword, double winRateMagic) {
		return (winRateSword + 3 * winRateMagic) / 4;
	}
}
