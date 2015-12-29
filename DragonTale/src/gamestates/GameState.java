package gamestates;

import java.awt.Graphics2D;

/**
 * An abstract class to create states.
 * 
 * @author Chandra Gummaluru
 * @version 0.1
 *
 */
public abstract class GameState {

	private final String LEVEL_PATH;
	private boolean paused;

	public GameState(String path) {
		this.LEVEL_PATH = path;
		initialize();
	}

	/**
	 * Initialize the state.
	 */
	public abstract void initialize();

	/**
	 * Updates the state.
	 */
	public abstract void tick();

	/**
	 * Draws this state onto the screen.
	 * 
	 * @param g
	 *            Graphics2D object that will be used for drawing to the screen.
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * Handle the input.
	 */
	public abstract void handleInput();

	/**
	 * Gets the level path of the state.
	 * 
	 * @return The level path.
	 */
	public String getLevelPath() {
		return LEVEL_PATH;
	}

	/**
	 * Returns true if the current state is paused, false otherwise.
	 * 
	 * @return the current state of the state. //lol what? TODO: Fix that return
	 *         statement above.F
	 */
	public boolean isPaused() {
		return paused;
	}

	public void togglePause() {
		paused = !paused;
	}
}
