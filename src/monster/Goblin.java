package monster;

public class Goblin extends BaseMonster {	
	public Goblin(String name, int swordStats, int magicStats, int dropMoney) {
		super(name, swordStats, magicStats, dropMoney);
	}
	
	public void evolution(int swordStats, int magicStats) {
		return;
	}
}
