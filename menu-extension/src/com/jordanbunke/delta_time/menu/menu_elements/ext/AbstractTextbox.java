package com.jordanbunke.delta_time.menu.menu_elements.ext;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.events.GameEvent;
import com.jordanbunke.delta_time.events.GameKeyEvent;
import com.jordanbunke.delta_time.events.GameMouseEvent;
import com.jordanbunke.delta_time.events.Key;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menu.menu_elements.button.MenuButtonStub;
import com.jordanbunke.delta_time.menu.menu_elements.ext.drawing_functions.TextboxDrawingFunction;
import com.jordanbunke.delta_time.utility.DeltaTimeGlobal;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractTextbox extends MenuButtonStub {
    // matrix indices
    private static final int I_TRUE = 1;
    private static final int TRUTH_TABLE_DIM = 2;

    private static String typingCode = "typing"; // default

    private String text, lastText, prefix, suffix;
    private int cursorIndex, lastCursorIndex,
            selectionIndex, lastSelectionIndex;
    private boolean typing, valid;

    private final Supplier<String> prefixGetter, suffixGetter;
    private final TextboxDrawingFunction fDraw;
    private final Function<String, Boolean> textValidator;
    private final Consumer<String> setter;
    private final int maxLength;

    private final GameImage[][][] imageMatrix =
            new GameImage[TRUTH_TABLE_DIM][TRUTH_TABLE_DIM][TRUTH_TABLE_DIM];
    private GameImage current;

    public static void setTypingCode(final String typingCode) {
        AbstractTextbox.typingCode = typingCode;
    }

    public AbstractTextbox(
            final Coord2D position, final Bounds2D dimensions, final Anchor anchor,
            final Supplier<String> prefixGetter, final String initialText,
            final Supplier<String> suffixGetter,
            final Function<String, Boolean> textValidator,
            final Consumer<String> setter,
            final TextboxDrawingFunction fDraw, final int maxLength
    ) {
        super(position, dimensions, anchor, true);

        this.prefixGetter = prefixGetter;
        this.suffixGetter = suffixGetter;

        text = initialText;
        cursorIndex = text.length();
        selectionIndex = cursorIndex;
        typing = false;

        this.fDraw = fDraw;
        this.textValidator = textValidator;
        this.setter = setter;
        this.maxLength = maxLength;

        validate();
        updateAssets();
        determineCurrent();
    }

    protected void updateAssets() {
        prefix = prefixGetter.get();
        suffix = suffixGetter.get();

        for (int iValidity = 0; iValidity <= I_TRUE; iValidity++)
            for (int iHighlighting = 0; iHighlighting <= I_TRUE; iHighlighting++)
                for (int iTyping = 0; iTyping <= I_TRUE; iTyping++)
                    imageMatrix[iValidity][iHighlighting][iTyping] = fDraw.draw(
                            new Coord2D(getWidth(), getHeight()),
                            prefix, text, suffix,
                            cursorIndex, selectionIndex,
                            iValidity == I_TRUE, iHighlighting == I_TRUE,
                            iTyping == I_TRUE);

        lastText = text;
        lastCursorIndex = cursorIndex;
        lastSelectionIndex = selectionIndex;
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        super.process(eventLogger);
        processClickOff(eventLogger);
        processTyping(eventLogger);
    }

    private void processClickOff(final InputEventLogger eventLogger) {
        if (isTyping() && !isHighlighted()) {
            final java.util.List<GameEvent> unprocessed = eventLogger.getUnprocessedEvents();

            for (GameEvent e : unprocessed)
                if (e instanceof GameMouseEvent me && me.matchesAction(GameMouseEvent.Action.DOWN)) {
                    // DO NOT MARK AS PROCESSED!

                    typing = false;
                    clickedOffBehaviour();
                    DeltaTimeGlobal.setStatus(typingCode, false);
                    return;
                }
        }
    }

    private void processTyping(final InputEventLogger eventLogger) {
        final int DELETE = 127, LOWEST_PRINTABLE = 32;

        final String textWas = text;

        if (isTyping()) {
            final List<GameEvent> unprocessed = eventLogger.getUnprocessedEvents();

            for (GameEvent e : unprocessed)
                if (e instanceof GameKeyEvent keyEvent) {
                    if (keyEvent.matchesAction(GameKeyEvent.Action.PRESS)) {
                        switch (keyEvent.key) {
                            // No longer setting button input
                            case ENTER, TAB, ESCAPE -> {
                                keyEvent.markAsProcessed();

                                typing = false;
                                clickedOffBehaviour();
                                DeltaTimeGlobal.setStatus(typingCode, false);
                            }
                            // Remove character before cursor from input string
                            case BACKSPACE -> {
                                keyEvent.markAsProcessed();

                                if (hasSelection())
                                    collapseSelection();
                                else if (cursorIndex > 0) {
                                    text = text.substring(0, cursorIndex - 1) +
                                            text.substring(cursorIndex);
                                    cursorIndex--;
                                    selectionIndex = cursorIndex;
                                }

                                DeltaTimeGlobal.setStatus(typingCode, true);
                            }
                            // Removes character after cursor from input string
                            case DELETE -> {
                                keyEvent.markAsProcessed();

                                if (hasSelection())
                                    collapseSelection();
                                else if (cursorIndex < text.length()) {
                                    text = text.substring(0, cursorIndex) +
                                            text.substring(cursorIndex + 1);
                                }

                                DeltaTimeGlobal.setStatus(typingCode, true);
                            }
                            // moves cursor index back if possible
                            case LEFT_ARROW ->  {
                                keyEvent.markAsProcessed();

                                if (eventLogger.isPressed(Key.SHIFT)) {
                                    if (cursorIndex > 0)
                                        cursorIndex--;
                                } else {
                                    if (hasSelection())
                                        cursorIndex = leftIndex();
                                    else if (cursorIndex > 0)
                                        cursorIndex--;

                                    selectionIndex = cursorIndex;
                                }

                                DeltaTimeGlobal.setStatus(typingCode, true);
                            }
                            // moves cursor index forwards if possible
                            case RIGHT_ARROW -> {
                                keyEvent.markAsProcessed();

                                if (eventLogger.isPressed(Key.SHIFT)) {
                                    if (cursorIndex < text.length())
                                        cursorIndex++;
                                } else {
                                    if (hasSelection())
                                        cursorIndex = rightIndex();
                                    else if (cursorIndex < text.length())
                                        cursorIndex++;

                                    selectionIndex = cursorIndex;
                                }

                                DeltaTimeGlobal.setStatus(typingCode, true);
                            }
                        }

                        attemptAccept();
                    } else if (keyEvent.matchesAction(GameKeyEvent.Action.TYPE)) {
                        keyEvent.markAsProcessed();

                        final char c = keyEvent.character;

                        if (c == DELETE || c < LOWEST_PRINTABLE ||
                                text.length() + 1 - (rightIndex() - leftIndex()) > maxLength)
                            continue;

                        if (hasSelection())
                            collapseSelection();

                        text = text.substring(0, cursorIndex) + c +
                                text.substring(cursorIndex);
                        cursorIndex++;
                        selectionIndex = cursorIndex;

                        attemptAccept();
                        DeltaTimeGlobal.setStatus(typingCode, true);
                    }
                }
        }

        if (!text.equals(textWas))
            validate();
    }

    @Override
    public void execute() {
        typing = !typing;

        if (isTyping())
            clickedOnBehaviour();
        else
            clickedOffBehaviour();

        DeltaTimeGlobal.setStatus(typingCode, typing);
    }

    private void clickedOnBehaviour() {
        cursorIndex = text.length();
        selectionIndex = 0;
    }

    private void clickedOffBehaviour() {
        attemptAccept();

        cursorIndex = text.length();
        selectionIndex = cursorIndex;
    }

    private void attemptAccept() {
        validate();

        if (valid)
            setter.accept(text);
    }

    @Override
    public void update(final double deltaTime) {
        validate();

        if (!text.equals(lastText) || cursorIndex != lastCursorIndex ||
                selectionIndex != lastSelectionIndex ||
                !prefix.equals(prefixGetter.get()) ||
                !suffix.equals(suffixGetter.get()))
            updateAssets();

        determineCurrent();
    }

    @Override
    public void render(final GameImage canvas) {
        draw(current, canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {

    }

    private void determineCurrent() {
        current = imageMatrix[toIndex(isValid())]
                [toIndex(isHighlighted())][toIndex(isTyping())];
    }

    private int toIndex(final boolean property) {
        return property ? I_TRUE : (1 - I_TRUE);
    }

    public void validate() {
        valid = textValidator.apply(text);
    }

    public boolean isValid() {
        return valid;
    }

    public boolean isTyping() {
        return typing;
    }

    private int leftIndex() {
        return Math.min(selectionIndex, cursorIndex);
    }

    private int rightIndex() {
        return Math.max(selectionIndex, cursorIndex);
    }

    private boolean hasSelection() {
        return selectionIndex != cursorIndex;
    }

    private void collapseSelection() {
        text = text.substring(0, leftIndex()) +
                text.substring(rightIndex());
        cursorIndex = leftIndex();
        selectionIndex = cursorIndex;
    }

    public void setText(final String text) {
        this.text = text.length() > maxLength
                ? text.substring(0, maxLength)
                : text;
        cursorIndex = this.text.length();
        selectionIndex = cursorIndex;

        validate();
    }
}
