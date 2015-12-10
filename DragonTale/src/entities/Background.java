package entities;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Background extends Entity {
	
	/**
	 * The default image of this instance.
	 */
	private BufferedImage DEFAULT_IMAGE;
	
	public Background(double xPosition, double yPosition, int zPosition, double depthRatio, BufferedImage image) {
		super(xPosition, yPosition, zPosition, depthRatio);
		
		this.DEFAULT_IMAGE = image;
		setWidth(DEFAULT_IMAGE.getWidth());
		setHeight(DEFAULT_IMAGE.getHeight());
	
	}

	@Override
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(DEFAULT_IMAGE, (int) getScaled(getXPosition()), (int) getScaled(getYPosition()), getScaled(getWidth()),
				getScaled(getHeight()), null);
	}
	
	
}
