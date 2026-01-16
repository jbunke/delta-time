package com.jordanbunke.delta_time.scripting.util;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class SyntaxErrorListener extends BaseErrorListener {
    private static final SyntaxErrorListener INSTANCE;

    static {
        INSTANCE = new SyntaxErrorListener();
    }

    public static SyntaxErrorListener get() {
        return INSTANCE;
    }

    @Override
    public void syntaxError(
            final Recognizer<?, ?> recognizer,
            final Object offendingSymbol,
            final int line, final int column,
            final String message,
            final RecognitionException e
    ) {
        ScriptErrorLog.syntaxError(new TextPosition(line, column), message);
    }
}
