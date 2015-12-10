package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Text extends Entity {

	private Font font;
	private String text;
	
	public Text(double xPosition, double yPosition, int zPosition, double depthRatio, String text, Font font) {
		super(xPosition, yPosition, zPosition, depthRatio);
		this.text = text;
		this.font = font;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("Arial", 50, Font.BOLD));
		g.setColor(Color.BLACK);
		g.drawString(text, (int) getScaled(getXPosition()), (int) getScaled(getYPosition()));
	}

}
