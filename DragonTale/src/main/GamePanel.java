package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import managers.GameStateManager;
import misc.AspectRatio;

/**
 * Implements fields and methods for a custom JPanel with a specified aspect
 * ratio and scale factor.
 * 
 * @author Chandra Gummaluru
 * @version 2.2
 *
 */
public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * The frame of the window.
	 */
	private JFrame frame;

	/**
	 * The factor by which to scale the resolution of the game.
	 */
	private final int SCALE;

	/**
	 * The aspect ratio of the panel.
	 */
	private final AspectRatio ASPECT_RATIO;

	/**
	 * The default color.
	 */
	private Color defaultColor = Color.BLACK;

	/**
	 * Creates a new GamePanel using a specified width, aspect ratio, and scale
	 * factor.
	 * 
	 * @param width
	 *            The width of the panel.
	 * @param scale
	 *            The factor by which to scale the resolution of the game.
	 */
	public GamePanel(int width, AspectRatio aspectRatio) {
		super();

		// set the required aspect ratio
		this.ASPECT_RATIO = aspectRatio;

		// set the minimum size of this panel.
		this.setPreferredSize(new Dimension(width, ASPECT_RATIO.calculateHeight(width)));

		// initialize the frame using this panel as the content pane.
		this.frame = new JFrame();

		// set the content pane of the frame to this panel.
		frame.setContentPane(this);
		frame.pack();

		// set the scale to the preferred scale.
		this.SCALE = getPreferredScale();
	}

	/**
	 * Initializes the game window and makes it visible.
	 */
	public void initialize() {

		// Re-scale the panel based on the calculated scale factor.
		this.setPreferredSize(new Dimension(this.getWidth() * SCALE, this.getHeight() * SCALE));

		// Set the frame properties.
		frame.setTitle("");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Pack the frame.
		frame.pack();

		setBackground(defaultColor);
		setFocusable(true);
		requestFocus();

		this.setVisible(true);
		frame.setVisible(true);
	}

	/**
	 * Draws all components to the panel.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		GameStateManager.draw((Graphics2D) g);
	}

	/**
	 * Calculates the maximum scale factor that can be applied to the base
	 * resolution while still ensuring the entire window is visible on screen.
	 * 
	 * @return the preferred scale
	 */
	public int getPreferredScale() {

		// Get the width of the frame border by taking the difference of
		// the frame width and panel width.
		int borderWidth = frame.getWidth() - this.getWidth();

		// Get the height of the frame border by taking the difference of
		// the frame height and the panel height.
		int borderHeight = frame.getHeight() - this.getHeight();

		// Get the maximum width of the screen that can be used for display.
		int screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;

		// Get the maximum height of the screen that can be used for display.
		int screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;

		// The preferred scale for the window.
		int preferredScale = 1;

		// This loop continues until the next possible scale factor becomes too
		// large.
		while (this.getWidth() * (preferredScale + 1) < screenWidth - borderWidth
				&& this.getHeight() * (preferredScale + 1) < screenHeight - borderHeight) {
			// Increment the scale.
			preferredScale++;
		}

		// Return the maximum scale factor.
		return preferredScale;
	}

	public int getScale() {
		return this.SCALE;
	}
}
