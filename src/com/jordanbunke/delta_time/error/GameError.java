package com.jordanbunke.delta_time.error;

import com.jordanbunke.delta_time.utility.DeltaTimeGlobal;

public class GameError extends Exception {
    private static final int FATAL_GAME_ERROR_EXIT_CODE = 42;

    private final String message;
    private final Runnable consequence;
    private final boolean printToGlobal;
    private final boolean fatal;

    private GameError(
            final String message, final Runnable consequence,
            final boolean printToGlobal, final boolean fatal
    ) {
        this.message = message;
        this.consequence = consequence;
        this.printToGlobal = printToGlobal;
        this.fatal = fatal;
    }

    public static void send(
            final String message, final Runnable consequence,
            final boolean printToGlobal, final boolean fatal
    ) {
        final GameError error = new GameError(message, consequence, printToGlobal, fatal);
        error.process();
    }

    public static void send(final String message) {
        send(message, () -> {}, true, false);
    }

    public void process() {
        if (printToGlobal)
            DeltaTimeGlobal.print("ERROR: " + message);

        consequence.run();

        if (fatal)
            System.exit(FATAL_GAME_ERROR_EXIT_CODE);
    }
}
