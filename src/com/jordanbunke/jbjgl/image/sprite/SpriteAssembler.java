package com.jordanbunke.jbjgl.image.sprite;

import com.jordanbunke.jbjgl.error.GameError;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.image.sprite.constituents.SpriteConstituent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpriteAssembler<T, R> {
    private final Map<T, SpriteConstituent<R>> layerMap;
    private final Map<T, Boolean> layerIsEnabledMap;
    private final List<T> layerIDs;

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
            if (layerIsEnabledMap.containsKey(layerID) && layerIsEnabledMap.get(layerID))
                sprite.draw(layerMap.get(layerID).getSprite(spriteID));

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

    public void addLayer(final int index, final T layerID, final SpriteConstituent<R> layer) {
        if (layerIDs.contains(layerID)) {
            GameError.send("A layer for this layer ID (\"" + layerID +
                    "\") is already present in the sprite assembler.");
            return;
        }

        layerIDs.add(index, layerID);
        layerIsEnabledMap.put(layerID, true);
        layerMap.put(layerID, layer);
    }

    public void addLayer(final T layerID, final SpriteConstituent<R> layer) {
        addLayer(layerIDs.size(), layerID, layer);
    }
}
