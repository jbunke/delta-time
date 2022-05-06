package com.jordanbunke.jbjgl.debug;

import java.util.HashMap;
import java.util.Map;

public class JBJGLGameDebugger extends JBJGLDebugger {

    public static final String PERFORMANCE_CHANNEL = "PERFORMANCE";
    public static final String LOGIC_CHANNEL = "LOGIC";
    public static final String MEMORY_CHANNEL = "MEMORY";

    private boolean showingBoundingBoxes;
    private int fps;

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
        JBJGLDebugger.standardChannelAddition(channelMap, PERFORMANCE_CHANNEL);
        JBJGLDebugger.standardChannelAddition(channelMap, LOGIC_CHANNEL);
        JBJGLDebugger.standardChannelAddition(channelMap, MEMORY_CHANNEL);
        return channelMap;
    }

    public void updateFPS(final int fps) {
        this.fps = fps;
    }

    public int getFPS() {
        return fps;
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
