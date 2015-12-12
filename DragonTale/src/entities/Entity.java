package entities;

import java.awt.Graphics2D;

import main.GamePanel;

public abstract class Entity {

	/**
	 * The current horizontal position of this instance.
	 */
	private double xPosition;

	/**
	 * The current vertical position of this instance.
	 */
	private double yPosition;

	/**
	 * The current depth of this instance. This field is the relative position
	 * and not the actual position in the z dimension.
	 */
	private int zPosition;

	/**
	 * The width of this instance.
	 */
	private int width;

	/**
	 * The height of this instance.
	 */
	private int height;

	/**
	 * The horizontal velocity of this instance relative to the player. This
	 * field is defaulted to a value of 0 and is not initialized in the
	 * constructor.
	 */
	private double xScrollSpeed = 0;

	/**
	 * The vertical velocity of this instance relative to the player. This field
	 * is defaulted to a value of 0 and is not initialized in the constructor.
	 */
	private double yScrollSpeed = 0;

	/**
	 * The constant at which to scale this instance. This field is final. This
	 * field is static.
	 */
	public static final int SCALE = GamePanel.SCALE;

	public Entity(double xPosition, double yPosition, int zPosition) {
		setXPosition(xPosition);
		setYPosition(yPosition);
		setZPosition(zPosition);
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Sets the horizontal scroll speed to the specified magnitude.
	 * 
	 * @param magnitude
	 *            The magnitude to set the scroll speed to.
	 */
	public void setXScroll(double magnitude) {
		xScrollSpeed = magnitude;
	}

	/**
	 * Sets the vertical scroll speed to the specified magnitude.
	 * 
	 * @param magnitude
	 *            The magnitude to set the scroll speed to.
	 */

	public void setYScroll(double magnitude) {
		yScrollSpeed = magnitude;
	}

	/**
	 * Sets the horizontal position of this instance.
	 * 
	 * @param xPosition
	 *            The coordinate to set the position to.
	 */
	public void setXPosition(double xPosition) {
		this.xPosition = xPosition;
	}

	/**
	 * Sets the vertical position of this instance.
	 * 
	 * @param yPosition
	 *            The coordinate to set the position to.
	 */
	public void setYPosition(double yPosition) {
		this.yPosition = yPosition;
	}

	public void setZPosition(int zPosition) {
		this.zPosition = zPosition;
	}

	/**
	 * Draws this instance to the screen.
	 * 
	 * @param g
	 *            The graphics object.
	 */

	public abstract void draw(Graphics2D g);

	/**
	 * Scrolls this instance according to the scroll speeds.
	 */
	public void scroll() {
		xPosition += xScrollSpeed;
		yPosition += yScrollSpeed;

		if (this.xPosition > GamePanel.WIDTH) {
			this.xPosition -= (GamePanel.WIDTH + this.width);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getXScrollSpeed() {
		return xScrollSpeed;
	}

	public double getYScrollSpeed() {
		return yScrollSpeed;
	}

	/**
	 * Gets the current horizontal position of this instance.
	 * 
	 * @return The current horizontal position.
	 */
	public double getXPosition() {
		return xPosition;
	}

	/**
	 * Gets the current vertical position of this instance.
	 * 
	 * @return The current vertical position.
	 */
	public double getYPosition() {
		return yPosition;
	}

	public int getZPosition() {
		return zPosition;
	}

	public int getScaled(int magnitude) {
		return magnitude * SCALE;
	}

	public double getScaled(double magnitude) {
		return magnitude * SCALE;
	}
}
