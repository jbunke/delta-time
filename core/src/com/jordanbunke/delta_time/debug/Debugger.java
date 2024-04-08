package com.jordanbunke.delta_time.debug;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Debugger {
    public static final String DEFAULT_CHANNEL = "DEFAULT";

    private final Map<String, DebugChannel> channelMap;
    private final Map<String, Boolean> customFields;

    Debugger(final Map<String, DebugChannel> channelMap) {
        Debugger.addDefaultChannel(channelMap);

        this.channelMap = channelMap;
        this.customFields = new HashMap<>();
    }

    public static Debugger createWithNoChannels() {
        Map<String, DebugChannel> channelMap = new HashMap<>();
        return createWithExistingChannels(channelMap);
    }

    public static Debugger createWithExistingChannels(
            final Map<String, DebugChannel> channelMap
    ) {
        return new Debugger(channelMap);
    }

    public static void standardChannelAddition(
            final Map<String, DebugChannel> channelMap,
            final String newChannelID, final boolean printsChannelID
    ) {
        channelMap.put(newChannelID, new DebugChannel(newChannelID,
                System.out::println, false, printsChannelID));
    }

    // CHANNELS
    private static void addDefaultChannel(final Map<String, DebugChannel> channelMap) {
        if (!channelMap.containsKey(DEFAULT_CHANNEL))
            standardChannelAddition(channelMap, DEFAULT_CHANNEL, false);
    }

    public void muteChannel(final String id) {
        if (channelMap.containsKey(id))
            channelMap.get(id).mute();
    }

    public void unmuteChannel(final String id) {
        if (channelMap.containsKey(id))
            channelMap.get(id).unmute();
    }

    public void addChannel(final DebugChannel channel) {
        channelMap.put(channel.getID(), channel);
    }

    public void createChannel(
            final String id, final Consumer<String> outputFunction,
            final boolean initiallyMuted, final boolean printsChannelID
    ) {
        DebugChannel channel = new DebugChannel(id,
                outputFunction, initiallyMuted, printsChannelID);
        channelMap.put(id, channel);
    }

    public DebugChannel getChannel(final String id) {
        if (channelMap.containsKey(id))
            return channelMap.get(id);
        else
            return channelMap.get(DEFAULT_CHANNEL);
    }

    // CUSTOM FIELDS
    public void setCustomField(final String id, final boolean value) {
        customFields.put(id, value);
    }

    public boolean isCustomField(final String id) {
        return customFields.getOrDefault(id, false);
    }
}
