package tools;

import java.awt.Graphics2D;

import entities.Background;
import entities.Terrain;

public class LevelPainter{

	public void drawLevel(Graphics2D g) {
		for (Background background : ResourceManager.getBackgrounds().values()) {
			background.scroll();
			background.draw(g);
		}
		
		for (Terrain element : ResourceManager.getElements().values()) {
			element.scroll();
			element.draw(g);
		}
	}
}
