package utility;

/**
 * Possible game state
 */
public enum GameState {
	/**
	 * Game is in welcome scene
	 */
	WELCOME_SCENE,

	/**
	 * Game is in getting player names scene
	 */
	INPUT_PLAYER_SCENE,
	
	/**
	 * Game is in choosing number of turns to play scene
	 */
	SELECT_TURN_SCENE,
	
	/**
	 * Gameplay scene
	 */
	PLAYING_SCENE,
	
	/**
	 * Game is in How To Play scene
	 */
	HOW_TO_PLAY_SCENE,
	LOADING_SCENE,
}
