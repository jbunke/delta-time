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
    private static final Function<Color, Boolean> TRIVIAL_FILTER = x -> false;
    private static final Function<Color, Color> IDENTITY_FILTER = x -> x;

    private final Map<T, SpriteConstituent<R>> layerMap;
    private final Map<T, Boolean> layerIsEnabledMap;
    private final Map<T, Function<Color, Boolean>> maskFunctionMap;
    private final Map<T, Function<Color, Color>> filterFunctionMap;
    private final List<T> layerIDs;
    private final Map<T, T> layerMaskMap, layerFilterMap;

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

        this.filterFunctionMap = new HashMap<>();
        this.layerFilterMap = new HashMap<>();

        for (int i = 0; i < size; i++) {
            final T layerID = layerIDs.get(i);

            if (this.layerIDs.contains(layerID))
                continue;

            this.layerIDs.add(layerID);
            this.layerIsEnabledMap.put(layerID, true);
            this.layerMap.put(layerID, layers[i]);
        }
    }

    public GameImage assembleSprite(final R spriteID) {
        final GameImage sprite = new GameImage(singleSpriteWidth, singleSpriteHeight);

        for (T layerID : layerIDs)
            if (layerIsEnabledMap.containsKey(layerID) && layerIsEnabledMap.get(layerID)) {
                // gets sprite from constituent
                GameImage layer = layerMap.get(layerID).getSprite(spriteID);

                // filter application
                if (layerFilterMap.containsKey(layerID)) {
                    final T filterID = layerFilterMap.get(layerID);

                    final boolean filterIsEnabled = layerIsEnabledMap.containsKey(filterID) &&
                                    layerIsEnabledMap.get(filterID),
                            notCalledOnItself = !filterID.equals(layerID),
                            notTrivial = filterFunctionMap.containsKey(filterID) &&
                                    !filterFunctionMap.get(filterID).equals(IDENTITY_FILTER);

                    if (filterIsEnabled && notCalledOnItself && notTrivial) {
                        final Function<Color, Color> filterFunction = filterFunctionMap.get(filterID);

                        final int width = layer.getWidth(), height = layer.getHeight();
                        final GameImage filteredLayer = new GameImage(width, height);

                        for (int x = 0; x < width; x++) {
                            for (int y = 0; y < height; y++) {
                                final Color sample = ImageProcessing.colorAtPixel(layer, x, y);
                                filteredLayer.dot(filterFunction.apply(sample), x, y);
                            }
                        }

                        layer = filteredLayer.submit();
                    }
                }

                // mask application
                if (layerMaskMap.containsKey(layerID)) {
                    final T maskID = layerMaskMap.get(layerID);

                    final boolean maskIsALayer = layerMap.containsKey(maskID),
                            maskIsEnabled = layerIsEnabledMap.containsKey(maskID) &&
                                    layerIsEnabledMap.get(maskID),
                            notCalledOnItself = !maskID.equals(layerID),
                            notTrivial = maskFunctionMap.containsKey(maskID) &&
                                    !maskFunctionMap.get(maskID).equals(TRIVIAL_FILTER);

                    if (maskIsALayer && maskIsEnabled && notCalledOnItself && notTrivial) {
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

        return removeMask(layerID) || removeFilter(layerID);
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

    public boolean removeFilter(final T filterID) {
        if (filterFunctionMap.containsKey(filterID)) {
            layerIsEnabledMap.remove(filterID);
            layerMap.remove(filterID);
            filterFunctionMap.remove(filterID);

            final Set<T> layerIDs = new HashSet<>(layerFilterMap.keySet());

            for (T layerID : layerIDs)
                if (layerFilterMap.containsKey(layerID) && layerFilterMap.get(layerID).equals(filterID))
                    layerFilterMap.remove(layerID);

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
                    "(caution) The layer this mask is trying to apply itself to (\"" +
                            appliedToLayerID + "\") is not in the assembler yet."
            );
        }

        layerMap.put(maskID, mask);
        maskFunctionMap.put(maskID, maskFunction);
        layerMaskMap.put(appliedToLayerID, maskID);
        layerIsEnabledMap.put(maskID, true);
    }

    public void addMask(
            final T maskID, final SpriteConstituent<R> mask, final T appliedToLayerID
    ) {
        addMask(maskID, x -> x.getAlpha() > 0, mask, appliedToLayerID);
    }

    public void addFilter(
            final T filterID, final Function<Color, Color> filterFunction,
            final T appliedToLayerID
    ) {
        if (!layerIDs.contains(appliedToLayerID)) {
            GameError.send(
                    "(caution) The layer this filter is trying to apply itself to (\"" +
                            appliedToLayerID + "\") is not in the assembler yet."
            );
        }

        filterFunctionMap.put(filterID, filterFunction);
        layerFilterMap.put(appliedToLayerID, filterID);
        layerIsEnabledMap.put(filterID, true);
    }
}
