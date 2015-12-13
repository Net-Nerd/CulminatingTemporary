package managers;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class handles the loading and saving of various resources required for
 * the game.
 * 
 * @author Chandra Gummaluru
 * @version 1.1.0
 */
public class ResourceManager {

	/**
	 * The various types of resources the manager can load.
	 */
	public enum ResourceType {
		TEXT, IMAGE, AUDIO, FONT
	};

	/**
	 * A hash map with keys of type String and values of type BufferedImage.
	 * This map is used to store image assets.
	 */
	private static LinkedHashMap<String, BufferedImage> images = new LinkedHashMap<String, BufferedImage>();

	/**
	 * A hash map with keys of type String and values of type Clip. This map is
	 * used to store audio assets.
	 */
	private static LinkedHashMap<String, Clip> audio = new LinkedHashMap<String, Clip>();

	/**
	 * A hash map with keys of type String and values of type Font. This map is
	 * used to store font assets.
	 */
	private static LinkedHashMap<String, Font> fonts = new LinkedHashMap<String, Font>();

	/**
	 * A hash map with keys of type String and values of type File. This map is
	 * used to store text assets.
	 */
	private static LinkedHashMap<String, ArrayList<String>> text = new LinkedHashMap<String, ArrayList<String>>();

	/**
	 * The delimiter constant for the
	 */
	private static final String TYPE_DELIMITER = ":";

	/**
	 * An array to temporarily store the resource data until parsing.
	 */
	private static ArrayList<String> resourceData;

	/**
	 * This class cannot be instantiated.
	 */
	private ResourceManager() {
	}

	/**
	 * Loads the required resources from the specified path.
	 * 
	 * @param filePath
	 *            the path of the resource text file.
	 */
	public static void loadResources(String filePath) {
		resourceData = loadText(filePath);

		for (int i = 0; i < resourceData.size(); i++) {
			String[] assetData = resourceData.get(i).split(TYPE_DELIMITER);

			ResourceType type = ResourceType.valueOf(assetData[0]);
			String key = assetData[1];
			String path = assetData[2];

			switch (type) {
			case TEXT:
				text.put(key, loadText(path));
				break;
			case IMAGE:
				images.put(key, loadImage(path));
				break;
			case AUDIO:
				audio.put(key, loadAudio(path));
				break;
			case FONT:
				fonts.put(key, loadFont(path));
				break;
			}
		}
	}

	/**
	 * Loads a file from the specified source path.
	 * 
	 * @param path
	 *            the path of the file.
	 * @return a new file.
	 */
	private static File loadFile(String path) {
		return new File(ResourceManager.class.getResource(path).getFile());
	}

	/**
	 * Loads text from the specified source path.
	 * 
	 * @param path
	 *            the path of the file.
	 * @return a new String ArrayList.
	 */
	private static ArrayList<String> loadText(String path) {
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(loadFile(path)));
			ArrayList<String> assetData = new ArrayList<String>();

			String currentLine;

			while ((currentLine = fileReader.readLine()) != null) {
				assetData.add(currentLine);
			}
			fileReader.close();
			return assetData;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Loads an image file from the specified source path.
	 * 
	 * @param path
	 *            the path of the file.
	 * @return a new BufferedImage.
	 */
	private static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(loadFile(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Loads an audio file from the specified source path.
	 * 
	 * @param path
	 *            the path of the file.
	 * @return a new Clip.
	 */
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

	/**
	 * Loads a font file from the specified source path.
	 * 
	 * @param path
	 *            the path of the file.
	 * @return a new Font.
	 */
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

	/**
	 * Returns all of the loaded text as a LinkedHashMap.
	 * 
	 * @return a LinkedHashMap containing String ArrayLists.
	 */
	public static LinkedHashMap<String, ArrayList<String>> getText() {
		return text;
	}

	/**
	 * Returns all of the loaded images as a LinkedHashMap.
	 * 
	 * @return a LinkedHashMap containing BufferedImage objects.
	 */
	public static LinkedHashMap<String, BufferedImage> getImages() {
		return images;
	}

	/**
	 * Returns all of the loaded audio as a LinkedHashMap.
	 * 
	 * @return a LinkedHashMap containing Clip objects.
	 */
	public static LinkedHashMap<String, Clip> getAudio() {
		return audio;
	}

	/**
	 * Returns all of the loaded fonts as a LinkedHashMap.
	 * 
	 * @return a LinkedHashMap containing Font objects.
	 */
	public static LinkedHashMap<String, Font> getFonts() {
		return fonts;
	}

	public static void unloadResources() {
		text.clear();
		images.clear();
		audio.clear();
		fonts.clear();
	}
}