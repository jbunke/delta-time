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

    public static String getContentsFromTag(
            final String toSearch, final String TAG, final String SEPARATOR,
            final String OPEN, final String CLOSE, final String defaultValue
    ) {
        final String openSequence = TAG + SEPARATOR + OPEN;

        if (toSearch.contains(openSequence)) {
            final int startIndex = toSearch.indexOf(openSequence) + openSequence.length();
            int level = 1;

            for (int i = startIndex; i < toSearch.length(); i++) {
                if (toSearch.substring(i).startsWith(OPEN))
                    level++;
                else if (toSearch.substring(i).startsWith(CLOSE)) {
                    level--;

                    if (level == 0)
                        return toSearch.substring(startIndex, i);
                }
            }
        }

        return defaultValue;
    }
}
