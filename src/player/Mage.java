package player;

import customException.InvalidValueException;

public class Mage extends BasePlayer implements GoodAtMagic {	
	public Mage(String name) throws InvalidValueException{
		super(name);
	}
	
	// TODO: (optional) fix the formula to make game balance
	public double calculateWinRate(double winRateSword, double winRateMagic) {
		return (winRateSword + 3 * winRateMagic) / 4;
	}
}
