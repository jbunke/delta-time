package com.jordanbunke.jbjgl.utility;

public class StringProcessing {
    public static String linesToString(final String[] lines) {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            s.append(lines[i]);
            if (i + 1 < lines.length)
                s.append("\n");
        }

        return s.toString();
    }
}
