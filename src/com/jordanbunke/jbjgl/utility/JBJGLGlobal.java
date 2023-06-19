package com.jordanbunke.jbjgl.utility;

import com.jordanbunke.jbjgl.Constants;
import com.jordanbunke.jbjgl.debug.DebugChannel;
import com.jordanbunke.jbjgl.debug.Debugger;

import java.util.HashMap;
import java.util.Map;

public class JBJGLGlobal {
    private static final String JBJGL_CHANNEL = Constants.TITLE;

    private static final Debugger DEBUGGER = Debugger.createWithExistingChannels(
            generateChannelMap());

    private static Map<String, DebugChannel> generateChannelMap() {
        Map<String, DebugChannel> channelMap = new HashMap<>();
        Debugger.standardChannelAddition(channelMap, JBJGL_CHANNEL, false);
        return channelMap;
    }

    public static void printMessageToJBJGLChannel(final String message) {
        DEBUGGER.getChannel(JBJGL_CHANNEL).printMessage(message);
    }
}
