package tools;

import java.awt.Graphics2D;

import entities.Background;
import entities.Terrain;
import entities.Text;

public class LevelPainter{

	private String[] backgroundOrder;
	
	public void updatePainter() {
		
	}
	public void drawLevel(Graphics2D g) {
		for (Background background : ResourceManager.getBackgrounds().values()) {
			background.scroll();
			background.draw(g);
		}
		
		for (Terrain element : ResourceManager.getElements().values()) {
			element.scroll();
			element.draw(g);
		}
		
		for (Text text : ResourceManager.getText().values()) {
			text.draw(g);
		}
	}
}
