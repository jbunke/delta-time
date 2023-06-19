package com.jordanbunke.jbjgl.sound;

import com.jordanbunke.jbjgl.error.JBJGLError;
import com.jordanbunke.jbjgl.io.JBJGLResourceLoader;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public final class Sound {
    private static final int NOT_SET = -1;

    private final String id;
    private final AudioFormat format;
    private final byte[] data;

    private Clip playbackClip;
    private int frame;
    private boolean playing;

    // TODO: add last played in millis buffer so that sound cannot be played repeatedly in too short a span of time

    private Sound(final String id, final AudioInputStream stream) {
        this.id = id;
        this.format = stream.getFormat();
        try {
            this.data = stream.readAllBytes();
        } catch (IOException e) {
            JBJGLError.send("Couldn't read audio data from input stream");
            throw new Error(e);
        }

        try {
            playbackClip = AudioSystem.getClip();
            playbackClip.open(format, data, 0, data.length);
            playbackClip.setFramePosition(0);
        } catch (LineUnavailableException e) {
            JBJGLError.send("The line is not available.");
            playbackClip = null;
        }

        frame = NOT_SET;
    }

    public static Sound fromResource(final String id, final InputStream in) {
        try {
            return new Sound(id, AudioSystem.getAudioInputStream(in));
        } catch (IOException | UnsupportedAudioFileException e) {
            JBJGLError.send("Couldn't load sound from input stream");
        }
        return null;
    }

    public static <T> Sound fromResource(
            final String id, final Path path, final Class<T> loaderClass
    ) {
        try {
            return new Sound(id, AudioSystem.getAudioInputStream(
                    JBJGLResourceLoader.loadResourceAsURL(loaderClass, path)
            ));
        } catch (IOException | UnsupportedAudioFileException e) {
            JBJGLError.send("Couldn't load sound from resource: " + path);
        }
        return null;
    }

    public static Sound fromFile(final String id, final Path filepath) {
        try {
            return new Sound(id, AudioSystem.getAudioInputStream(filepath.toFile()));
        } catch (IOException | UnsupportedAudioFileException e) {
            JBJGLError.send("Couldn't load sound from file: " + filepath);
        }
        return null;
    }

    public void play() {
        if (!isPlaying()) {
            if (playbackClip.isActive())
                playbackClip.stop();

            playing = true;

            playbackClip.setFramePosition(0);
            playbackClip.loop(0);
            playbackClip.start();
        }
    }

    public void loop() {
        if (!isPlaying()) {
            if (playbackClip.isActive())
                playbackClip.stop();

            playing = true;

            playbackClip.setFramePosition(0);
            playbackClip.loop(Clip.LOOP_CONTINUOUSLY);
            playbackClip.start();
        }
    }

    public void pause() {
        if (isPlaying()) {
            playing = false;

            frame = playbackClip.getFramePosition();
            playbackClip.stop();
        }
    }

    public void resume() {
        if (!isPlaying() && frame != NOT_SET) {
            playing = true;

            playbackClip.setFramePosition(frame);
            playbackClip.start();

            frame = NOT_SET;
        }
    }

    public void stop() {
        if (isPlaying()) {
            playing = false;

            frame = NOT_SET;
            playbackClip.stop();
        }
    }

    public boolean isPlaying() {
        return playing && playbackClip != null && playbackClip.isRunning();
    }

    public String getId() {
        return id;
    }

    public AudioFormat getFormat() {
        return format;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return id + (playbackClip != null
                ? " [ " + (isPlaying() ? "playing" : "paused at frame " + frame)
                : "not playing") + " ]";
    }
}
