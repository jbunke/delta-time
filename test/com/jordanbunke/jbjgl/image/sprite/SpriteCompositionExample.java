package com.jordanbunke.jbjgl.image.sprite;

import com.jordanbunke.jbjgl.fonts.FontsForTests;
import com.jordanbunke.jbjgl.game.Game;
import com.jordanbunke.jbjgl.game.GameEngine;
import com.jordanbunke.jbjgl.game.GameManager;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.image.ImageProcessing;
import com.jordanbunke.jbjgl.image.sprite.constituents.DecisionSpriteConstituent;
import com.jordanbunke.jbjgl.image.sprite.constituents.InterpretedSpriteSheet;
import com.jordanbunke.jbjgl.image.sprite.constituents.OffsetSingleSpriteConstituent;
import com.jordanbunke.jbjgl.io.GameImageIO;
import com.jordanbunke.jbjgl.io.ResourceLoader;
import com.jordanbunke.jbjgl.menus.Menu;
import com.jordanbunke.jbjgl.menus.MenuBuilder;
import com.jordanbunke.jbjgl.menus.menu_elements.MenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.visual.AnimationMenuElement;
import com.jordanbunke.jbjgl.text.Text;
import com.jordanbunke.jbjgl.text.TextBuilder;
import com.jordanbunke.jbjgl.utility.Coord2D;
import com.jordanbunke.jbjgl.window.GameWindow;

import java.awt.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public class SpriteCompositionExample {
    private static final int WIDTH = 40, HEIGHT = 40, SCALE_UP = 15;

    private enum Layer {
        BODY, HEAD
    }

    public static void main(String[] args) {
        test("ff");
        test("kobold");
        test("greyscale");
    }

    private static void test(final String prefix) {
        final String title = "Sprite Generation Test: " + prefix;

        final SpriteStates<String> states = new SpriteStates<>(
                new String[] { "front", "back" },
                new String[] { "running" },
                new String[] { "1", "2", "3" }
        );
        final List<String> validSpriteIDs = states.getValidSpriteIDs();

        final SpriteMap<Layer> spriteMap = new SpriteMap<>(buildAssembler(prefix), states);
        saveSpriteSheet(spriteMap, validSpriteIDs, prefix);

        final Menu menu = new MenuBuilder().add(new AnimationMenuElement(
                new Coord2D(0, 0), new Coord2D(WIDTH, HEIGHT),
                MenuElement.Anchor.LEFT_TOP, 1,
                validSpriteIDs.stream().map(spriteMap::getSprite).toArray(GameImage[]::new)
        )).build();
        final GameManager gm = new GameManager(0, menu);
        final GameEngine ge = new GameEngine(
                new GameWindow(title, WIDTH * SCALE_UP, HEIGHT * SCALE_UP,
                GameImage.dummy(), false), gm, 10d, 60d
        );
        ge.setRenderDimension(WIDTH, HEIGHT);
        ge.getDebugger().hideBoundingBoxes();
        new Game(title, gm, ge);

        spriteMap.assembler.disableLayer(Layer.HEAD);
        spriteMap.spriteStates.removeMutuallyExclusiveContributors("back", "3");
        spriteMap.redraw();
        saveSpriteSheet(spriteMap, validSpriteIDs, prefix + "-redraw");
    }

    private static SpriteAssembler<Layer, String> buildAssembler(final String prefix) {
        final int DIRECTION = 0, STATE = 1, FRAME = 2;

        final GameImage
                spriteSheet = ResourceLoader.loadImageResource(Path.of("sprite", "spritesheets", "ff-running.png")),
                colorNet = ResourceLoader.loadImageResource(Path.of("sprite", "spritemaps", "ff-color-net.png")),
                palette = ResourceLoader.loadImageResource(Path.of("sprite", "spritemaps", prefix + "-palette.png")),
                headFront = ResourceLoader.loadImageResource(Path.of("sprite", "single_sprites", prefix + "-head-front.png")),
                headBack = ResourceLoader.loadImageResource(Path.of("sprite", "single_sprites", prefix + "-head-back.png"));

        final GameImage composedRunningFrames = SpriteComposer.compose(spriteSheet, colorNet, palette);

        final SpriteSheet runningSpriteSheet = new SpriteSheet(composedRunningFrames, WIDTH, HEIGHT);
        final InterpretedSpriteSheet<String> body = new InterpretedSpriteSheet<>(
                runningSpriteSheet, id -> {
            final int x = Integer.parseInt(SpriteStates.extractContributor(FRAME, id)) - 1;
            return new Coord2D(x, SpriteStates.extractContributor(DIRECTION, id).equals("front") ? 0 : 1);
        }
        );

        final Function<String, Coord2D> headBobbing =
                x -> SpriteStates.extractContributor(STATE, x).equals("running") &&
                        SpriteStates.extractContributor(FRAME, x).equals("3")
                        ? new Coord2D(0, 1)
                        : new Coord2D();
        final DecisionSpriteConstituent<String> head = new DecisionSpriteConstituent<>(
                x -> SpriteStates.extractContributor(DIRECTION, x).equals("front") ? 0 : 1,
                new OffsetSingleSpriteConstituent<>(headFront, headBobbing),
                new OffsetSingleSpriteConstituent<>(headBack, headBobbing));

        final SpriteAssembler<Layer, String> assembler = new SpriteAssembler<>(WIDTH, HEIGHT);
        assembler.addLayer(Layer.BODY, body);
        assembler.addLayer(Layer.HEAD, head);

        return assembler;
    }

    private static void saveSpriteSheet(
            final SpriteMap<Layer> spriteMap, final List<String> spriteIDs, final String prefix
    ) {
        final int s = 5, w = WIDTH * s, h = HEIGHT * s;

        final GameImage spriteSheet = new GameImage(w * spriteIDs.size(), h);
        final int BUFFER = 10;

        for (int i = 0; i < spriteIDs.size(); i++) {
            final GameImage sprite = spriteMap.getSprite(spriteIDs.get(i));

            spriteSheet.draw(ImageProcessing.scale(sprite, s), i * w, 0);
            spriteSheet.draw(drawText(spriteIDs.get(i)), BUFFER + (i * w), 0);
        }

        GameImageIO.writeImage(Path.of("test_out", "sprite", prefix + "-spritesheet.png"), spriteSheet.submit());
    }

    private static GameImage drawText(final String text) {
        return new TextBuilder(
                1.0, Text.Orientation.LEFT, new Color(0, 0, 0),
                FontsForTests.MY_HANDWRITING.getStandard()
        ).addText(text).build().draw();
    }
}
