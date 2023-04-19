package monster;

import customExecption.InvalidValueExecption;

public class BaseMonster {
	private String name;
	private int swordStats;
	private int magicStats;

	public BaseMonster(String name, int swordStats, int magicStats) {
		try {
			this.setName(name);
			this.setSwordStats(swordStats);
			this.setMagicStats(magicStats);
		} catch (InvalidValueExecption err) {
			System.out.println(String.format("Error occured with monster %s: %s", name, err.getMessage()));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.isEmpty())
			name = "Untitled";
		this.name = name;
	}

	public int getSwordStats() {
		return swordStats;
	}

	public void setSwordStats(int swordStats) throws InvalidValueExecption {
		if (swordStats <= 0) {
			throw new InvalidValueExecption("Invalid sword stats for monster");
		}
		this.swordStats = swordStats;
	}

	public int getMagicStats() {
		return magicStats;
	}

	public void setMagicStats(int magicStats) throws InvalidValueExecption {
		if (magicStats <= 0) {
			throw new InvalidValueExecption("Invalid magic stats for monster");
		}
		this.magicStats = magicStats;
	}
}
