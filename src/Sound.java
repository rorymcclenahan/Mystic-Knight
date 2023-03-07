import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// Name: Rory McClenahan
// Student Number: 22210985

public class Sound {
	Model gameworld =new Model(); 
	private Clip clip;
	private boolean isPlaying;
	
	public Sound(Model World) {
		this.gameworld=World;
		this.clip = null;
        this.isPlaying = false;
	}

//	public static void main(String[] args) {
//        System.out.println("Hello");
//        
//     
////        	 playSound();
//     
//    }
	
	public void playSound(String fileName) {  
		
        
//		 System.out.println(fileName);
		 try {
			 AudioInputStream audioInputStream = null;
			 File file = new File("res/" + fileName); // StackOverflow for this line https://stackoverflow.com/questions/61915708/why-is-there-a-nullpointerexception-when-execute-the-audiosystem-getaudioinputst
			 audioInputStream = AudioSystem.getAudioInputStream(file);
	            Clip newClip = AudioSystem.getClip();
	            newClip.open(audioInputStream);
	            newClip.start();
	            newClip.loop(Clip.LOOP_CONTINUOUSLY);
	            this.clip = newClip;
	            this.isPlaying = true;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
	public void stopSound() {
        if (this.clip != null) {
            this.clip.stop();
            this.clip.close();
            this.clip = null;
            this.isPlaying = false;
        }
    }
	public boolean isPlaying() {
        return this.isPlaying;
    }

}
