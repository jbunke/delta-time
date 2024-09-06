package com.jordanbunke.delta_time.scripting.util;

public class DebugUtils {
    public static String formatMultiLineNode(
            final String prepend, final String node
    ) {
        final String[] nodeLines = node.split("\n");

        final StringBuilder sb = new StringBuilder();

        for (String nodeLine : nodeLines)
            sb.append(prepend).append(nodeLine).append("\n");

        return sb.toString();
    }
}
