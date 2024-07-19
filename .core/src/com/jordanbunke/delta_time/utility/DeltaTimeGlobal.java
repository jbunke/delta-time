package com.jordanbunke.delta_time.utility;

import com.jordanbunke.delta_time.About;
import com.jordanbunke.delta_time.debug.DebugChannel;
import com.jordanbunke.delta_time.debug.Debugger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class DeltaTimeGlobal {
    private static final String NATIVE_STATUS_CODE_PREFIX = "dt:";

    public static final String
            SC_TEXT_STRING_CONVERSION = NATIVE_STATUS_CODE_PREFIX + "conv_text",
            SC_CURSOR_CAPTURED = NATIVE_STATUS_CODE_PREFIX + "cursor_captured";

    private static final String DT_CHANNEL = About.TITLE;

    private static final Debugger DEBUGGER = Debugger.createWithExistingChannels(
            generateChannelMap());
    private static final Map<String, Object> STATUS_MAP = new HashMap<>();

    static {
        setStatus(SC_TEXT_STRING_CONVERSION, true);
        setStatus(SC_CURSOR_CAPTURED, null);
    }

    private static Map<String, DebugChannel> generateChannelMap() {
        Map<String, DebugChannel> channelMap = new HashMap<>();
        Debugger.standardChannelAddition(channelMap, DT_CHANNEL, false);
        return channelMap;
    }

    public static void print(final String message) {
        DEBUGGER.getChannel(DT_CHANNEL).printMessage(message);
    }

    public static Optional<Object> getStatusOf(final String code) {
        return STATUS_MAP.containsKey(code)
                ? Optional.of(STATUS_MAP.get(code))
                : Optional.empty();
    }

    public static void setStatus(final String code, final Object value) {
        if (value == null)
            STATUS_MAP.remove(code);
        else
            STATUS_MAP.put(code, value);
    }
}
