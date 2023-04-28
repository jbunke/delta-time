package com.jordanbunke.jbjgl;

import com.jordanbunke.jbjgl.error.JBJGLError;
import com.jordanbunke.jbjgl.utility.JBJGLGlobal;

import javax.swing.*;
import java.nio.file.Paths;

public class JBJGLOnStartup {
    public static void run() {
        startUpMessages();
        setLookAndFeel();
    }

    private static void startUpMessages() {
        JBJGLGlobal.printMessageToJBJGLChannel(Constants.TITLE + " v" + Constants.VERSION +
                " | running from directory: " + Paths.get("").toAbsolutePath());
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JBJGLError.send("Something went wrong setting the look and feel");
        }
    }
}
