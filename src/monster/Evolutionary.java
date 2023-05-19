package monster;

public interface Evolutionary {
	/**
	 * Evolve the monster by increasing sword and magic stats
	 * @param swordStats new sword stats
	 * @param magicStats new magic stats
	 */
	void evolve(int swordStats, int magicStats);
}
