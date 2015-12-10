/**
 * LICENSE header
 */
package tools;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import entities.Background;
import entities.Terrain;
import entities.Text;

/**
 * This class handles the loading and saving of various resources required for
 * the game.
 */
public class ResourceManager {

	/**
	 * The various types of resources the manager can load. TODO: Add the other
	 * resource types
	 */
	public enum ResourceType {
		BACKGROUND, ELEMENT, MUSIC, TEXT;
	};

	// Image resources

	/**
	 * The various types of images.
	 */
	public enum ImageType {
		SINGLE, SHEET;
	}

	/**
	 * A hash map with keys of type String and values of type BufferedImage.
	 * This map is used to store the background images binded by the name.
	 */
	private static LinkedHashMap<String, Background> backgrounds = new LinkedHashMap<String, Background>();

	/**
	 * A hash map with keys of type String and values of type BufferedImage.
	 * This map is used to store the terrain images binded by the name.
	 */
	private static LinkedHashMap<String, Terrain> terrain = new LinkedHashMap<String, Terrain>();

	/**
	 * A hash map with keys of type String and values of type BufferedImage[].
	 * This map is used to store the sprite sheet images binded by the sprite
	 * name.
	 */
	@SuppressWarnings("unused")
	private static HashMap<String, BufferedImage[]> spritesheets = new HashMap<String, BufferedImage[]>();

	/**
	 * A hash map with keys of type String and values of type BufferedImage[].
	 * This map is used to store the tile set images binded by the sprite name.
	 */
	@SuppressWarnings("unused")
	private static HashMap<String, BufferedImage[]> tilesets = new HashMap<String, BufferedImage[]>();

	// Text resources

	/**
	 * A hash map with keys of type String and values of type String[]. This map
	 * is used to store dialog binded by the character name.
	 */
	@SuppressWarnings("unused")
	private static HashMap<String, String[]> dialog = new HashMap<String, String[]>();

	/**
	 * A hash map with keys of type Integer and values of type String. This map
	 * is used to store text binded by the event number.
	 */
	private static LinkedHashMap<String, Text> text = new LinkedHashMap<String, Text>();

	/**
	 * A hash map with keys of type String and values of type Clip. This map is
	 * used to store music files binded by track name.
	 */
	private static HashMap<String, Clip> music = new HashMap<String, Clip>();

	/**
	 * A hash map with keys of type String and values of type Clip. This map is
	 * used to store sound files binded by name.
	 */
	@SuppressWarnings("unused")
	private static HashMap<String, Clip> sounds = new HashMap<String, Clip>();

	/**
	 * A hash map with keys of type String and values of type Clip. This map is
	 * used to store sound files binded by name.
	 * 
	 * The explicit separation between this hash map and the <i>sound</i> hash
	 * map is for clarity only.
	 */
	@SuppressWarnings("unused")
	private static HashMap<String, Clip> narrations = new HashMap<String, Clip>();

	/**
	 * The delimiter constant for the
	 */
	private static final String TYPE_DELIMITER = ":";
	@SuppressWarnings("unused")
	private static final String PROPERTY_DELIMITER = ";";
	@SuppressWarnings("unused")
	private static final String SUBPROPERTY_DELIMITER = ",";

	/**
	 * The loaded resource file.
	 */
	private static File resourceFile;

	/**
	 * The array to temporarily store the level data until parsing.
	 */
	private static ArrayList<String> fileData;

	/**
	 * This class cannot be instantiated.
	 */
	private ResourceManager() {
	}

	private static File loadFile(String path) {
		return new File(ResourceManager.class.getResource(path).getFile());
	}

	/**
	 * Loads and stores the specified file into a File object.
	 * 
	 * @param path
	 *            The path of the file to load.
	 */
	public static void loadResourceFile(String path) {
		// Create a File from the specified path
		resourceFile = loadFile(path);
	}

	public static void loadAllData(String path) {
		loadResourceFile(path);
		readData();

		for (int i = 0; i < fileData.size(); i++) {
			String[] resourceData = fileData.get(i).split(TYPE_DELIMITER);
			ResourceType resourceType = ResourceType.valueOf(resourceData[0]);

			switch (resourceType) {
			case BACKGROUND:
				loadBackground(resourceData[1], resourceData[2]);
				break;
			case ELEMENT:
				loadElement(resourceData[1], resourceData[2]);
				break;
			case MUSIC:
				loadMusic(resourceData[1], resourceData[2]);
				break;
			case TEXT:
				loadText(resourceData[1], resourceData[2]);
				break;
			// This code should never execute if the data file is properly
			// formatted.
			default:
				try {
					throw new Exception();
				} catch (Exception e) {
					// TODO: Switch to game error state.
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * Reads and stores the data from the loaded file.
	 */
	public static void readData() {
		try {
			// Create a new BufferedReader with a FileReader for the current
			// file.
			BufferedReader fileReader = new BufferedReader(new FileReader(resourceFile));
			fileData = new ArrayList<String>();

			String currentLine;

			// This loop continues until there are no mores lines to read.
			while ((currentLine = fileReader.readLine()) != null) {
				fileData.add(currentLine);
			}

			// Close the file reader.
			fileReader.close();

		} catch (FileNotFoundException e) {
			// TODO: Switch to game error state.
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: Switch to game error state.
			e.printStackTrace();
		}
	}

	public static void loadBackground(String key, String data) {
		String[] resourceData = data.split(";");

		int xPosition = loadInteger(resourceData[0]);
		int yPosition = loadInteger(resourceData[1]);
		int zPosition = loadInteger(resourceData[2]);
		double depthRatio = loadDouble(resourceData[3]);

		BufferedImage image = loadImage(resourceData[4]);

		backgrounds.put(key, new Background(xPosition, yPosition, zPosition, depthRatio, image));
	}

	public static void loadElement(String key, String data) {
		String[] resourceData = data.split(";");

		int xPosition = loadInteger(resourceData[0]);
		int yPosition = loadInteger(resourceData[1]);
		int zPosition = loadInteger(resourceData[2]);
		double depthRatio = loadDouble(resourceData[3]);

		double xVelocity = loadDouble(resourceData[4]);
		double yVelocity = loadDouble(resourceData[5]);
		BufferedImage image = loadImage(resourceData[6]);
		terrain.put(key, new Terrain(xPosition, yPosition, zPosition, depthRatio, xVelocity, yVelocity, image));
	}
	
	public static void loadMusic(String key, String data) {
		String[] resourceData = data.split(";");
		
		Clip track = loadAudio(resourceData[0]);

		music.put(key, track);
	}
	
	public static void loadText(String key, String data) {
		String[] resourceData = data.split(";");
		
		int xPosition = loadInteger(resourceData[0]);
		int yPosition = loadInteger(resourceData[1]);
		int zPosition = loadInteger(resourceData[2]);
		double depthRatio = loadDouble(resourceData[3]);
		
		String string = resourceData[4];
		//Font font = loadFont(resourceData[5]);
		
		text.put(key, new Text(xPosition, yPosition, zPosition, depthRatio, string, new Font("Arial", Font.BOLD, 25)));
	}

	
	private static Font loadFont(String path) {
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, loadFile(path));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return font;
	}

	private static Clip loadAudio(String path) {
		Clip clip = null;

		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(loadFile(path));
			clip = AudioSystem.getClip();
			clip.open(audioInput);

		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clip;
	}

	private static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ResourceManager.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int loadInteger(String string) {
		return Integer.parseInt(string);
	}

	public static double loadDouble(String string) {
		return Double.parseDouble(string);
	}

	public static HashMap<String, Background> getBackgrounds() {
		return backgrounds;
	}

	public static HashMap<String, Terrain> getElements() {
		return terrain;
	}

	public static HashMap<String, Clip> getMusic() {
		return music;
	}
	
	public static HashMap<String, Text> getText() {
		return text;
	}
}
