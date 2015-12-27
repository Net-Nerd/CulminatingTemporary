package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import exceptions.LoadException;
import main.GamePanel;

/**
 * This state is loaded in when an error occurs within the game.
 * 
 * @author Chandra Gummaluru
 * @version 0.1
 */
public class ErrorState extends GameState {

	private final String[] ERROR_TEXT = { "An unexpected error occured.",
			"Close the game and refer to the manual for troubleshooting.", "Details:", "" };

	private final int[] lineSpacing = { 1, 1, 2, 1 };
	private final int[] fontSize = { 10, 10, 8, 8 };

	public ErrorState(String path, LoadException e) {
		super(path);

		this.ERROR_TEXT[3] = e.getMessage();
	}

	@Override
	public void tick() {
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);

		// g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);

		int panelCenterX = GamePanel.WIDTH * GamePanel.SCALE / 2;
		int defaultYPosition = 50 * GamePanel.SCALE;
		int textHeight = 10 * GamePanel.SCALE;
		int defaultGap = 5 * GamePanel.SCALE;

		int currentSpacing = 0;

		for (int i = 0; i < ERROR_TEXT.length; i++) {
			g.setFont(new Font("MS Sans Serif", Font.BOLD, fontSize[i] * GamePanel.SCALE));
			int textCenter = g.getFontMetrics().stringWidth(ERROR_TEXT[i]) / 2;
			currentSpacing += lineSpacing[i];
			g.drawString(ERROR_TEXT[i], panelCenterX - textCenter,
					defaultYPosition + defaultGap + textHeight * currentSpacing);
		}
	}

	@Override
	public void handleInput() {
	}

}
