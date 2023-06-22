package com.jordanbunke.jbjgl.utility;

import java.util.Random;

public final class RNG {
    private static final Random r = new Random();

    public static boolean prob(final double p) {
        return r.nextDouble() < p;
    }

    public static boolean flipCoin() {
        return prob(0.5);
    }

    public static int randomInRange(final int min, final int maxEx) {
        return min + (int)(r.nextDouble() * (maxEx - min));
    }

    public static int rollDie() {
        return randomInRange(1, 7);
    }
}
