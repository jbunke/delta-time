package com.jordanbunke.delta_time.image.sprite;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.image.ImageProcessing;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SpriteComposer {
    private final GameImage colorNet;
    private final Map<Color, Coord2D> colorCoordinateMap;

    public SpriteComposer(final GameImage colorNet) {
        this.colorNet = colorNet;
        this.colorCoordinateMap = new HashMap<>();

        populateColorCoordinateMap();
    }

    private void populateColorCoordinateMap() {
        for (int x = 0; x < colorNet.getWidth(); x++) {
            for (int y = 0; y < colorNet.getHeight(); y++) {
                final Color sampled = ImageProcessing.colorAtPixel(colorNet, x, y);

                if (sampled.getAlpha() > 0) {
                    if (colorCoordinateMap.containsKey(sampled)) {
                        GameError.send("Duplicate color \"" + sampled +
                                "\" detected again at " +
                                new Coord2D(x, y));
                    } else {
                        colorCoordinateMap.put(sampled, new Coord2D(x, y));
                    }
                }
            }
        }
    }

    public static GameImage compose(
            final GameImage overlayTexture, final GameImage colorNet, final GameImage lookupNet
    ) {
        final SpriteComposer composer = new SpriteComposer(colorNet);
        return composer.compose(overlayTexture, lookupNet);
    }

    public GameImage compose(
            final GameImage overlayTexture, final GameImage lookupNet
    ) {
        if (colorNet.getWidth() != lookupNet.getWidth() ||
                colorNet.getHeight() != lookupNet.getHeight()) {
            GameError.send(
                    "The lookup net and the color net are different sizes!" +
                            "The sprite could not be composed and the " +
                            "input was texture was returned instead."
            );
            return overlayTexture;
        }

        final int width = overlayTexture.getWidth(),
                height = overlayTexture.getHeight();
        final GameImage composition = new GameImage(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final Color sampled = ImageProcessing.colorAtPixel(overlayTexture, x, y);

                if (sampled.getAlpha() > 0) {
                    if (colorCoordinateMap.containsKey(sampled)) {
                        final Coord2D coordinate = colorCoordinateMap.get(sampled);
                        final Color lookedUp = ImageProcessing
                                .colorAtPixel(lookupNet, coordinate.x, coordinate.y);
                        composition.dot(lookedUp, x, y);
                    } else {
                        GameError.send("Did not find \"" + sampled +
                                "\" in the color coordinate map. " +
                                "Passed color sampled from overlay " +
                                "texture back to the result at " +
                                new Coord2D(x, y) + ".");
                        composition.dot(sampled, x, y);
                    }
                }
            }
        }

        return composition.submit();
    }

    public static GameImage generateNaiveColorNet(final GameImage lookupNet, final boolean stripedVertically) {
        final int width = lookupNet.getWidth(), height = lookupNet.getHeight();
        final int outerDim = stripedVertically ? width : height,
                innerDim = stripedVertically ? height : width;
        final GameImage colorNet = new GameImage(width, height);

        int nonTransparentCounter = 0;

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                if (ImageProcessing.colorAtPixel(lookupNet, x, y).getAlpha() > 0)
                    nonTransparentCounter++;

        final int colorsInNet = nonTransparentCounter;

        int colorsAssigned = 0;

        for (int d0 = 0; d0 < outerDim; d0++) {
            for (int d1 = 0; d1 < innerDim; d1++) {
                final int x = stripedVertically ? d0 : d1,
                        y = stripedVertically ? d1 : d0;
                if (ImageProcessing.colorAtPixel(lookupNet, x, y).getAlpha() > 0) {
                    final Color assignee = getNaiveColor(colorsAssigned, colorsInNet);
                    colorNet.dot(assignee, x, y);
                    colorsAssigned++;
                }
            }
        }

        return colorNet.submit();
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
