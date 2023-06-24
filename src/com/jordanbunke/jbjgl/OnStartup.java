package com.jordanbunke.jbjgl;

import com.jordanbunke.jbjgl.error.GameError;
import com.jordanbunke.jbjgl.utility.JBJGLGlobal;

import javax.swing.*;
import java.nio.file.Paths;

public class OnStartup {
    public static void run() {
        startUpMessages();
        setLookAndFeel();
    }

    private static void startUpMessages() {
        JBJGLGlobal.print(Constants.TITLE + " v" + Constants.VERSION +
                " | running from directory: " + Paths.get("").toAbsolutePath());
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            GameError.send("Something went wrong setting the look and feel");
        }
    }
}