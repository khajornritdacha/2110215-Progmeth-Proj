package player;

import customException.InvalidValueException;

public class TheRich extends BasePlayer implements Rich {
	public TheRich(String name) throws InvalidValueException{
		super(name);
	}
	
	// TODO: (optional) fix the formula to make game balance
	public double calculateWinRate(double winRateSword, double winRateMagic) {
		return (winRateSword + winRateMagic) / 2;
	}
}
