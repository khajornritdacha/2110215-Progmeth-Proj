package player;

public class Farmer extends BasePlayer {
	private final int SWORD_MULTIPLIER = 1;
	private final int MAGIC_MULTIPLIER = 1;
	private final int MONEY_MULTIPLIER = 1;
	
	public Farmer() {
		super();
	}
	
	public void learnSword(int swordStats) {
		setSwordStats(getSwordStats() + swordStats * SWORD_MULTIPLIER);
	}
	
	public void learnMagic(int magicStats) {
		setMagicStats(getMagicStats() + magicStats * MAGIC_MULTIPLIER);
	}
	
	public void earnMoney(int money) {
		setMoney(getMoney() + money * MONEY_MULTIPLIER);
	}
}
