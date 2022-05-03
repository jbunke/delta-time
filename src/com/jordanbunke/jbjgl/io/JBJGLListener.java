package com.jordanbunke.jbjgl.io;

import com.jordanbunke.jbjgl.events.*;
import com.jordanbunke.jbjgl.window.JBJGLCanvas;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class JBJGLListener implements
        KeyListener, MouseListener, MouseMotionListener, WindowListener {

    private final List<JBJGLEvent> eventList;

    private JBJGLListener(final JBJGLCanvas canvas) {
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        
        eventList = new ArrayList<>();
    }

    public static JBJGLListener create(final JBJGLCanvas canvas) {
        return new JBJGLListener(canvas);
    }

    public List<JBJGLEvent> getEventList() {
        return eventList;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        eventList.add(
                JBJGLKeyEvent.generate(e.getKeyChar(), JBJGLKeyEvent.Action.TYPE)
        );
    }

    @Override
    public void keyPressed(KeyEvent e) {
        eventList.add(
                JBJGLKeyEvent.generate(e.getKeyChar(), JBJGLKeyEvent.Action.PRESS)
        );
    }

    @Override
    public void keyReleased(KeyEvent e) {
        eventList.add(
                JBJGLKeyEvent.generate(e.getKeyChar(), JBJGLKeyEvent.Action.RELEASE)
        );
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        eventList.add(
                JBJGLMouseEvent.generate(new int[] {
                        e.getX(), e.getY()
                }, JBJGLMouseEvent.Action.CLICK)
        );
    }

    @Override
    public void mousePressed(MouseEvent e) {
        eventList.add(
                JBJGLMouseEvent.generate(new int[] {
                        e.getX(), e.getY()
                }, JBJGLMouseEvent.Action.DOWN)
        );
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        eventList.add(
                JBJGLMouseEvent.generate(new int[] {
                        e.getX(), e.getY()
                }, JBJGLMouseEvent.Action.UP)
        );
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        eventList.add(
                JBJGLMoveEvent.generate(new int[] {
                        e.getX(), e.getY()
                }, JBJGLMoveEvent.Action.ENTER)
        );
    }

    @Override
    public void mouseExited(MouseEvent e) {
        eventList.add(
                JBJGLMoveEvent.generate(new int[] {
                        e.getX(), e.getY()
                }, JBJGLMoveEvent.Action.EXIT)
        );
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        eventList.add(
                JBJGLMoveEvent.generate(new int[] {
                        e.getX(), e.getY()
                }, JBJGLMoveEvent.Action.DRAG)
        );
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        eventList.add(
                JBJGLMoveEvent.generate(new int[] {
                        e.getX(), e.getY()
                }, JBJGLMoveEvent.Action.MOVE)
        );
    }

    @Override
    public void windowOpened(WindowEvent e) {
        eventList.add(
                JBJGLWindowEvent.generate(JBJGLWindowEvent.Action.OPENED)
        );
    }

    @Override
    public void windowClosing(WindowEvent e) {
        eventList.add(
                JBJGLWindowEvent.generate(JBJGLWindowEvent.Action.CLOSING)
        );
    }

    @Override
    public void windowClosed(WindowEvent e) {
        eventList.add(
                JBJGLWindowEvent.generate(JBJGLWindowEvent.Action.CLOSED)
        );
    }

    @Override
    public void windowIconified(WindowEvent e) {
        eventList.add(
                JBJGLWindowEvent.generate(JBJGLWindowEvent.Action.ICONIFIED)
        );
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        eventList.add(
                JBJGLWindowEvent.generate(JBJGLWindowEvent.Action.DEICONIFIED)
        );
    }

    @Override
    public void windowActivated(WindowEvent e) {
        eventList.add(
                JBJGLWindowEvent.generate(JBJGLWindowEvent.Action.ACTIVATED)
        );
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        eventList.add(
                JBJGLWindowEvent.generate(JBJGLWindowEvent.Action.DEACTIVATED)
        );
    }
}
