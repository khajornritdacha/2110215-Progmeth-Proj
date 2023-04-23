package player;

import customException.InvalidValueException;

public class SwordMan extends BasePlayer implements GoodAtSword {	
	public SwordMan(String name) throws InvalidValueException{
		super(name);
	}
	
	// TODO: (optional) fix the formula to make game balance
	public double calculateWinRate(double winRateSword, double winRateMagic) {
		return (3 * winRateSword + winRateMagic) / 4;
	}
}
