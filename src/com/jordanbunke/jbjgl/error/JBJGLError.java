package com.jordanbunke.jbjgl.error;

import com.jordanbunke.jbjgl.utility.JBJGLGlobal;

public class JBJGLError extends Exception {
    private final String message;
    private final Runnable consequence;
    private final boolean printToGlobal;
    private final boolean fatal;

    // TODO: Refactor remaining uses of JBJGLGlobal.printErrorToJBJGLChannel

    private JBJGLError(
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
        final JBJGLError error = new JBJGLError(message, consequence, printToGlobal, fatal);
        error.process();
    }

    public static void send(final String message) {
        final JBJGLError error = new JBJGLError(message, () -> {}, true, false);
        error.process();
    }

    public void process() {
        if (printToGlobal)
            JBJGLGlobal.printErrorToJBJGLChannel(message);

        consequence.run();

        if (fatal)
            System.exit(0);
    }
}
