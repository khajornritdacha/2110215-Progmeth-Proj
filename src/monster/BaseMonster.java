package monster;

import customException.InvalidValueException;
import javafx.scene.paint.Color;

public abstract class BaseMonster {
	private String name;
	private int swordStats;
	private int magicStats;
	private int dropMoney;

	public BaseMonster(String name, int swordStats, int magicStats, int dropMoney) {
		try {
			this.setName(name);
			this.setSwordStats(swordStats);
			this.setMagicStats(magicStats);
			this.setDropMoney(dropMoney);
		}
		catch (InvalidValueException err) {
			System.out.println(String.format("Error occured with monster %s: %s", name, err.getMessage()));
		}
	}
	
	public abstract Color getColor();
	
	public abstract int getLevel();
	
	public abstract void respawn();

	public int getDropMoney() {
		return dropMoney;
	}

	public void setDropMoney(int dropMoney) {
		if (dropMoney < 0) dropMoney = 0;
		this.dropMoney = dropMoney;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.isEmpty()) {
			name = this.getClass().getSimpleName();
		}
		this.name = name;
	}

	public int getSwordStats() {
		return swordStats;
	}

	public void setSwordStats(int swordStats) throws InvalidValueException {
		if (swordStats <= 0) {
			throw new InvalidValueException("Invalid sword stats for monster");
		}
		this.swordStats = swordStats;
	}

	public int getMagicStats() {
		return magicStats;
	}

	public void setMagicStats(int magicStats) throws InvalidValueException {
		if (magicStats <= 0) {
			throw new InvalidValueException("Invalid magic stats for monster");
		}
		this.magicStats = magicStats;
	}
}
