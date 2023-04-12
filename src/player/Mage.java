package player;

public class Mage extends BasePlayer{
//	TODO: adjust the MAGIC_MULTIPLIER to balance players
	private final int SWORD_MULTIPLIER = 1;
	private final int MAGIC_MULTIPLIER = 2;
	private final int MONEY_MULTIPLIER = 1;
	
	public Mage() {
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
