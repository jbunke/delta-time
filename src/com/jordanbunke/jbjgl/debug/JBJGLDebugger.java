package com.jordanbunke.jbjgl.debug;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class JBJGLDebugger {
    public static final String DEFAULT_CHANNEL = "DEFAULT";

    private final Map<String, JBJGLDebugChannel> channelMap;

    JBJGLDebugger(final Map<String, JBJGLDebugChannel> channelMap) {
        JBJGLDebugger.addDefaultChannel(channelMap);

        this.channelMap = channelMap;
    }

    public static void standardChannelAddition(
            final Map<String, JBJGLDebugChannel> channelMap, final String newChannelID
    ) {
        channelMap.put(newChannelID, JBJGLDebugChannel.initialize(
                newChannelID, new DefaultOutputFunction(), false
        ));
    }

    public static JBJGLDebugger createWithNoChannels() {
        Map<String, JBJGLDebugChannel> channelMap = new HashMap<>();
        return createWithExistingChannels(channelMap);
    }

    public static JBJGLDebugger createWithExistingChannels(
            final Map<String, JBJGLDebugChannel> channelMap
    ) {
        return new JBJGLDebugger(channelMap);
    }


    public static void addDefaultChannel(final Map<String, JBJGLDebugChannel> channelMap) {
        if (!channelMap.containsKey(DEFAULT_CHANNEL))
            channelMap.put(DEFAULT_CHANNEL, JBJGLDebugChannel.initialize(
                    DEFAULT_CHANNEL, new DefaultOutputFunction(), false
        ));
    }

    public void muteChannel(final String id) {
        if (channelMap.containsKey(id))
            channelMap.get(id).mute();
    }

    public void unmuteChannel(final String id) {
        if (channelMap.containsKey(id))
            channelMap.get(id).unmute();
    }

    public void addChannel(final JBJGLDebugChannel channel) {
        channelMap.put(channel.getID(), channel);
    }

    public void createChannel(
            final String id, final Consumer<String> outputFunction, final boolean initiallyMuted
    ) {
        JBJGLDebugChannel channel = JBJGLDebugChannel.initialize(
                id, outputFunction, initiallyMuted
        );
        channelMap.put(id, channel);
    }

    public JBJGLDebugChannel getChannel(final String id) {
        if (channelMap.containsKey(id))
            return channelMap.get(id);
        else {

            return channelMap.get(DEFAULT_CHANNEL);
        }
    }
}
