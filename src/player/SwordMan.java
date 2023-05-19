package player;

import customException.InvalidValueException;

/**
 * Swordman, expert in sword
 */
public class SwordMan extends BasePlayer implements GoodAtSword {	
	/**
	 * Create new player with SwordMan role
	 * @param name player name
	 * @throws InvalidValueException
	 */
	public SwordMan(String name) throws InvalidValueException{
		super(name);
	}
	
	// TODO: (optional) fix the formula to make game balance
	public double calculateWinRate(double winRateSword, double winRateMagic) {
		return (3 * winRateSword + winRateMagic) / 4;
	}
}
