package managers;

import javax.sound.sampled.Clip;

/**
 * 
 * @author Anddy Pena
 * @version 0.0.2
 *
 */
public class AudioManager {

	public static void play(Clip clip) {
		clip.start();
	}
}
