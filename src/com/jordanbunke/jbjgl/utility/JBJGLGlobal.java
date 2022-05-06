package com.jordanbunke.jbjgl.utility;

import com.jordanbunke.jbjgl.debug.JBJGLDebugChannel;
import com.jordanbunke.jbjgl.debug.JBJGLDebugger;

import java.util.HashMap;
import java.util.Map;

public class JBJGLGlobal {
    private static final String ANSI_RESET = "\033[0m";
    private static final String ANSI_RED_BOLD = "\033[1;31m";

    private static final JBJGLDebugger DEBUGGER = JBJGLDebugger.createWithExistingChannels(
            generateChannelMap()
    );

    private static Map<String, JBJGLDebugChannel> generateChannelMap() {
        Map<String, JBJGLDebugChannel> channelMap = new HashMap<>();
        JBJGLDebugger.standardChannelAddition(channelMap, "JBJGL");
        return channelMap;
    }

    public static void printMessageToJBJGLChannel(final String message) {
        DEBUGGER.getChannel(JBJGLDebugger.DEFAULT_CHANNEL).printMessage(message);
    }

    public static void printErrorToJBJGLChannel(final String message) {
        printMessageToJBJGLChannel(ANSI_RED_BOLD + "ERROR: " + message + ANSI_RESET);
    }
}
