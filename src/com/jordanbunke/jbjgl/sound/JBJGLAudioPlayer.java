package com.jordanbunke.jbjgl.sound;

import com.jordanbunke.jbjgl.utility.JBJGLGlobal;

import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.Path;

public class JBJGLAudioPlayer {

    private static final int BEGINNING = 0;

    public static void playSoundFile(final Path filepath) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(filepath.toFile());
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.setFramePosition(BEGINNING);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            JBJGLGlobal.printErrorToJBJGLChannel(
                    "This is not a supported audio file: " + filepath
            );
        } catch (LineUnavailableException e) {
            JBJGLGlobal.printErrorToJBJGLChannel(
                    "The line is not available."
            );
        } catch (IOException e) {
            JBJGLGlobal.printErrorToJBJGLChannel(
                    "Could not get the input stream for file: " + filepath
            );
        }
    }
}
