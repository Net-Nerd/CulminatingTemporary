package gamestates;

import java.awt.Graphics2D;

public abstract class GameState {

	private final String LEVEL_PATH;
	private boolean paused;

	public GameState(String path) {
		this.LEVEL_PATH = path;
		initialize();
	}

	public void initialize() {
		
	}

	/**
	 * Update the GameState
	 */
	public abstract void tick();

	/**
	 * Draws this state onto the screen.
	 * 
	 * @param g
	 *            Graphics2D object that will be used for drawing to the screen
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * Each GameState could handle input differently
	 */
	public abstract void handleInput();

	public String getLevelPath() {
		return LEVEL_PATH;
	}

	public boolean isPaused() {
		return paused;
	}

	public void togglePause() {
		paused = !paused;
	}
}
