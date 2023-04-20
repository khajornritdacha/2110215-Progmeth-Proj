package player;

import customExecption.InvalidValueExecption;

public class TheRich extends BasePlayer{
//	TODO: adjust the MONEY_MULTIPLIER to balance players
	private final int SWORD_MULTIPLIER = 1;
	private final int MAGIC_MULTIPLIER = 1;
	private final int MONEY_MULTIPLIER = 2;
	
	public TheRich(String name) throws InvalidValueExecption{
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
