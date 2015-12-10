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
	 * The current depth position of this instance.
	 */
	@SuppressWarnings("unused")
	private double zPosition;

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

	/**
	 * The parallax factor of this instance. The xScrollSpeed and yScrollSpeed
	 * fields will be scaled by this factor. This field is final.
	 */
	private final double DEPTH_RATIO;

	public Entity(double xPosition, double yPosition, int zPosition, double depthRatio) {
		setXPosition(xPosition);
		setYPosition(yPosition);
		setZPosition(zPosition);

		this.DEPTH_RATIO = depthRatio;
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
		xScrollSpeed = (magnitude * DEPTH_RATIO);
	}

	/**
	 * Sets the vertical scroll speed to the specified magnitude.
	 * 
	 * @param magnitude
	 *            The magnitude to set the scroll speed to.
	 */

	public void setYScroll(double magnitude) {
		yScrollSpeed = (magnitude * DEPTH_RATIO);
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
	
	public int getScaled(int magnitude) {
		return magnitude * SCALE;
	}

	public double getScaled(double magnitude) {
		return magnitude * SCALE;
	}
}
