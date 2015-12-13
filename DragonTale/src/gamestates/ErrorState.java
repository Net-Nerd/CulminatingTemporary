package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class ErrorState extends GameState {

	public ErrorState() {
		super(null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.setColor(Color.WHITE);
		g.drawString("Error", 50, 50);

	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}

}
