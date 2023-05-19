package player;

/**
 * Good at sword trait, if players own this trait, their sword stats increase faster than others 
 */
public interface GoodAtSword {
	// TODO: adjust the sword stats to make game balance
	/**
	 * Multiplier of sword stats
	 */
	int swordMultiplier = 2;
}
