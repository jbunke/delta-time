package com.jordanbunke.jbjgl.utility;

import com.jordanbunke.jbjgl.Constants;
import com.jordanbunke.jbjgl.debug.JBJGLDebugChannel;
import com.jordanbunke.jbjgl.debug.JBJGLDebugger;

import java.util.HashMap;
import java.util.Map;

public class JBJGLGlobal {
    private static final String JBJGL_CHANNEL = Constants.TITLE;

    private static final JBJGLDebugger DEBUGGER = JBJGLDebugger.createWithExistingChannels(
            generateChannelMap());

    private static Map<String, JBJGLDebugChannel> generateChannelMap() {
        Map<String, JBJGLDebugChannel> channelMap = new HashMap<>();
        JBJGLDebugger.standardChannelAddition(channelMap, JBJGL_CHANNEL, false);
        return channelMap;
    }

    public static void printMessageToJBJGLChannel(final String message) {
        DEBUGGER.getChannel(JBJGL_CHANNEL).printMessage(message);
    }
}
