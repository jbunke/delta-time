package com.jordanbunke.delta_time.io;

import com.jordanbunke.delta_time.image.GameImage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class ClipboardUtils {
    private static Clipboard get() {
        return Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public static GameImage getImage() {
        final Transferable content = getContent();

        if (content == null || !content.isDataFlavorSupported(DataFlavor.imageFlavor))
            return null;

        try {
            final Image img = (Image) content.getTransferData(DataFlavor.imageFlavor);

            final int w = img.getWidth(null), h = img.getHeight(null);

            if (w <= 0 || h <= 0)
                return null;

            final GameImage result = new GameImage(w, h);
            result.draw(img);

            return result.submit();
        } catch (Exception e) {
            return null;
        }
    }

    public static Transferable getContent() {
        return get().getContents(null);
    }

    public static void setContent(final ClipboardData data) {
        setContent(data, null);
    }

    public static void setContent(
            final ClipboardData data, final ClipboardOwner owner
    ) {
        get().setContents(data, owner);
    }
}
