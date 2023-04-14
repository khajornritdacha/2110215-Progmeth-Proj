package player;

import customExecption.InvalidValueExecption;

public class Farmer extends BasePlayer {
	private final int SWORD_MULTIPLIER = 1;
	private final int MAGIC_MULTIPLIER = 1;
	private final int MONEY_MULTIPLIER = 1;
	
	public Farmer(String name) throws InvalidValueExecption{
		super(name);
	}
	
	public void learnSword(int swordStats) throws InvalidValueExecption{
		setSwordStats(getSwordStats() + swordStats * SWORD_MULTIPLIER);
	}
	
	public void learnMagic(int magicStats) throws InvalidValueExecption{
		setMagicStats(getMagicStats() + magicStats * MAGIC_MULTIPLIER);
	}
	
	public void earnMoney(int money) throws InvalidValueExecption{
		setMoney(getMoney() + money * MONEY_MULTIPLIER);
	}
}
