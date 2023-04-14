package player;

import customExecption.InvalidValueExecption;

public class SwordMan extends BasePlayer{
//	TODO: adjust the SWORD_MULTIPLIER to balance players
	private final int SWORD_MULTIPLIER = 2;
	private final int MAGIC_MULTIPLIER = 1;
	private final int MONEY_MULTIPLIER = 1;
	
	public SwordMan(String name) throws InvalidValueExecption{
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
