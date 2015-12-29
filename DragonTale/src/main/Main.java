package main;

import java.awt.Color;

import javax.swing.JFrame;
import gamestates.LoadingState;
import gamestates.MenuState;
import managers.GameStateManager;
import misc.AspectRatio;

/**
 * 
 * @author Chandra Gummaluru
 * @version 1.1
 *
 */
public class Main {

	private static final int WINDOW_WIDTH = 320;

	private static final AspectRatio aspectRatio = new AspectRatio(5, 4);

	private static GamePanel gameWindow;
	
	private static GameLoop gameLoop;

	public static void main(String[] args) throws InterruptedException {
		initializeGame();
	}

	/**
	 * Initializes the game.
	 */
	public static void initializeGame() {
		GameStateManager.setState(new LoadingState("/level_resources/loading.res.txt"));
		gameWindow = new GamePanel(WINDOW_WIDTH, aspectRatio);
		gameLoop = new GameLoop();
		gameWindow.initialize();
		gameLoop.initialize();
	}

	public static GamePanel getGameWindow() {
		return gameWindow;
	}
}
