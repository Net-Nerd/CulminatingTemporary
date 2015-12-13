package tools;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.sound.sampled.Clip;

import entities.Background;
import entities.Element;
import managers.ResourceManager;

/**
 * This class builds and stores the required entities from the specified level
 * data.
 * 
 * @author Chandra Gummaluru
 * @version 0.0.1
 *
 */
public class EntityBuilder {

	private enum EntityType {
		BACKGROUND, ELEMENT;
	}

	private static ArrayList<String> levelData;

	private static final String TYPE_DELIMITER = ":";
	private static final String PROPERTY_DELIMITER = ";";

	/**
	 * A hash map with keys of type String and values of type Clip. This map is
	 * used to store backgrounds for a level.
	 */
	private static LinkedHashMap<String, Background> backgrounds = new LinkedHashMap<String, Background>();

	/**
	 * A hash map with keys of type String and values of type Element. This map
	 * is used to store elements for a level.
	 */
	private static LinkedHashMap<String, Element> elements = new LinkedHashMap<String, Element>();

	/**
	 * This class cannot be instantiated.
	 */
	private EntityBuilder() {
	}

	public static void buildLevel() {
		levelData = ResourceManager.getText().get("LEVEL_DATA");

		for (int i = 0; i < levelData.size(); i++) {
			String[] properties = levelData.get(i).split(TYPE_DELIMITER);

			EntityType type = EntityType.valueOf(properties[0]);
			String key = properties[1];

			switch (type) {
			case BACKGROUND:
				backgrounds.put(key, buildBackground(properties[2]));
				break;
			case ELEMENT:
				elements.put(key, buildElement(properties[2]));
				break;
			}
		}
	}

	private static Background buildBackground(String buildData) {
		String[] properties = buildData.split(PROPERTY_DELIMITER);

		double xPosition = Double.parseDouble(properties[0]);
		double yPosition = Double.parseDouble(properties[1]);
		int zPosition = Integer.parseInt(properties[2]);

		double depthRatio = Double.parseDouble(properties[3]);

		BufferedImage image = ResourceManager.getImages().get(properties[4]);

		return new Background(xPosition, yPosition, zPosition, depthRatio, image);

	}

	private static Element buildElement(String buildData) {
		String[] properties = buildData.split(PROPERTY_DELIMITER);

		double xPosition = Double.parseDouble(properties[0]);
		double yPosition = Double.parseDouble(properties[1]);
		int zPosition = Integer.parseInt(properties[2]);

		double depthRatio = Double.parseDouble(properties[3]);

		double xVelocity = Double.parseDouble(properties[4]);
		double yVelocity = Double.parseDouble(properties[5]);

		BufferedImage image = ResourceManager.getImages().get(properties[6]);

		return new Element(xPosition, yPosition, zPosition, depthRatio, xVelocity, yVelocity, image);

	}

	public static LinkedHashMap<String, Background> getBackgrounds() {
		return backgrounds;
	}

	public static LinkedHashMap<String, Element> getElements() {
		return elements;
	}

	public static void destoryEntities() {
		backgrounds.clear();
		elements.clear();
	}

}
