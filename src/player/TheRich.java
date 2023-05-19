package player;

import customException.InvalidValueException;

/**
 * The rich, earning money quicker than any others
 */
public class TheRich extends BasePlayer implements Rich {
	/**
	 * Create new player with TheRich role
	 * @param name player name
	 * @throws InvalidValueException
	 */
	public TheRich(String name) throws InvalidValueException{
		super(name);
	}
	
	// TODO: (optional) fix the formula to make game balance
	public double calculateWinRate(double winRateSword, double winRateMagic) {
		return (winRateSword + winRateMagic) / 2;
	}
}
