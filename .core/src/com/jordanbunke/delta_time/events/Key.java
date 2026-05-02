package com.jordanbunke.delta_time.events;

import com.jordanbunke.delta_time.utility.OSUtils;

import java.awt.event.KeyEvent;

public enum Key {
    // arrow keys
    DOWN_ARROW, UP_ARROW, LEFT_ARROW, RIGHT_ARROW,
    // special keys
    ENTER, BACKSPACE, DELETE, TAB, SHIFT, ESCAPE, SPACE, ALT,
    CTRL, CTRL_OR_COMMAND, COMMAND,
    // punctuation keys
    COMMA, PERIOD, MINUS, EQUALS, SEMICOLON, APOSTROPHE, SLASH, BACKSLASH,
    OPEN_SQUARE_BRACKET, CLOSE_SQUARE_BRACKET,
    // alphabet keys
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,
    // numerical keys
    _1, _2, _3, _4, _5, _6, _7, _8, _9, _0,

    UNSUPPORTED;

    private static boolean ctrlCommandMatch;

    static {
        ctrlCommandMatch = false;
    }

    public static boolean isCtrlCommandMatch() {
        return ctrlCommandMatch;
    }

    public static void setCtrlCommandMatch(final boolean ctrlCommandMatch) {
        Key.ctrlCommandMatch = ctrlCommandMatch;
    }

    @Override
    public String toString() {
        return "[ " + simpleName() + " ]";
    }

    public String simpleName() {
        return switch (this) {
            case A, B, C, D, E, F, G, H, I, J, K, L, M,
                 N, O, P, Q, R, S, T, U, V, W, X, Y, Z,
                 ENTER, BACKSPACE, DELETE, TAB, SHIFT,
                 ESCAPE, SPACE, CTRL, ALT -> name();
            case _0, _1, _2, _3, _4, _5, _6, _7, _8, _9 -> name().substring(1);
            case UP_ARROW, DOWN_ARROW, LEFT_ARROW, RIGHT_ARROW ->
                    name().replace("_", " ");
            case CTRL_OR_COMMAND -> resolveCtrlCommand().simpleName();
            case COMMAND -> "⌘";
            case COMMA -> ",";
            case PERIOD -> ".";
            case MINUS -> "-";
            case EQUALS -> "=";
            case SLASH -> "/";
            case BACKSLASH -> "\\";
            case APOSTROPHE -> "'";
            case SEMICOLON -> ";";
            case OPEN_SQUARE_BRACKET -> "[";
            case CLOSE_SQUARE_BRACKET -> "]";
            default -> "UNKNOWN INPUT";
        };
    }

    private static Key resolveCtrlCommand() {
        return OSUtils.isMacOS() ? COMMAND : CTRL;
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
            default -> fromCode(code);
        };
    }
    
    private static Key fromCode(final int code) {
        return switch (code) {
            case KeyEvent.VK_BACK_SPACE -> BACKSPACE;
            case KeyEvent.VK_TAB -> TAB;
            case KeyEvent.VK_ENTER -> ENTER;
            case KeyEvent.VK_SHIFT -> SHIFT;
            case KeyEvent.VK_CONTROL ->
                    (ctrlCommandMatch && !OSUtils.isMacOS())
                            ? CTRL_OR_COMMAND : CTRL;
            case KeyEvent.VK_META -> {
                if (OSUtils.isMacOS())
                    yield ctrlCommandMatch ? CTRL_OR_COMMAND : COMMAND;

                yield UNSUPPORTED;
            }
            case KeyEvent.VK_ALT -> ALT;
            case KeyEvent.VK_ESCAPE -> ESCAPE;
            case KeyEvent.VK_LEFT -> LEFT_ARROW;
            case KeyEvent.VK_UP -> UP_ARROW;
            case KeyEvent.VK_RIGHT -> RIGHT_ARROW;
            case KeyEvent.VK_DOWN -> DOWN_ARROW;
            case KeyEvent.VK_COMMA -> COMMA;
            case KeyEvent.VK_MINUS -> MINUS;
            case KeyEvent.VK_PERIOD -> PERIOD;
            case KeyEvent.VK_SLASH -> SLASH;
            case 49, 50, 51, 52, 53, 54, 55, 56, 57 ->
                    Key.valueOf("_" + (char) code);
            case KeyEvent.VK_SEMICOLON -> SEMICOLON;
            case KeyEvent.VK_EQUALS -> EQUALS;
            case 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78,
                    79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90 ->
                    Key.valueOf(String.valueOf((char) code));
            case 91 -> OPEN_SQUARE_BRACKET;
            case 92 -> BACKSLASH;
            case 93 -> CLOSE_SQUARE_BRACKET;
            case KeyEvent.VK_DELETE -> DELETE;
            case KeyEvent.VK_QUOTE -> APOSTROPHE;
            default -> UNSUPPORTED;
        };
    }
}
