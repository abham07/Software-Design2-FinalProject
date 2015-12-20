package spacegame;

import java.io.*;
import javax.sound.sampled.*;

public class PlayContinuousRumble extends Thread {
    boolean engineOn;

    public void run() {
        try {
            AudioInputStream audioInputStream;
            File file = new File("\\SpaceGame\\engine3.wav");
            Clip line;
            audioInputStream = AudioSystem.getAudioInputStream(file);
            line = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioInputStream.getFormat()));
            line.open(audioInputStream);

            while (true) {
                if (engineOn) {
                    line.start();
                    if (!line.isRunning())
                        line.setFramePosition(0); //loop condition
                } else {
                    line.stop(); //stops playback but does not rewind
                    line.setFramePosition(0); //restart file from beginning
                }
            }
            //audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
