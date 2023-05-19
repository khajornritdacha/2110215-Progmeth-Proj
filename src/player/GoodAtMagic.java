package player;

/**
 * Good at magic trait, if players own this trait, their magic stats increase faster than others 
 */
public interface GoodAtMagic {
	// TODO: adjust the magic stats to make game balance
	/**
	 * Multiplier of magic stats
	 */
	int magicMultiplier = 2;
}
