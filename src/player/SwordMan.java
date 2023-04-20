package player;

import customException.InvalidValueException;

public class SwordMan extends BasePlayer{
//	TODO: adjust the SWORD_MULTIPLIER to balance players
	private final int SWORD_MULTIPLIER = 2;
	private final int MAGIC_MULTIPLIER = 1;
	private final int MONEY_MULTIPLIER = 1;
	
	public SwordMan(String name) throws InvalidValueException{
		super(name);
	}
	
	public String learnSword(int swordStats) throws InvalidValueException{
		setSwordStats(getSwordStats() + swordStats * SWORD_MULTIPLIER);
		return this.getName() + " has learnt sword for " + swordStats * SWORD_MULTIPLIER + " units.";
	}
	
	public String learnMagic(int magicStats) throws InvalidValueException{
		setMagicStats(getMagicStats() + magicStats * MAGIC_MULTIPLIER);
		return this.getName() + " has learnt magic for " + magicStats * MAGIC_MULTIPLIER + " units.";
	}
	
	public String earnMoney(int money) throws InvalidValueException{
		setMoney(getMoney() + money * MONEY_MULTIPLIER);
		return this.getName() + " has earned " + money * MONEY_MULTIPLIER + " bahts.";
	}
}
