package com.jordanbunke.delta_time.io;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.events.*;
import com.jordanbunke.delta_time.utility.math.Coord2D;
import com.jordanbunke.delta_time.window.GameCanvas;

import java.awt.event.*;
import java.util.*;
import java.util.stream.Collectors;

public class InputEventLogger implements
        KeyListener, ComponentListener, WindowListener,
        MouseListener, MouseMotionListener, MouseWheelListener {

    public static final double DEFAULT_SCALE = 1.0;

    private final List<InputTask> tasks;

    private final List<GameEvent> eventList;
    private final Map<Key, Boolean> characterPressedStatusMap;
    private Coord2D mousePosition;

    private double scaleUpRatioX, scaleUpRatioY;

    private InputEventLogger(final GameCanvas canvas) {
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseWheelListener(this);

        tasks = new ArrayList<>();

        eventList = new ArrayList<>();
        characterPressedStatusMap = new HashMap<>();
        mousePosition = new Coord2D();

        scaleUpRatioX = DEFAULT_SCALE;
        scaleUpRatioY = DEFAULT_SCALE;
    }

    public static InputEventLogger create(final GameCanvas canvas) {
        return new InputEventLogger(canvas);
    }

    public void checkForMatchingKeyStroke(
            final GameKeyEvent toMatch, final Runnable behaviour
    ) {
        List<GameEvent> eventList = getUnprocessedEvents();

        for (GameEvent event : eventList) {
            if (event.isProcessed())
                continue;

            if (event.equals(toMatch)) {
                behaviour.run();
                event.markAsProcessed();
            }
        }
    }

    public void checkForMatchingKeyStroke(
            final Key key, final GameKeyEvent.Action action,
            final Runnable behaviour
    ) {
        GameKeyEvent toMatch = GameKeyEvent.newKeyStroke(key, action);
        checkForMatchingKeyStroke(toMatch, behaviour);
    }

    public List<GameEvent> getUnprocessedEvents() {
        try {
            return new ArrayList<>(eventList).stream()
                    .filter(x -> x!= null && !x.isProcessed()).collect(Collectors.toList());
        } catch (ConcurrentModificationException e) {
            GameError.send(
                    "Attempted to fetch unprocessed input events as one occurred. Events were not returned."
            );
            return new ArrayList<>();
        }
    }

    public List<GameEvent> getAllEvents() {
        return List.copyOf(eventList);
    }

    public void clearEventList() {
        eventList.clear();
    }

    public void unpressAllKeys() {
        characterPressedStatusMap.clear();
    }

    public void addTask(final InputTask task) {
        tasks.add(task);
    }

    public void addTasks(final Collection<InputTask> tasks) {
        this.tasks.addAll(tasks);
    }

    public void removeTask(final InputTask task) {
        tasks.remove(task);
    }

    public void clearTasks() {
        tasks.clear();
    }

    private void updateMousePosition(final MouseEvent e) {
        mousePosition = new Coord2D(e.getX(), e.getY());
    }

    private boolean isScaledUp() {
        return scaleUpRatioX != DEFAULT_SCALE || scaleUpRatioY != DEFAULT_SCALE;
    }

    public Coord2D getAdjustedMousePosition() {
        if (isScaledUp())
            return new Coord2D((int)(mousePosition.x / scaleUpRatioX),
                    (int)(mousePosition.y / scaleUpRatioY));

        return mousePosition;
    }

    public Coord2D getRawMousePosition() {
        return mousePosition;
    }

    public boolean isPressed(final Key key) {
        return characterPressedStatusMap.getOrDefault(key, false);
    }

    private void handleKeyEvent(final GameKeyEvent event) {
        // check in tasks
        for (InputTask task : tasks)
            if (task.getEvent().equals(event)) {
                task.execute();
                return;
            }

        // add to list of unprocessed events iff there is no matching task
        if (event != null)
            eventList.add(event);
    }

    @Override
    public void keyTyped(final KeyEvent e) {
        char character = e.getKeyChar();

        handleKeyEvent(GameKeyEvent.newTypedKey(character));
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        Key key = Key.fromKeyEvent(e);

        if (characterPressedStatusMap.containsKey(key) && characterPressedStatusMap.get(key))
            return;

        characterPressedStatusMap.put(key, true);
        handleKeyEvent(
                GameKeyEvent.newKeyStroke(key, GameKeyEvent.Action.PRESS)
        );
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        Key key = Key.fromKeyEvent(e);

        if (characterPressedStatusMap.containsKey(key) && !characterPressedStatusMap.get(key))
            return;

        characterPressedStatusMap.put(key, false);
        handleKeyEvent(GameKeyEvent.newKeyStroke(key, GameKeyEvent.Action.RELEASE));
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        updateMousePosition(e);
        eventList.add(new GameMouseEvent(getAdjustedMousePosition(),
                GameMouseEvent.Action.CLICK, GameMouseEvent.Button.fromInt(e.getButton())));
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        updateMousePosition(e);
        eventList.add(new GameMouseEvent(getAdjustedMousePosition(),
                GameMouseEvent.Action.DOWN, GameMouseEvent.Button.fromInt(e.getButton())));
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        updateMousePosition(e);
        eventList.add(new GameMouseEvent(getAdjustedMousePosition(),
                GameMouseEvent.Action.UP, GameMouseEvent.Button.fromInt(e.getButton())));
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        updateMousePosition(e);
        eventList.add(new GameMouseMoveEvent(getAdjustedMousePosition(),
                GameMouseMoveEvent.Action.ENTER));
    }

    @Override
    public void mouseExited(final MouseEvent e) {
        updateMousePosition(e);
        eventList.add(new GameMouseMoveEvent(getAdjustedMousePosition(),
                GameMouseMoveEvent.Action.EXIT));
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        updateMousePosition(e);
        eventList.add(new GameMouseMoveEvent(getAdjustedMousePosition(),
                GameMouseMoveEvent.Action.DRAG));
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        updateMousePosition(e);
        eventList.add(new GameMouseMoveEvent(getAdjustedMousePosition(),
                GameMouseMoveEvent.Action.MOVE));
    }

    @Override
    public void mouseWheelMoved(final MouseWheelEvent e) {
        updateMousePosition(e);
        eventList.add(new GameMouseScrollEvent(e.getWheelRotation()));
    }

    @Override
    public void windowOpened(final WindowEvent e) {
        eventList.add(new GameWindowEvent(GameWindowEvent.Action.OPENED));
    }

    @Override
    public void windowClosing(final WindowEvent e) {
        eventList.add(new GameWindowEvent(GameWindowEvent.Action.CLOSING));
    }

    @Override
    public void windowClosed(final WindowEvent e) {
        eventList.add(new GameWindowEvent(GameWindowEvent.Action.CLOSED));
    }

    @Override
    public void windowIconified(final WindowEvent e) {
        eventList.add(new GameWindowEvent(GameWindowEvent.Action.ICONIFIED));
    }

    @Override
    public void windowDeiconified(final WindowEvent e) {
        eventList.add(new GameWindowEvent(GameWindowEvent.Action.DEICONIFIED));
    }

    @Override
    public void windowActivated(final WindowEvent e) {
        eventList.add(new GameWindowEvent(GameWindowEvent.Action.ACTIVATED));
    }

    @Override
    public void componentResized(final ComponentEvent e) {
        eventList.add(new WindowResizedEvent(
                e.getComponent().getWidth(), e.getComponent().getHeight()));
    }

    @Override
    public void componentMoved(final ComponentEvent e) {
        eventList.add(new WindowMovedEvent(
                e.getComponent().getX(), e.getComponent().getY()));
    }

    @Override
    public void componentShown(final ComponentEvent e) {}

    @Override
    public void componentHidden(final ComponentEvent e) {}

    @Override
    public void windowDeactivated(final WindowEvent e) {
        eventList.add(new GameWindowEvent(GameWindowEvent.Action.DEACTIVATED));
    }

    public void setScaleUpRatioX(final double scaleUpRatioX) {
        this.scaleUpRatioX = scaleUpRatioX;
    }

    public void setScaleUpRatioY(final double scaleUpRatioY) {
        this.scaleUpRatioY = scaleUpRatioY;
    }
}
