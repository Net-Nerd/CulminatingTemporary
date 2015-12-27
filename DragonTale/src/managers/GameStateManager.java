package managers;

import java.awt.Graphics2D;
import java.util.HashMap;

import exceptions.LoadException;
import gamestates.ErrorState;
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
	 * A hash map with keys of type String and values of type GameState. This
	 * map is used to store all the game states used within the game.
	 */
	private static HashMap<String, GameState> gameStates;
	/**
	 * The currently active state.
	 */
	private static GameState currentState;

	/**
	 * The previously active state.
	 */
	private static GameState previousState;

	/**
	 * The required state to switch to.
	 */
	private static GameState requiredState;

	/**
	 * This class cannot be instantiated.
	 */
	private GameStateManager() {
	}

	public static void initialize() {

	}

	/**
	 * Changes the current state to the specified state and unloads the previous
	 * state and all associated resources.
	 * 
	 * @param state
	 *            the state to switch to.
	 */

	public static void setState(GameState state) {
		// set the required state to the specified state.
		requiredState = state;

		// unload the current state if it not a null state.
		if (previousState != null) {
			unloadState();
		}
		try {
			// load the required state if it is not a null or error state.
			if (requiredState.getLevelPath() != null) {
				loadState();
			}

			// set the previous state to the current state.
			previousState = currentState;

			// set the current state to the required state.
			currentState = requiredState;
		} catch (LoadException e) {
			// switch to the error state if an error occurred during loading.
			setState(new ErrorState(null, e));
		}
	}

	/**
	 * Loads the current state and all associated resources.
	 * 
	 * @throws LoadException
	 */
	private static void loadState() throws LoadException {
		ResourceManager.loadResources(requiredState.getLevelPath());
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
		currentState.tick();
	}
	
	public static void handleInput() {
		currentState.handleInput();
	}
	
	public static void draw(Graphics2D g) {
		currentState.draw(g);
	}

	public static GameState getCurrentState() {
		return currentState;
	}
}
