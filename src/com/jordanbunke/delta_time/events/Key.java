package com.jordanbunke.delta_time.events;

import java.awt.event.KeyEvent;

public enum Key {
    DOWN_ARROW, UP_ARROW, LEFT_ARROW, RIGHT_ARROW,

    ENTER, BACKSPACE, DELETE, TAB, SHIFT, ESCAPE, SPACE, CTRL, ALT,

    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,

    _1, _2, _3, _4, _5, _6, _7, _8, _9, _0,

    UNSUPPORTED;

    public String print() {
        return "[ " + switch (this) {
            case A, B, C, D, E, F, G, H, I, J, K, L, M,
                    N, O, P, Q, R, S, T, U, V, W, X, Y, Z,
                    ENTER, BACKSPACE, DELETE, TAB, SHIFT,
                    ESCAPE, SPACE, CTRL, ALT -> name();
            case _0, _1, _2, _3, _4, _5, _6, _7, _8, _9 -> name().substring(1);
            case UP_ARROW, DOWN_ARROW, LEFT_ARROW, RIGHT_ARROW ->
                    name().replace("_", " ");
            default -> "UNKNOWN INPUT";
        } + " ]";
    }

    public static Key fromKeyEvent(final KeyEvent keyEvent) {
        final char character = keyEvent.getKeyChar();
        final int code = keyEvent.getKeyCode();

        return switch (character) {
            case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                    'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                    'x', 'y', 'z' ->
                    Key.valueOf(String.valueOf(character).toUpperCase());
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' ->
                    Key.valueOf("_" + character);
            case '\n' -> ENTER;
            case ' ' -> SPACE;
            default -> getFromCode(code);
        };
    }
    
    private static Key getFromCode(final int code) {
        return switch (code) {
            case 8 -> BACKSPACE;
            case 9 -> TAB;
            case 10 -> ENTER;
            case 16 -> SHIFT;
            case 17 -> CTRL;
            case 18 -> ALT;
            case 27 -> ESCAPE;
            case 37 -> LEFT_ARROW;
            case 38 -> UP_ARROW;
            case 39 -> RIGHT_ARROW;
            case 40 -> DOWN_ARROW;
            case 49, 50, 51, 52, 53, 54, 55, 56, 57 -> Key.valueOf("_" + (char) code);
            case 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78,
                    79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90 -> Key.valueOf(String.valueOf((char) code));
            case 127 -> DELETE;
            default -> UNSUPPORTED;
        };
    }
}
