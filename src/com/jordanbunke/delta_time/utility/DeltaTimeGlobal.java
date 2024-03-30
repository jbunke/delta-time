package com.jordanbunke.delta_time.utility;

import com.jordanbunke.delta_time.Constants;
import com.jordanbunke.delta_time.debug.DebugChannel;
import com.jordanbunke.delta_time.debug.Debugger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class DeltaTimeGlobal {
    private static final String NATIVE_STATUS_CODE_PREFIX = "dt:";

    public static final String
            DT_STATUS_CODE_TEXT_STRING_CONVERSION =
            NATIVE_STATUS_CODE_PREFIX + "conv_text";

    private static final String DT_CHANNEL = Constants.TITLE;

    private static final Debugger DEBUGGER = Debugger.createWithExistingChannels(
            generateChannelMap());
    private static final Map<String, Object> STATUS_MAP = new HashMap<>();

    static {
        setStatus(DT_STATUS_CODE_TEXT_STRING_CONVERSION, true);
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
        STATUS_MAP.put(code, value);
    }
}
