package gamestates;

import java.awt.Graphics2D;

public abstract class GameState {

	private final String LEVEL_PATH;

	public GameState(String path) {
		this.LEVEL_PATH = path;
	}

	public void initalize() {
		
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
}
