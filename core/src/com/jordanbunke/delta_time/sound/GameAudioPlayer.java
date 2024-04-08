package com.jordanbunke.delta_time.sound;

import com.jordanbunke.delta_time.About;
import com.jordanbunke.delta_time.error.GameError;

import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.Path;

public class GameAudioPlayer {

    public static final int BEGINNING = 0, END = -1;

    public static void playSoundFile(final Path filepath) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(filepath.toFile());
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.setFramePosition(BEGINNING);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            GameError.send("This is not a supported audio file: " + filepath);
        } catch (LineUnavailableException e) {
            GameError.send("The line is not available.");
        } catch (IOException e) {
            GameError.send("Could not get the input stream for file: " + filepath);
        }
    }

    public static void loopSoundInNewThread(final Sound sound) {
        final Thread newSoundThread = getLoopSoundThread(sound);

        newSoundThread.start();
    }

    public static Thread getLoopSoundThread(final Sound sound) {
        return new Thread(() -> loopSound(sound),
                About.TITLE + " loop sound thread: " + sound.getId());
    }

    public static void playSoundInNewThread(final Sound sound) {
        final Thread newSoundThread = getPlaySoundThread(sound);

        newSoundThread.start();
    }

    public static Thread getPlaySoundThread(final Sound sound) {
        return new Thread(() -> playSound(sound),
                About.TITLE + " loop sound thread: " + sound.getId());
    }

    public static Clip getSoundClip(final Sound sound) {
        try {
            final Clip clip = AudioSystem.getClip();
            clip.open(sound.getFormat(), sound.getData(), 0, sound.getData().length);
            clip.setFramePosition(BEGINNING);

            return clip;
        } catch (LineUnavailableException e) {
            GameError.send("Line unavailable for sound");
        }

        return null;
    }

    public static void playSound(final Sound sound) {
        final Clip clip = getSoundClip(sound);

        if (clip != null) clip.start();
    }

    public static void loopSound(final Sound sound) {
        final Clip clip = getSoundClip(sound);

        if (clip != null) {
            clip.setLoopPoints(BEGINNING, END);

            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
