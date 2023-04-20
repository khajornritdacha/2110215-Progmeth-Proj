package player;

import customExecption.InvalidValueExecption;

public abstract class BasePlayer {
	private int money;
	private int swordStats;
	private int magicStats;
	private String name;
	private boolean isAlive;
	
	//	TODO: change these starting number for game balance
	private int START_MONEY = 100;
	private int START_MAGICSTATS = 1;
	private int START_SWORDSTATS = 1;
	
	public BasePlayer(String name) throws InvalidValueExecption{
		this.setMoney(START_MONEY);
		this.setMagicStats(START_MAGICSTATS);
		this.setSwordStats(START_SWORDSTATS);
		this.setName(name);
		this.setAlive(true);			
	}
	
	public abstract String learnSword(int swordStats) throws InvalidValueExecption;
	
	public abstract String learnMagic(int magicStats) throws InvalidValueExecption;
	
	public abstract String earnMoney(int money) throws InvalidValueExecption;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.isEmpty()) name = "Untitled";
		this.name = name;
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
//		Player dies if money is less than or equal to 0 
		if (money <= 0) {
			this.setAlive(false);
			money = 0;
		}
		this.money = money;
	}

	public int getSwordStats() {
		return swordStats;
	}

	public void setSwordStats(int swordStats) throws InvalidValueExecption{
		if (swordStats <= 0) {
			throw new InvalidValueExecption("Invalid sword stats for player");
		}
		this.swordStats = swordStats;
	}

	public int getMagicStats() {
		return magicStats;
	}

	public void setMagicStats(int magicStats) throws InvalidValueExecption{
		if (magicStats <= 0) {
			throw new InvalidValueExecption("Invalid magic stats for player");
		}
		this.magicStats = magicStats;
	}	
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
