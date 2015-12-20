package spacegame;

import java.io.*;
import javax.sound.sampled.*;

public class PlayBackgroundMusic extends Thread {
    boolean play;

    public void run() {
        try {
            AudioInputStream audioInputStream;
            File file = new File("\\SpaceGame\\background.wav");
            File file2 = new File("\\SpaceGame\\explosion2.wav");
            Clip line;
            Clip line2;

            audioInputStream = AudioSystem.getAudioInputStream(file);
            line = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioInputStream.getFormat()));
            line.open(audioInputStream);

            audioInputStream = AudioSystem.getAudioInputStream(file2);
            line2 = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioInputStream.getFormat()));
            line2.open(audioInputStream);

            while (true) {
                if (play) {
                    line.start();
                    if (!line.isRunning())
                        line.setFramePosition(0); //loop condition
                } else {
                    line.stop(); //stops playback but does not rewind
                    line.setFramePosition(0); //restart file from beginning


                    line2.start();
                    //if(!line2.isRunning())
                    //line2.setFramePosition(0); //loop condition

                }
            }
            //audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
