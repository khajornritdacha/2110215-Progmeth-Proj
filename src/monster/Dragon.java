package monster;

import customException.InvalidValueException;

public class Dragon extends BaseMonster {
	// TODO: change sword multiplier to make game balance (in percent)
	private final int SWORD_MULTIPLIER = 10;
	private final int MAGIC_MULTIPLIER = 10;
	
	public Dragon(String name, int swordStats, int magicStats, int dropMoney) {
		super(name, swordStats, magicStats, dropMoney);
	}
	
	public void evolution(int swordStats, int magicStats) {
		try {
			this.setSwordStats(this.getSwordStats() + swordStats * SWORD_MULTIPLIER / 100);
			this.setMagicStats(this.getMagicStats() + magicStats * MAGIC_MULTIPLIER / 100);
			this.setDropMoney(this.getDropMoney() + (swordStats + magicStats) * (SWORD_MULTIPLIER + MAGIC_MULTIPLIER) / 400);
		}
		catch (InvalidValueException e) {
			System.out.println("Dragon evolved failed by " + e);
		}
	}
}
