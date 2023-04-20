package monster;

import customException.InvalidValueException;

public class Skeleton extends BaseMonster {
	// TODO: change sword multiplier to make game balance (in percent)
	private final int MULTIPLIER = 5;
	
	public Skeleton(String name, int swordStats, int magicStats, int dropMoney) {
		super(name, swordStats, magicStats, dropMoney);
	}
	
	public void evolution(int swordStats, int magicStats) {
		try {
			this.setSwordStats(this.getSwordStats() + swordStats * MULTIPLIER / 100);
			this.setDropMoney(this.getDropMoney() + swordStats * MULTIPLIER / 100);
		}
		catch (InvalidValueException e) {
			System.out.println("Skeleton evolved failed by " + e);
		}
	}
}
