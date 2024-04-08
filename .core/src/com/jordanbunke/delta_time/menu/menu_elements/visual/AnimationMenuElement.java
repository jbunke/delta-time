package com.jordanbunke.delta_time.menu.menu_elements.visual;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.Arrays;

public class AnimationMenuElement extends MenuElement {
    private final int[] frameTimings;
    private final GameImage[] frames;
    private int frameIndex, count;

    public AnimationMenuElement(
            final Coord2D position, final Coord2D dimensions, final Anchor anchor,
            final int[] frameTimings, final GameImage... frames
    ) {
        super(position, dimensions, anchor, true);

        this.frameTimings = frameTimings;
        this.frames = frames;
        this.frameIndex = 0;
        this.count = 0;
    }

    public AnimationMenuElement(
            final Coord2D position, final Coord2D dimensions, final Anchor anchor,
            final int ticksPerFrame, final GameImage... frames
    ) {
        this(position, dimensions, anchor, consistentFrameTimings(ticksPerFrame, frames.length), frames);
    }

    private static int[] consistentFrameTimings(final int ticksPerFrame, final int numFrames) {
        final int[] frameTimings = new int[numFrames];
        Arrays.fill(frameTimings, ticksPerFrame);

        return frameTimings;
    }

    @Override
    public void update(final double deltaTime) {
        if (frames.length != frameTimings.length) {
            GameError.send("Animation menu element does not have the same number" +
                    " of frames as it does timings, and thus could not be displayed");
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
    public void render(final GameImage canvas) {
        draw(frames[frameIndex], canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        renderBoundingBox(canvas, debugger);
    }

    @Override
    public void process(final InputEventLogger eventLogger) {

    }

    @Override
    public String toString() {
        return frames.length + " frame animation " + super.toString();
    }
}
