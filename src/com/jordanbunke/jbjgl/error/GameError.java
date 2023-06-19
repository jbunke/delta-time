package com.jordanbunke.jbjgl.error;

import com.jordanbunke.jbjgl.utility.JBJGLGlobal;

public class GameError extends Exception {

    private static final String ANSI_RESET = "\033[0m", ANSI_RED_BOLD = "\033[1;31m";

    private final String message;
    private final Runnable consequence;
    private final boolean printToGlobal;
    private final boolean fatal;

    // TODO: Refactor remaining uses of JBJGLGlobal.printErrorToJBJGLChannel

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
        final GameError error = new GameError(message, () -> {}, true, false);
        error.process();
    }

    public void process() {
        if (printToGlobal)
            JBJGLGlobal.printMessageToJBJGLChannel(ANSI_RED_BOLD + "ERROR: " + message + ANSI_RESET);

        consequence.run();

        if (fatal)
            System.exit(0);
    }
}
