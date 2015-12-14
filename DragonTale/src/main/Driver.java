package main;

import javax.swing.JFrame;
import gamestates.LoadingState;
import gamestates.MenuState;
import managers.GameStateManager;

public class Driver {

	public static void main(String[] args) {
		loadLoadingState();
		initializeGame();
		loadMenuState();
	}

	public static void initializeGame() {
		JFrame window = new JFrame("Dragon Tale");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

	public static void loadMenuState() {
		GameStateManager.setState(new MenuState("/level_resources/menu.res.txt"));
	}

	public static void loadLoadingState() {
		GameStateManager.setState(new LoadingState("/level_resources/loading.res.txt"));
	}
}
