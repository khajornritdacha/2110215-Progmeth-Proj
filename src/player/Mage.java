package player;

import customExecption.InvalidValueExecption;

public class Mage extends BasePlayer{
//	TODO: adjust the MAGIC_MULTIPLIER to balance players
	private final int SWORD_MULTIPLIER = 1;
	private final int MAGIC_MULTIPLIER = 2;
	private final int MONEY_MULTIPLIER = 1;
	
	public Mage(String name) throws InvalidValueExecption{
		super(name);
	}
	
	public String learnSword(int swordStats) throws InvalidValueExecption{
		setSwordStats(getSwordStats() + swordStats * SWORD_MULTIPLIER);
		return this.getName() + " has learnt sword for " + swordStats * swordStats * SWORD_MULTIPLIER + " units.";
	}
	
	public String learnMagic(int magicStats) throws InvalidValueExecption{
		setMagicStats(getMagicStats() + magicStats * MAGIC_MULTIPLIER);
		return this.getName() + " has learnt magic for " + magicStats * MAGIC_MULTIPLIER + " units.";
	}
	
	public String earnMoney(int money) throws InvalidValueExecption{
		setMoney(getMoney() + money * MONEY_MULTIPLIER);
		return this.getName() + " has earned " + money * MONEY_MULTIPLIER + " bahts.";
	}
}
