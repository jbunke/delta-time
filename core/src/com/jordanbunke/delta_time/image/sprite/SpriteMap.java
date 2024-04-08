package com.jordanbunke.delta_time.image.sprite;

import com.jordanbunke.delta_time.image.GameImage;

import java.util.*;

public class SpriteMap<T> {
    private final Map<String, GameImage> spriteMap;

    public final SpriteStates<String> spriteStates;
    public final SpriteAssembler<T, String> assembler;

    public SpriteMap(final SpriteAssembler<T, String> assembler, final SpriteStates<String> spriteStates) {
        spriteMap = new HashMap<>();

        this.assembler = assembler;
        this.spriteStates = spriteStates;

        draw();
    }

    private void draw() {
        final List<String> validSpriteIDs = spriteStates.getValidSpriteIDs();

        for (String validSpriteID : validSpriteIDs)
            spriteMap.put(validSpriteID, assembler.assembleSprite(validSpriteID));
    }

    public void redraw() {
        Set<String> inMap = new HashSet<>(spriteMap.keySet());

        for (String validSpriteID : inMap)
            spriteMap.remove(validSpriteID);

        draw();
    }

    public GameImage getSprite(final String spriteID) {
        if (spriteMap.containsKey(spriteID))
            return spriteMap.get(spriteID);

        return GameImage.dummy();
    }
}
