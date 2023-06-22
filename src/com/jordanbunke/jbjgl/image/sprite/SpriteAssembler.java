package com.jordanbunke.jbjgl.image.sprite;

import com.jordanbunke.jbjgl.error.GameError;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.image.ImageProcessing;
import com.jordanbunke.jbjgl.image.sprite.constituents.SpriteConstituent;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;

public class SpriteAssembler<T, R> {
    private static final Function<Color, Boolean> IDENTITY_MASK = x -> false;

    private final Map<T, SpriteConstituent<R>> layerMap;
    private final Map<T, Boolean> layerIsEnabledMap;
    private final Map<T, Function<Color, Boolean>> maskFunctionMap;
    private final List<T> layerIDs;
    private final Map<T, T> layerMaskMap;

    private final int singleSpriteWidth, singleSpriteHeight;

    public SpriteAssembler(final int singleSpriteWidth, final int singleSpriteHeight) {
        this(singleSpriteWidth, singleSpriteHeight, new ArrayList<>());
    }

    @SafeVarargs
    public SpriteAssembler(
            final int singleSpriteWidth, final int singleSpriteHeight,
            final List<T> layerIDs, final SpriteConstituent<R>... layers
    ) {
        this.singleSpriteWidth = singleSpriteWidth;
        this.singleSpriteHeight = singleSpriteHeight;

        final int size = Math.min(layerIDs.size(), layers.length);

        this.layerIDs = new ArrayList<>();
        this.layerMap = new HashMap<>();
        this.layerIsEnabledMap = new HashMap<>();
        this.maskFunctionMap = new HashMap<>();
        this.layerMaskMap = new HashMap<>();

        for (int i = 0; i < size; i++) {
            final T layerID = layerIDs.get(i);

            if (this.layerIDs.contains(layerID))
                continue;

            this.layerIDs.add(layerID);
            this.layerIsEnabledMap.put(layerID, true);
            this.layerMap.put(layerID, layers[i]);
            this.maskFunctionMap.put(layerID, IDENTITY_MASK);
        }
    }

    public GameImage assembleSprite(final R spriteID) {
        final GameImage sprite = new GameImage(singleSpriteWidth, singleSpriteHeight);

        for (T layerID : layerIDs)
            if (layerIsEnabledMap.containsKey(layerID) && layerIsEnabledMap.get(layerID)) {
                GameImage layer = layerMap.get(layerID).getSprite(spriteID);

                if (layerMaskMap.containsKey(layerID)) {
                    final T maskID = layerMaskMap.get(layerID);

                    final boolean maskIsLayer = layerMap.containsKey(maskID),
                            maskIsEnabled = layerIsEnabledMap.containsKey(maskID) &&
                                    layerIsEnabledMap.get(maskID),
                            notCalledOnItself = !maskID.equals(layerID),
                            notTrivial = maskFunctionMap.containsKey(maskID) &&
                                    !maskFunctionMap.get(maskID).equals(IDENTITY_MASK);

                    if (maskIsLayer && maskIsEnabled && notCalledOnItself && notTrivial) {
                        final Function<Color, Boolean> maskFunction = maskFunctionMap.get(maskID);
                        final GameImage maskImage = layerMap.get(maskID).getSprite(spriteID);

                        final int width = layer.getWidth(), height = layer.getHeight();
                        final GameImage filteredLayer = new GameImage(width, height);

                        for (int x = 0; x < width; x++) {
                            for (int y = 0; y < height; y++) {
                                final Color sample = ImageProcessing.colorAtPixel(maskImage, x, y),
                                        place = ImageProcessing.colorAtPixel(layer, x, y);

                                if (!maskFunction.apply(sample))
                                    filteredLayer.dot(place, x, y);
                            }
                        }

                        layer = filteredLayer.submit();
                    }
                }

                sprite.draw(layer.submit());
            }

        return sprite.submit();
    }

    public void setLayerEnabledStatus(final T layerID, final boolean isEnabled) {
        if (layerIsEnabledMap.containsKey(layerID))
            layerIsEnabledMap.put(layerID, isEnabled);
    }

    public void enableLayer(final T layerID) {
        setLayerEnabledStatus(layerID, true);
    }

    public void disableLayer(final T layerID) {
        setLayerEnabledStatus(layerID, false);
    }

    public boolean removeLayer(final T layerID) {
        if (layerIDs.contains(layerID)) {
            layerIDs.remove(layerID);
            layerIsEnabledMap.remove(layerID);
            layerMap.remove(layerID);

            return true;
        }

        return false;
    }

    public void addLayer(
            final int index, final T layerID,
            final SpriteConstituent<R> layer
    ) {
        if (layerIDs.contains(layerID)) {
            GameError.send("A layer for this layer ID (\"" + layerID +
                    "\") is already present in the sprite assembler.");
            return;
        }

        layerIDs.add(index, layerID);
        layerIsEnabledMap.put(layerID, true);
        layerMap.put(layerID, layer);
    }

    public void addLayer(
            final T layerID, final SpriteConstituent<R> layer
    ) {
        addLayer(layerIDs.size(), layerID, layer);
    }

    public void addMask(
            final T maskID, final Function<Color, Boolean> maskFunction,
            final SpriteConstituent<R> mask, final T appliedToLayerID
    ) {
        if (!layerIDs.contains(appliedToLayerID)) {
            GameError.send(
                    "(caution) The layer this mask filter is trying to apply itself to (\"" +
                            appliedToLayerID + "\") is not in the assembler yet."
            );
        }

        layerMap.put(maskID, mask);
        maskFunctionMap.put(maskID, maskFunction);
        layerMaskMap.put(appliedToLayerID, maskID);
        layerIsEnabledMap.put(maskID, true);
    }

    public boolean removeMask(final T maskID) {
        if (maskFunctionMap.containsKey(maskID)) {
            layerIsEnabledMap.remove(maskID);
            layerMap.remove(maskID);
            maskFunctionMap.remove(maskID);

            final Set<T> layerIDs = new HashSet<>(layerMaskMap.keySet());

            for (T layerID : layerIDs)
                if (layerMaskMap.containsKey(layerID) && layerMaskMap.get(layerID).equals(maskID))
                    layerMaskMap.remove(layerID);

            return true;
        }

        return false;
    }
}
