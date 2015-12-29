package gamestates;

import java.awt.Graphics2D;

import tools.LevelPainter;

public class LoadingState extends GameState {

	public LoadingState(String path) {
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
