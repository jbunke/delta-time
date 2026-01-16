package com.jordanbunke.delta_time.scripting.util;

public class Error {
    private final Type type;
    private final TextPosition position;
    private final String message;

    public enum Type {
        SYNTAX, SEMANTIC, RUNTIME;

        @Override
        public String toString() {
            return name() + " ERROR";
        }
    }

    public Error(
            final Type type, final TextPosition position, final String message
    ) {
        this.type = type;
        this.position = position;
        this.message = message;
    }

    @Override
    public String toString() {
        return (position.exists() ? "[" + position + "] " : "") +
                type + ": " + message;
    }
}
