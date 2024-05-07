package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.error.GameError;

import java.awt.*;

public class ColorHelper {
    public static String colorToHexCode(final Color c) {
        final String r = Integer.toHexString(c.getRed()),
                g = Integer.toHexString(c.getGreen()),
                b = Integer.toHexString(c.getBlue()),
                a = Integer.toHexString(c.getAlpha());

        return (r.length() == 1 ? ("0") + r : r) +
                (g.length() == 1 ? ("0") + g : g) +
                (b.length() == 1 ? ("0") + b : b) +
                (c.getAlpha() < 0xff ? (a.length() == 1 ? ("0") + a : a) : "");
    }

    public static Color hexCodeToColor(final String contents) {
        final int LENGTH_OF_SECTION = 2, R = 0, G = 2, B = 4, A = 6;
        final String hex = contents.toLowerCase();

        final int r = hexToInt(hex.substring(R, R + LENGTH_OF_SECTION)),
                g = hexToInt(hex.substring(G, G + LENGTH_OF_SECTION)),
                b = hexToInt(hex.substring(B, B + LENGTH_OF_SECTION)),
                a = hexToInt(hex.substring(A, A + LENGTH_OF_SECTION));

        return new Color(r, g, b, a);
    }

    private static int hexToInt(final String hex) {
        if (hex.isEmpty() || !validHexSequence(hex)) {
            GameError.send("String \"" + hex +
                    "\" is not a valid hex sequence.");
            return 0;
        }

        final int BASE = 16;
        int accumulator = 0, power = 0;

        for (int i = hex.length() - 1; i >= 0; i--) {
            final char c = hex.toLowerCase().charAt(i);
            final int placeValue = isNumeric(c)
                    ? c - '0' : 10 + (c - 'a');

            accumulator += placeValue * Math.pow(BASE, power);
            power++;
        }

        return accumulator;
    }

    private static boolean validHexSequence(final String hexSequence) {
        for (char c : hexSequence.toCharArray())
            if (!(isNumeric(c) || isAlpha(c)))
                return false;

        return true;
    }

    private static boolean isNumeric(final char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isAlpha(final char c) {
        return c >= 'a' && c <= 'f';
    }
}
