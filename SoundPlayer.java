import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class SoundPlayer {
	
	private AudioInputStream audioStream=null;
	private Clip clip;
	public void start(){
		clip.start();
		}
	SoundPlayer(File soundfile){
		try {
			audioStream = AudioSystem.getAudioInputStream(soundfile);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AudioFormat format=audioStream.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class,format);
		/*if (!AudioSystem.isLineSupported(info)) {
		    // Handle the error.
		    }
		    // Obtain and open the line.*/
		
		    try {
				clip = (Clip) AudioSystem.getLine(info);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		    try {
				clip.open(audioStream);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		

	}

}
