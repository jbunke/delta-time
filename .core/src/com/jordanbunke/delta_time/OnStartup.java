package com.jordanbunke.delta_time;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.io.FileIO;
import com.jordanbunke.delta_time.utility.DeltaTimeGlobal;

import javax.swing.*;
import java.nio.file.Paths;

public class OnStartup {
    public static void run() {
        startUpMessages();
        setLookAndFeel();
        FileIO.reinitializeDialog();
    }

    private static void startUpMessages() {
        DeltaTimeGlobal.print(About.TITLE + " v" + About.VERSION +
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
