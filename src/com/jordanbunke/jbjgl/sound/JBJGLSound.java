package com.jordanbunke.jbjgl.sound;

import com.jordanbunke.jbjgl.error.JBJGLError;
import com.jordanbunke.jbjgl.io.JBJGLResourceLoader;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public final class JBJGLSound {
    private static final int NOT_SET = -1;


    private final String id;
    private final AudioFormat format;
    private final byte[] data;

    private Clip playbackClip;
    private int frame;

    // TODO: add last played in millis buffer so that sound cannot be played repeatedly in too short a span of time

    private JBJGLSound(final String id, final AudioInputStream stream) {
        this.id = id;
        this.format = stream.getFormat();
        try {
            this.data = stream.readAllBytes();
        } catch (IOException e) {
            JBJGLError.send("Couldn't read audio data from input stream");
            throw new Error(e);
        }

        playbackClip = null;
        frame = NOT_SET;
    }

    public static JBJGLSound fromResource(final String id, final InputStream in) {
        try {
            return new JBJGLSound(id, AudioSystem.getAudioInputStream(in));
        } catch (IOException | UnsupportedAudioFileException e) {
            JBJGLError.send("Couldn't load sound from input stream");
        }
        return null;
    }

    public static <T> JBJGLSound fromResource(
            final String id, final Path path, final Class<T> loaderClass
    ) {
        try {
            return new JBJGLSound(id, AudioSystem.getAudioInputStream(
                    JBJGLResourceLoader.loadResourceAsURL(loaderClass, path)
            ));
        } catch (IOException | UnsupportedAudioFileException e) {
            JBJGLError.send("Couldn't load sound from resource: " + path);
        }
        return null;
    }

    public static JBJGLSound fromFile(final String id, final Path filepath) {
        try {
            return new JBJGLSound(id, AudioSystem.getAudioInputStream(filepath.toFile()));
        } catch (IOException | UnsupportedAudioFileException e) {
            JBJGLError.send("Couldn't load sound from file: " + filepath);
        }
        return null;
    }

    public void play() {
        if (!isPlaying()) {
            playbackClip = JBJGLAudioPlayer.getSoundClip(this);

            if (playbackClip != null)
                playbackClip.start();
        }
    }

    public void loop() {
        if (!isPlaying()) {
            playbackClip = JBJGLAudioPlayer.getSoundClip(this);

            if (playbackClip != null) {
                playbackClip.setLoopPoints(JBJGLAudioPlayer.BEGINNING, JBJGLAudioPlayer.END);

                playbackClip.start();
                playbackClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
    }

    public void pause() {
        if (isPlaying()) {
            frame = playbackClip.getFramePosition();
            playbackClip.stop();
        }
    }

    public void resume() {
        if (playbackClip != null && !playbackClip.isRunning() && playbackClip.isOpen() && frame != NOT_SET) {
            playbackClip.setFramePosition(frame);
            playbackClip.start();
        }
    }

    public void stop() {
        if (isPlaying()) {
            playbackClip.stop();
            playbackClip.close();
            playbackClip = null;
        }
    }

    public boolean isPlaying() {
        return playbackClip != null && playbackClip.isRunning();
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
}
