package player;

public abstract class BasePlayer {
	private int money;
	private int swordStats;
	private int magicStats;
	
//	TODO: change these starting number for game balance
	private int START_MONEY = 100;
	private int START_MAGICSTATS = 1;
	private int START_SWORDSTATS = 1;
	
	public BasePlayer() {
		this.setMoney(START_MONEY);
		this.setMagicStats(START_MAGICSTATS);
		this.setSwordStats(START_SWORDSTATS);
	}
	
	public abstract void learnSword(int swordStats);
	
	public abstract void learnMagic(int magicStats);
	
	public abstract void earnMoney(int money);
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		if (money < 0) money = 0;
		this.money = money;
	}

	public int getSwordStats() {
		return swordStats;
	}

	public void setSwordStats(int swordStats) {
		if (swordStats < 0) swordStats = 0;
		this.swordStats = swordStats;
	}

	public int getMagicStats() {
		return magicStats;
	}

	public void setMagicStats(int magicStats) {
		if (magicStats < 0) magicStats = 0;
		this.magicStats = magicStats;
	}	
}
