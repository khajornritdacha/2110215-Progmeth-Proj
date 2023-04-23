package player;

import customException.InvalidValueException;

public class Farmer extends BasePlayer {	
	public Farmer(String name) throws InvalidValueException{
		super(name);
	}
	
	// TODO: (optional) fix the formula to make game balance
	public double calculateWinRate(double winRateSword, double winRateMagic) {
		return (winRateSword + winRateMagic) / 2;
	}
}
