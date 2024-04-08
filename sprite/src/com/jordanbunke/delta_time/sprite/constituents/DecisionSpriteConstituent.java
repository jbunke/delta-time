package com.jordanbunke.delta_time.sprite.constituents;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;

import java.util.function.Function;

public final class DecisionSpriteConstituent<R> implements SpriteConstituent<R> {
    private final Function<R, Integer> decisionIndexLogic;
    private final SpriteConstituent<R>[] choices;

    @SafeVarargs
    public DecisionSpriteConstituent(
            final Function<R, Integer> decisionIndexLogic, final SpriteConstituent<R>... choices
    ) {
        this.decisionIndexLogic = decisionIndexLogic;
        this.choices = choices;
    }

    @Override
    public GameImage getSprite(final R spriteID) {
        final int choice = decisionIndexLogic.apply(spriteID);

        if (choice < 0 || choice >= choices.length) {
            GameError.send(
                    "Invalid choice index (" + choice + " for " +
                            choices.length + " choices) for sprite id \"" +
                            spriteID + "\".");
            return GameImage.dummy();
        }

        return choices[choice].getSprite(spriteID);
    }
}
