package entities;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Background extends Entity {

	/**
	 * The default image of this instance.
	 */
	private BufferedImage DEFAULT_IMAGE;

	/**
	 * The parallax factor of this instance. The xScrollSpeed and yScrollSpeed
	 * fields will be scaled by this factor. This field is final.
	 */
	private final double DEPTH_RATIO;

	public Background(double xPosition, double yPosition, int zPosition, double depthRatio, BufferedImage image) {
		super(xPosition, yPosition, zPosition);

		this.DEPTH_RATIO = depthRatio;
		this.DEFAULT_IMAGE = image;
		setWidth(DEFAULT_IMAGE.getWidth());
		setHeight(DEFAULT_IMAGE.getHeight());
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
