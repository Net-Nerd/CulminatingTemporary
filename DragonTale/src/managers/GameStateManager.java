package managers;

import gamestates.GameState;
import tools.EntityBuilder;

/**
 * 
 * @author Anddy Pena
 * @author Chandra Gummaluru
 * 
 * @version 0.0.1
 *
 */
public class GameStateManager {

	/**
	 * The currently active state.
	 */
	private static GameState currentState;

	/**
	 * The previously active state.
	 */
	private static GameState previousState;

	/**
	 * This class cannot be instantiated.
	 */
	private GameStateManager() {
	}

	/**
	 * Changes the current state to the specified state and unloads the previous
	 * state and all associated resources.
	 * 
	 * @param state
	 *            the state to switch to.
	 */
	public static void setState(GameState state) {
		if (previousState!=null) {
			unloadState();
		}
		previousState = currentState;
		currentState = state;
		loadState();
	}

	/**
	 * Loads the current state and all associated resources.
	 */
	private static void loadState() {
		ResourceManager.loadResources(currentState.getLevelPath());
		EntityBuilder.buildLevel();
	}

	/**
	 * Unloads the current state and all associated resources.
	 */
	private static void unloadState() {
		EntityBuilder.destoryEntities();
		ResourceManager.unloadResources();
	}

	public static void togglePause() {
		currentState.togglePause();
	}

	public static void tick() {
	}

	public static GameState getCurrentState() {
		return currentState;
	}
}
