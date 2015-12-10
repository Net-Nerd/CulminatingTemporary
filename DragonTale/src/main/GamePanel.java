package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import gamestates.MenuState;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	/**
	 * The width of the panel.
	 */
	public static final int WIDTH = 320;

	/**
	 * The height of the panel. The height is calculated to ensure a 4:3 aspect
	 * ratio.
	 */
	public static final int HEIGHT = WIDTH / 4 * 3;

	/**
	 * The factor by which to scale the resolution of the game. By default, the
	 * resolution is 320 by 240.
	 */
	public static final int SCALE = 4;

	private Thread thread;
	private boolean isRunning;

	/**
	 * The default refresh rate in number of frames per second.
	 */
	private int FPS = 30;

	/**
	 * The target time in milliseconds for each frame.
	 */
	private long targetTime = 1000 / FPS;

	// FIXME: This is temporary.
	MenuState mainMenu = new MenuState("/levelData/MENU_RESOURCES.txt");

	// Temporary Variables
	DecimalFormat df = new DecimalFormat("0.00");
	private double currentFPS = 0.00;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		setFocusable(true);
		requestFocus();

		thread = new Thread(this);
		thread.start();
	}

	private void initializeComponents() {
		isRunning = true;
	}

	public void run() {
		initializeComponents();

		int frames = 0;
		long previousUpdate = 0;
		while (isRunning) {
			// the point in time (in nanoseconds) in which an iteration of the
			// loop
			// is started.
			long startTime;

			// the point in time (in nanoseconds) in which the same iteration of
			// the
			// loop is finished.
			long endTime;

			// the elapsed time (in milliseconds) between the start and end the
			// iteration.
			long elapsedTime;

			// the time (in milliseconds) to wait, until the next iteration of
			// the
			// loop.
			long waitTime;

			if (frames > 29) {
				currentFPS = 30 / ((System.nanoTime() - previousUpdate) / 1000000000.0);
				frames = 0;
				previousUpdate = System.nanoTime();
			} else {
				frames++;
			}
			startTime = System.nanoTime();

			repaint();

			endTime = System.nanoTime();

			elapsedTime = (int) ((endTime - startTime) / 1000000);
			
			waitTime = (int) (targetTime - elapsedTime);

			try {
				if (waitTime > 0) {
					Thread.sleep(waitTime);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		mainMenu.draw((Graphics2D) g);

		// Display FPS for debugging
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("FPS: " + df.format(currentFPS) + "", 5, 27);
	}
}
