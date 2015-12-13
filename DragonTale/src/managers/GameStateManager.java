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

	private enum StateType {
		MENU(0), LEVEL(1), MINIGAME(2), LOADING(3), ERROR(4);

		private int state;

		StateType(int state) {
			this.state = state;
		}
	}

	private boolean isPaused = false;
	private GameState currentState;
	private GameState previousState;

	public GameStateManager() {
		this.currentState
	}

	private void loadState() {
		ResourceManager.loadResources(currentState.getLevelPath());
		EntityBuilder.buildLevel();
	}

	private void unloadState() {
		EntityBuilder.destoryEntities();
		ResourceManager.unloadResources();
	}

	public void setState(int state) {

	}

	public void pause(boolean p) {
		isPaused = p;
	}

	public void tick() {
		if (isPaused) {
			return;
		}
	}
}
