package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLMessageLog;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.io.JBJGLListener;

import java.awt.*;
import java.util.Arrays;

public class JBJGLAnimationMenuElement extends JBJGLMenuElement {
    private final int[] frameTimings;
    private final JBJGLImage[] frames;
    private int frameIndex, count;

    private JBJGLAnimationMenuElement(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final int[] frameTimings, final JBJGLImage[] frames
    ) {
        super(position, dimensions, anchor);

        this.frameTimings = frameTimings;
        this.frames = frames;
        this.frameIndex = 0;
        this.count = 0;
    }

    public static JBJGLAnimationMenuElement generate(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final int[] frameTimings, final JBJGLImage[] frames
    ) {
        return new JBJGLAnimationMenuElement(position, dimensions, anchor, frameTimings, frames);
    }

    public static JBJGLAnimationMenuElement generate(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final int ticksPerFrame, final JBJGLImage[] frames
    ) {
        final int[] frameTimings = new int[frames.length];
        Arrays.fill(frameTimings, ticksPerFrame);

        return new JBJGLAnimationMenuElement(position, dimensions, anchor, frameTimings, frames);
    }

    @Override
    public void update(final JBJGLMessageLog messageLog) {
        if (frames.length != frameTimings.length) {
            messageLog.printMessage(
                    "Animation menu element does not have the same number of frames as timings and could not be displaced"
            );
            return;
        }

        count++;

        if (count >= frameTimings[frameIndex]) {
            frameIndex++;
            count = 0;
        }
        if (frameIndex >= frames.length)
            frameIndex = 0;
    }

    @Override
    public void render(Graphics g) {
        draw(frames[frameIndex], g);
    }

    @Override
    public void process(JBJGLListener listener, JBJGLMenuManager menuManager) {

    }

    @Override
    public String toString() {
        return frames.length + " frame animation " + super.toString();
    }
}
