package com.jordanbunke.jbjgl.events;

import java.awt.event.KeyEvent;

public enum JBJGLKey {
    DOWN_ARROW, UP_ARROW, LEFT_ARROW, RIGHT_ARROW,

    ENTER, BACKSPACE, DELETE, TAB, SHIFT, ESCAPE, SPACE,

    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,

    _1, _2, _3, _4, _5, _6, _7, _8, _9, _0,

    UNSUPPORTED;

    public static JBJGLKey fromKeyEvent(final KeyEvent keyEvent) {
        final char character = keyEvent.getKeyChar();
        final int code = keyEvent.getKeyCode();

        return switch (character) {
            case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                    'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                    'x', 'y', 'z' ->
                    JBJGLKey.valueOf(String.valueOf(character).toUpperCase());
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' ->
                    JBJGLKey.valueOf("_" + character);
            case '\n' -> ENTER;
            case ' ' -> SPACE;
            default -> getFromCode(code);
        };
    }
    
    private static JBJGLKey getFromCode(final int code) {
        return switch (code) {
            case 8 -> BACKSPACE;
            case 9 -> TAB;
            case 10 -> ENTER;
            case 16 -> SHIFT;
            case 27 -> ESCAPE;
            case 37 -> LEFT_ARROW;
            case 38 -> UP_ARROW;
            case 39 -> RIGHT_ARROW;
            case 40 -> DOWN_ARROW;
            case 127 -> DELETE;
            default -> UNSUPPORTED;
        };
    }
}
