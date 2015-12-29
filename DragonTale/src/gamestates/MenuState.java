package gamestates;

import java.awt.Graphics2D;
import tools.LevelPainter;

public class MenuState extends GameState {
	public MenuState(String path) {
		super(path);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void tick() {
	}

	@Override
	public void draw(Graphics2D g) {
		LevelPainter.drawLevel(g);
	}

	@Override
	public void handleInput() {
	}
}
