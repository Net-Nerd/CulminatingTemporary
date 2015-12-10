package tools;

import javax.sound.sampled.Clip;

public class AudioManager {
	
	public static void play(Clip clip) {
		clip.start();
	}
}
