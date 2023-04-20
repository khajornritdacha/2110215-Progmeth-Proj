package monster;

import customException.InvalidValueException;

public class Wizzard extends BaseMonster {
	// TODO: change magic multiplier to make game balance (in percent)
	private final int MULTIPLIER = 5;
	
	public Wizzard(String name, int swordStats, int magicStats, int dropMoney) {
		super(name, swordStats, magicStats, dropMoney);
	}
	
	public void evolution(int swordStats, int magicStats) {
		try {
			this.setMagicStats(this.getMagicStats() + magicStats * MULTIPLIER / 100);
			this.setDropMoney(this.getDropMoney() + magicStats * MULTIPLIER / 100);
		}
		catch (InvalidValueException e) {
			System.out.println("Wizzard evolved failed by " + e);
		}
	}
}
