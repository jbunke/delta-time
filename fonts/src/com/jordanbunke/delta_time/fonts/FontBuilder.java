package com.jordanbunke.delta_time.fonts;

import java.nio.file.Path;

public final class FontBuilder {
    private boolean charSpecificSpacing, smoothResizing;
    private int pixelSpacing;
    private double whitespaceBreadthMultiplier;

    public FontBuilder() {
        this.whitespaceBreadthMultiplier = FontConstants.DEF_WHITESPACE_MULT;
        this.pixelSpacing = FontConstants.DEF_PIXEL_SPACING;
        this.charSpecificSpacing = FontConstants.DEF_CHAR_SPECIFIC_SPACING;
        this.smoothResizing = FontConstants.DEF_SMOOTH_RESIZING;
    }

    public Font build(final String fontCode) {
        return DeltaTimeFonts.buildFontFromCode(fontCode,
                whitespaceBreadthMultiplier, pixelSpacing,
                smoothResizing, charSpecificSpacing);
    }

    public Font build(
            final Path folder, final boolean resource,
            final String baseName
    ) {
        return Font.loadFromFiles(folder, resource, baseName,
                whitespaceBreadthMultiplier, pixelSpacing,
                smoothResizing, charSpecificSpacing);
    }

    public Font build(final FontSources sources) {
        return Font.loadFromFontSources(sources,
                whitespaceBreadthMultiplier, pixelSpacing,
                smoothResizing, charSpecificSpacing);
    }

    public FontBuilder setCharSpecificSpacing(final boolean charSpecificSpacing) {
        this.charSpecificSpacing = charSpecificSpacing;
        return this;
    }

    public FontBuilder setWhitespaceBreadthMultiplier(
            final double whitespaceBreadthMultiplier) {
        this.whitespaceBreadthMultiplier = whitespaceBreadthMultiplier;
        return this;
    }

    public FontBuilder setPixelSpacing(final int pixelSpacing) {
        this.pixelSpacing = pixelSpacing;
        return this;
    }

    public FontBuilder setSmoothResizing(final boolean smoothResizing) {
        this.smoothResizing = smoothResizing;
        return this;
    }
}
