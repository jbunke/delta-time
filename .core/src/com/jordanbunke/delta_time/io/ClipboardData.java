package com.jordanbunke.delta_time.io;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public record ClipboardData(Object object, DataFlavor flavor) implements Transferable {
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{flavor};
    }

    @Override
    public boolean isDataFlavorSupported(final DataFlavor flavor) {
        return this.flavor.equals(flavor);
    }

    @Override
    public Object getTransferData(final DataFlavor flavor) {
        return isDataFlavorSupported(flavor) ? object : null;
    }
}
