package com.jordanbunke.delta_time.fonts;

import com.jordanbunke.delta_time.image.GameImage;

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
            final Path folder, final boolean isResource,
            final String baseName,
            final boolean hasLatinExtended
    ) {
        return Font.loadFromSource(folder, isResource,
                baseName, hasLatinExtended,
                whitespaceBreadthMultiplier, pixelSpacing,
                smoothResizing, charSpecificSpacing);
    }

    public Font build(final FontSources sources) {
        return build(sources.ascii(), sources.latinExtended());
    }

    public Font build(final GameImage ascii, final GameImage latinExtended) {
        return Font.loadFromImages(ascii, latinExtended,
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
