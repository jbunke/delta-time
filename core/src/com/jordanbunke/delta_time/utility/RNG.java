package com.jordanbunke.delta_time.utility;

import java.util.Optional;
import java.util.Random;

public final class RNG {
    private static final Random r = new Random();

    public static boolean prob(final double p) {
        return r.nextDouble() < p;
    }

    public static <T> T prob(final double p, final T a, final T aPrime) {
        return prob(p) ? a : aPrime;
    }

    public static boolean flipCoin() {
        return prob(0.5);
    }

    public static <T> T flipCoin(final T heads, final T tails) {
        return flipCoin() ? heads : tails;
    }

    public static int randomInRange(final int min, final int maxEx) {
        return min + (int)(r.nextDouble() * (maxEx - min));
    }

    public static double randomInRange(final double min, final double max) {
        return min + (r.nextDouble() * (max - min));
    }

    public static int rollDie() {
        return randomInRange(1, 7);
    }

    @SafeVarargs
    public static <T> Optional<T> randomOutcome(final double[] probabilities, final T... outcomes) {
        if (probabilities.length != outcomes.length || probabilities.length < 1)
            return Optional.empty();
        else if (probabilities.length == 1)
            return Optional.of(outcomes[0]);

        final int size = probabilities.length;

        for (int i = 0; i < size - 1; i++) {
            if (probabilities[i + 1] - probabilities[i] <= 0d)
                return Optional.empty();
        }

        if (probabilities[size - 1] != 1d)
            return Optional.empty();

        final double p = r.nextDouble();

        for (int i = 0; i < size; i++)
            if (p < probabilities[i])
                return Optional.of(outcomes[i]);

        return Optional.empty();
    }
}
