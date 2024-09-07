package com.jordanbunke.delta_time.sprite;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UVMapping {
    private final GameImage lookup;
    private final Map<Color, Coord2D> colorCoordinateMap;

    public UVMapping(final GameImage lookup) {
        this.lookup = lookup;
        this.colorCoordinateMap = new HashMap<>();

        populateColorCoordinateMap();
    }

    private void populateColorCoordinateMap() {
        for (int x = 0; x < lookup.getWidth(); x++) {
            for (int y = 0; y < lookup.getHeight(); y++) {
                final Color sampled = lookup.getColorAt(x, y);

                if (sampled.getAlpha() > 0) {
                    if (colorCoordinateMap.containsKey(sampled))
                        GameError.send("Duplicate color \"" + sampled +
                                "\" associated with first sampled at " +
                                colorCoordinateMap.get(sampled) +
                                " was detected again at " + new Coord2D(x, y));
                    else
                        colorCoordinateMap.put(sampled, new Coord2D(x, y));
                }
            }
        }
    }

    public static GameImage replace(
            final GameImage texture,
            final GameImage map,
            final GameImage animation
    ) {
        final UVMapping uvm = new UVMapping(map);
        return uvm.replace(texture, animation);
    }

    public GameImage replace(
            final GameImage texture,
            final GameImage animation
    ) {
        if (lookup.getWidth() != animation.getWidth() ||
                lookup.getHeight() != animation.getHeight()) {
            GameError.send(
                    "The lookup net and the color net are different sizes! " +
                            "The sprite could not be composed, so the " +
                            "input texture was returned instead.");
            return texture;
        }

        final int width = texture.getWidth(),
                height = texture.getHeight();
        final GameImage replaced = new GameImage(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final Color sampled = texture.getColorAt(x, y);

                if (sampled.getAlpha() > 0) {
                    if (colorCoordinateMap.containsKey(sampled)) {
                        final Coord2D coordinate = colorCoordinateMap.get(sampled);
                        final Color lookedUp = animation
                                .getColorAt(coordinate.x, coordinate.y);
                        replaced.dot(lookedUp, x, y);
                    } else
                        replaced.dot(sampled, x, y);
                }
            }
        }

        return replaced.submit();
    }

    public static GameImage generateNaiveLookup(
            final GameImage base, final boolean horz
    ) {
        final int width = base.getWidth(), height = base.getHeight();
        final int outerDim = horz ? height : width,
                innerDim = horz ? width : height;
        final GameImage lookup = new GameImage(width, height);

        int nonTransparentCounter = 0;

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                if (base.getColorAt(x, y).getAlpha() > 0)
                    nonTransparentCounter++;

        final int totalColorsToAssign = nonTransparentCounter;

        int colorsAssigned = 0;

        for (int d0 = 0; d0 < outerDim; d0++) {
            for (int d1 = 0; d1 < innerDim; d1++) {
                final int x = horz ? d1 : d0,
                        y = horz ? d0 : d1;
                if (base.getColorAt(x, y).getAlpha() > 0) {
                    final Color assignee =
                            getNaiveColor(colorsAssigned, totalColorsToAssign);
                    lookup.dot(assignee, x, y);
                    colorsAssigned++;
                }
            }
        }

        return lookup.submit();
    }

    private static Color getNaiveColor(final int index, final int total) {
        final int possibleRGBDivisions = (int)Math.ceil(Math.pow(total, 1 / 3d));

        if (possibleRGBDivisions < 2) {
            final Color failCaseColor = new Color(255, 0, 0);
            GameError.send("Too few color divisions are possible. Returned " +
                    failCaseColor + " trivially.");
            return failCaseColor;
        }

        final int r = index % possibleRGBDivisions;
        final int g = (index / possibleRGBDivisions) % possibleRGBDivisions;
        final int b = index / (possibleRGBDivisions * possibleRGBDivisions);

        final int multiplier = 255 / (possibleRGBDivisions - 1);

        return new Color(r * multiplier, g * multiplier, b * multiplier);
    }
}
