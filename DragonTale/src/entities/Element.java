package entities;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Element extends Entity {

	/**
	 * The default image of this instance.
	 */
	private BufferedImage DEFAULT_IMAGE;

	/**
	 * The absolute horizontal velocity of this instance. This field is not
	 * related to xScrollSpeed.
	 */
	private double xVelocity;

	/**
	 * The absolute vertical velocity of this instance. This field is not
	 * related to yScrollSpeed.
	 */
	private double yVelocity;

	private double DEPTH_RATIO;

	public Element(double xPosition, double yPosition, int zPosition, double depthRatio, double xVelocity,
			double yVelocity, BufferedImage image) {
		super(xPosition, yPosition, zPosition);

		this.DEPTH_RATIO = depthRatio;

		this.DEFAULT_IMAGE = image;
		setWidth(DEFAULT_IMAGE.getWidth());
		setHeight(DEFAULT_IMAGE.getHeight());
		setXVelocity(xVelocity);
		setYVelocity(yVelocity);
	}

	public void setXVelocity(double magnitude) {
		xVelocity = magnitude;
		setXScroll(getXScrollSpeed() + xVelocity);
	}

	public void setYVelocity(double magnitude) {
		yVelocity = magnitude;
		setYScroll(getYScrollSpeed() + yVelocity);
	}

	/**
	 * Sets the horizontal scroll speed to the specified magnitude.
	 * 
	 * @param magnitude
	 *            The magnitude to set the scroll speed to.
	 */
	@Override
	public void setXScroll(double magnitude) {
		super.setXScroll(magnitude * DEPTH_RATIO);
	}

	/**
	 * Sets the vertical scroll speed to the specified magnitude.
	 * 
	 * @param magnitude
	 *            The magnitude to set the scroll speed to.
	 */

	@Override
	public void setYScroll(double magnitude) {
		super.setYScroll(magnitude * DEPTH_RATIO);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(DEFAULT_IMAGE, (int) getScaled(getXPosition()), (int) getScaled(getYPosition()),
				getScaled(getWidth()), getScaled(getHeight()), null);
	}

}
