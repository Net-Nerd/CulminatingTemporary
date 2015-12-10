package gamestates;

import java.awt.Graphics2D;

import tools.AudioManager;
import tools.LevelPainter;
import tools.ResourceManager;

public class MenuState extends GameState {

	private final String LEVEL_PATH;
	private LevelPainter painter;
	
	public MenuState(String path) {		
		this.LEVEL_PATH = path;
		ResourceManager.loadAllData(LEVEL_PATH);
		this.painter = new LevelPainter();
		AudioManager.play(ResourceManager.getMusic().get("THEME"));
	}

	@Override
	public void initalize() {

	}

	@Override
	public void tick() {

	}

	@Override
	public void draw(Graphics2D g) {
		painter.drawLevel(g);
	}

	@Override
	public void handleInput() {
	   	    
	}
}
