package com.jordanbunke.jbjgl.debug;

import java.util.HashMap;
import java.util.Map;

public class JBJGLGameDebugger extends JBJGLDebugger {

    public static final String FRAME_RATE = "FRAME RATE";
    public static final String LOGIC_CHANNEL = "GAME LOGIC";
    public static final String PERFORMANCE = "PERFORMANCE";

    private boolean showingBoundingBoxes;
    private int fps;
    private long updateMillis, eventHandlerMillis, renderMillis, drawMillis;

    private JBJGLGameDebugger(final Map<String, JBJGLDebugChannel> channelMap) {
        super(channelMap);

        this.fps = 0;
        this.showingBoundingBoxes = true;
    }

    public static JBJGLGameDebugger create() {
        return new JBJGLGameDebugger(
                createBasicGameChannelMap()
        );
    }

    private static Map<String, JBJGLDebugChannel> createBasicGameChannelMap() {
        Map<String, JBJGLDebugChannel> channelMap = new HashMap<>();
        JBJGLDebugger.standardChannelAddition(channelMap, FRAME_RATE, true);
        JBJGLDebugger.standardChannelAddition(channelMap, LOGIC_CHANNEL, true);
        JBJGLDebugger.standardChannelAddition(channelMap, PERFORMANCE, true);
        return channelMap;
    }

    public void updateFPS(final int fps) {
        this.fps = fps;
    }

    public int getFPS() {
        return fps;
    }

    public void setUpdateMillis(final long updateMillis) {
        this.updateMillis = updateMillis;
    }

    public void setEventHandlerMillis(final long eventHandlerMillis) {
        this.eventHandlerMillis = eventHandlerMillis;
    }

    public void setRenderMillis(final long renderMillis) {
        this.renderMillis = renderMillis;
    }

    public void setDrawMillis(final long drawMillis) {
        this.drawMillis = drawMillis;
    }

    public long getUpdateMillis() {
        return updateMillis;
    }

    public long getEventHandlerMillis() {
        return eventHandlerMillis;
    }

    public long getRenderMillis() {
        return renderMillis;
    }

    public long getDrawMillis() {
        return drawMillis;
    }

    public boolean isShowingBoundingBoxes() {
        return showingBoundingBoxes;
    }

    public void showBoundingBoxes() {
        showingBoundingBoxes = true;
    }

    public void hideBoundingBoxes() {
        showingBoundingBoxes = false;
    }
}
