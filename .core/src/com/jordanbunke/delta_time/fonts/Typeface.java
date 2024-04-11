package com.jordanbunke.delta_time.fonts;

public record Typeface(String name, Font regular, Font bold, Font italics) {
    public Typeface(
            final String name, final Font regular,
            final Font bold, final Font italics
    ) {
        this.name = name;

        this.regular = regular;
        this.bold = bold == null ? regular : bold;
        this.italics = italics == null ? regular : italics;
    }
}
