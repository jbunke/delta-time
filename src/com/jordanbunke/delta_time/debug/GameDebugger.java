package com.jordanbunke.delta_time.debug;

import java.util.HashMap;
import java.util.Map;

public class GameDebugger extends Debugger {

    public static final String FRAME_RATE = "FRAME RATE";
    public static final String LOGIC_CHANNEL = "GAME LOGIC";
    public static final String PERFORMANCE = "PERFORMANCE";

    private boolean showingBoundingBoxes;
    private int fps;
    private long updateMillis, eventHandlerMillis, renderMillis, drawMillis;
    private long refMillis;

    protected GameDebugger(final Map<String, DebugChannel> channelMap) {
        super(channelMap);

        this.fps = 0;
        this.showingBoundingBoxes = true;
    }

    public static GameDebugger newDefault() {
        return new GameDebugger(
                createBasicGameChannelMap()
        );
    }

    private static Map<String, DebugChannel> createBasicGameChannelMap() {
        Map<String, DebugChannel> channelMap = new HashMap<>();
        Debugger.standardChannelAddition(channelMap, FRAME_RATE, true);
        Debugger.standardChannelAddition(channelMap, LOGIC_CHANNEL, true);
        Debugger.standardChannelAddition(channelMap, PERFORMANCE, true);
        return channelMap;
    }

    public void updateFPS(final int fps) {
        this.fps = fps;
    }

    public int getFPS() {
        return fps;
    }

    public void startTimer() {
        refMillis = System.currentTimeMillis();
    }

    private long elapsedTime() {
        return System.currentTimeMillis() - refMillis;
    }

    public void setUpdateMillis() {
        this.updateMillis = elapsedTime();
    }

    public void setEventHandlerMillis() {
        this.eventHandlerMillis = elapsedTime();
    }

    public void setRenderMillis() {
        this.renderMillis = elapsedTime();
    }

    public void setDrawMillis() {
        this.drawMillis = elapsedTime();
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
