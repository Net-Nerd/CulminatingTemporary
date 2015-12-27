package main;

import java.awt.Color;

import javax.swing.JFrame;
import gamestates.LoadingState;
import gamestates.MenuState;
import managers.GameStateManager;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		initializeGame();
		loadLoadingState();
		loadMenuState();
	}

	public static void initializeGame() {
		JFrame window = new JFrame("Dragon Tale - Alpha 0.1");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

	public static void loadMenuState() {
		GameStateManager.setState(new MenuState("/level_resources/menu.res.txt"));
	}

	public static void loadLoadingState() throws InterruptedException {
		GameStateManager.setState(new LoadingState("/level_resources/loading.res.txt"));
	}
}
