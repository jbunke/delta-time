package com.jordanbunke.jbjgl.window;

import com.jordanbunke.jbjgl.error.JBJGLError;

import javax.swing.*;

public class JBJGLBoilerplate {
    public static void run() {
        setLookAndFeel();
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JBJGLError.send("Something went wrong setting the look and feel");
        }
    }
}
