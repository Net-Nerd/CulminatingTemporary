package tools;

import java.awt.Graphics2D;
import java.util.ArrayList;

import entities.Background;
import entities.Element;
import entities.Text;

public class LevelPainter{

	private LevelPainter() {
		
	}
		
	public void updatePainter() {
		
	}
	
	public static void drawLevel(Graphics2D g) {
		for (Background background : EntityBuilder.getBackgrounds().values()) {
			background.scroll();
			background.draw(g);
		}
		
		for (Element element : EntityBuilder.getElements().values()) {
			element.scroll();
			element.draw(g);
		}
	}
}
