package com.jordanbunke.jbjgl.sound;

import com.jordanbunke.jbjgl.Constants;
import com.jordanbunke.jbjgl.error.JBJGLError;

import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.Path;

public class JBJGLAudioPlayer {

    public static final int BEGINNING = 0, END = -1;

    public static void playSoundFile(final Path filepath) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(filepath.toFile());
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.setFramePosition(BEGINNING);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            JBJGLError.send("This is not a supported audio file: " + filepath);
        } catch (LineUnavailableException e) {
            JBJGLError.send("The line is not available.");
        } catch (IOException e) {
            JBJGLError.send("Could not get the input stream for file: " + filepath);
        }
    }

    public static void loopSoundInNewThread(final JBJGLSound sound) {
        final Thread newSoundThread = getLoopSoundThread(sound);

        newSoundThread.start();
    }

    public static Thread getLoopSoundThread(final JBJGLSound sound) {
        return new Thread(() -> loopSound(sound),
                Constants.TITLE + " loop sound thread: " + sound.getId());
    }

    public static void playSoundInNewThread(final JBJGLSound sound) {
        final Thread newSoundThread = getPlaySoundThread(sound);

        newSoundThread.start();
    }

    public static Thread getPlaySoundThread(final JBJGLSound sound) {
        return new Thread(() -> playSound(sound),
                Constants.TITLE + " loop sound thread: " + sound.getId());
    }

    public static Clip getSoundClip(final JBJGLSound sound) {
        try {
            final Clip clip = AudioSystem.getClip();
            clip.open(sound.getFormat(), sound.getData(), 0, sound.getData().length);
            clip.setFramePosition(BEGINNING);

            return clip;
        } catch (LineUnavailableException e) {
            JBJGLError.send("Line unavailable for sound");
        }

        return null;
    }

    public static void playSound(final JBJGLSound sound) {
        final Clip clip = getSoundClip(sound);

        if (clip != null) clip.start();
    }

    public static void loopSound(final JBJGLSound sound) {
        final Clip clip = getSoundClip(sound);

        if (clip != null) {
            clip.setLoopPoints(BEGINNING, END);

            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
