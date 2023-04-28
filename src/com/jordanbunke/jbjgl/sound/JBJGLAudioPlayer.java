package com.jordanbunke.jbjgl.sound;

import com.jordanbunke.jbjgl.error.JBJGLError;

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
            JBJGLError.send("This is not a supported audio file: " +
                    filepath);
        } catch (LineUnavailableException e) {
            JBJGLError.send("The line is not available.");
        } catch (IOException e) {
            JBJGLError.send("Could not get the input stream for file: " + filepath);
        }
    }
}
