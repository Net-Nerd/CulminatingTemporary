package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import managers.GameStateManager;

/**
 * A custom JPanel class that is used to create the main Panel within the game.
 * 
 * @author Chandra Gummaluru
 * @version 2.0
 *
 */
public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	/**
	 * The width of the panel.
	 */
	public static final int WIDTH = 320;

	/**
	 * The height of the panel. The height is calculated to ensure a 5:4 aspect
	 * ratio.
	 */
	public static final int HEIGHT = WIDTH / 5 * 4;

	/**
	 * The factor by which to scale the resolution of the game. By default, the
	 * resolution is 320 by 256.
	 */
	public static final int SCALE = getPreferredScale();

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

	private Color defaultBackground = Color.BLACK;

	// Temporary Variables
	DecimalFormat df = new DecimalFormat("0.00");
	private double currentFPS = 0.00;

	/**
	 * TODO Add documentation.
	 */
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setBackground(defaultBackground);
		setFocusable(true);
		requestFocus();

		thread = new Thread(this);
		thread.start();
	}

	private void initializeComponents() {
		isRunning = true;
	}

	/**
	 * This method is called upon the starting the thread.
	 */
	public void run() {
		initializeComponents();

		// The current frame count.
		int frames = 0;

		// The time (in nanoseconds) from the last update.
		long previousUpdate = 0;

		// This loop executes as long as the game is running.
		while (isRunning) {
			// the point in time (in nanoseconds) in which an iteration of the
			// loop is started.
			long startTime;

			// the point in time (in nanoseconds) in which the same iteration of
			// the loop is finished.
			long endTime;

			// the elapsed time (in milliseconds) between the start and end the
			// iteration.
			long elapsedTime;

			// the time (in milliseconds) to wait, until the next iteration of
			// the loop.
			long waitTime;

			if (frames > 29) {
				// Calculate the amount of time (in seconds) it took, for the
				// target frames to display.
				currentFPS = 30 / ((System.nanoTime() - previousUpdate) / 1000000000.0);

				// Reset the frame count.
				frames = 0;
				previousUpdate = System.nanoTime();
			} else {
				frames++;
			}
			startTime = System.nanoTime();

			repaint();
			GameStateManager.tick();
			GameStateManager.handleInput();

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

	/**
	 * Draws all components to the panel.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		GameStateManager.draw((Graphics2D) g);

		// Display FPS for debugging
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 10 * SCALE));
		g.drawString("FPS: " + df.format(currentFPS) + "", 2 * SCALE, 10 * SCALE);
		g.drawString("0.1a", 295 * SCALE, 10 * SCALE);
	}

	/**
	 * Calculates the maximum scale factor that can be applied to the base
	 * resolution while still ensuring the entire window is visible on screen.
	 * 
	 * @return the preferred scale
	 */
	public static int getPreferredScale() {

		// Gets the screen width based on the maximum window bounds.
		int screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;

		// Gets the screen height based on the maximum window bounds.
		int screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;

		// The preferred scale for the window.
		int preferredScale = 1;

		// This loop continues until the next possible scale factor becomes too
		// large.
		while (WIDTH * (preferredScale + 1) < screenWidth && HEIGHT * (preferredScale + 1) < screenHeight) {
			// Increment the scale.
			preferredScale++;
		}

		// Return the maximum scale factor.
		return preferredScale;
	}
}
