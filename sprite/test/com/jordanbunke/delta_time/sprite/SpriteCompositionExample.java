package com.jordanbunke.delta_time.sprite;

import com.jordanbunke.anim.GIFWriter;
import com.jordanbunke.delta_time._core.GameManager;
import com.jordanbunke.delta_time._core.Program;
import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.fonts.DeltaTimeFonts;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.image.ImageProcessing;
import com.jordanbunke.delta_time.io.GameImageIO;
import com.jordanbunke.delta_time.io.ResourceLoader;
import com.jordanbunke.delta_time.menu.Menu;
import com.jordanbunke.delta_time.menu.MenuBuilder;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menu.menu_elements.visual.AnimationMenuElement;
import com.jordanbunke.delta_time.sprite.constituents.DecisionSpriteConstituent;
import com.jordanbunke.delta_time.sprite.constituents.InterpretedSpriteSheet;
import com.jordanbunke.delta_time.sprite.constituents.OffsetSingleSpriteConstituent;
import com.jordanbunke.delta_time.text.Text;
import com.jordanbunke.delta_time.text.TextBuilder;
import com.jordanbunke.delta_time.utility.math.Coord2D;
import com.jordanbunke.delta_time.utility.math.RNG;
import com.jordanbunke.delta_time.window.GameWindow;

import java.awt.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public class SpriteCompositionExample {
    private static final int WIDTH = 40, HEIGHT = 40, SCALE_UP = 12;

    private static final Function<Color, Color>
            NONE = x -> x,
            ODDBALL = x -> new Color(255 - x.getGreen(), 255 - x.getBlue(), 255 - x.getRed(), x.getAlpha());

    private enum Layer {
        BODY, HEAD, HAIR_MASK, HELMET, HELMET_FILTER, HELMET_MASK
    }

    public static void main(String[] args) {
        test("ff", false, false);
        test("afro", false, true);
        test("afro", true, false);
    }

    private static void test(
            final String prefix,
            final boolean hasHelmet, final boolean hasHelmetDamage
    ) {
        final String title = "Sprite Generation Test: " + prefix;

        final SpriteStates<String> states = new SpriteStates<>(
                new String[] { "front", "back" },
                new String[] { "running" },
                new String[] { "1", "2", "3" }
        );
        final List<String> validSpriteIDs = states.getValidSpriteIDs();

        final SpriteMap<Layer> spriteMap = new SpriteMap<>(
                buildAssembler(prefix, hasHelmet, hasHelmetDamage), states
        );
        saveSpriteSheet(spriteMap, validSpriteIDs, prefix);

        final Menu menu = new MenuBuilder().add(new AnimationMenuElement(
                new Coord2D(0, 0), new Coord2D(WIDTH, HEIGHT),
                MenuElement.Anchor.LEFT_TOP, 1,
                validSpriteIDs.stream().map(spriteMap::getSprite).toArray(GameImage[]::new)
        )).build();
        final GameManager gm = new GameManager(0, menu);
        final Program ge = new Program(
                new GameWindow(title, WIDTH * SCALE_UP, HEIGHT * SCALE_UP,
                GameImage.dummy(), false), gm, 10d, 60d
        );
        ge.setCanvasSize(WIDTH, HEIGHT);
        ge.getDebugger().hideBoundingBoxes();
        ge.getDebugger().muteChannel(GameDebugger.FRAME_RATE);

        spriteMap.assembler.disableLayer(Layer.HELMET);
        spriteMap.assembler.disableLayer(Layer.HAIR_MASK);
        spriteMap.redraw();
        saveSpriteSheet(spriteMap, validSpriteIDs, prefix + "-redraw");
    }

    private static SpriteAssembler<Layer, String> buildAssembler(
            final String prefix, final boolean hasHelmet, final boolean hasHelmetDamage
    ) {
        final int DIRECTION = 0, STATE = 1, FRAME = 2;

        final GameImage
                spriteSheet = ResourceLoader.loadImageResource(Path.of("sprite", "spritesheets", "ff-running.png")),
                colorNet = ResourceLoader.loadImageResource(Path.of("sprite", "spritemaps", "ff-color-net.png")),
                palette = ResourceLoader.loadImageResource(Path.of("sprite", "spritemaps", prefix + "-palette.png")),
                headFront = ResourceLoader.loadImageResource(Path.of("sprite", "single_sprites", prefix + "-head-front.png")),
                headBack = ResourceLoader.loadImageResource(Path.of("sprite", "single_sprites", prefix + "-head-back.png")),
                hairMaskFront = ResourceLoader.loadImageResource(Path.of("sprite", "single_sprites", "hair-mask-front.png")),
                hairMaskBack = ResourceLoader.loadImageResource(Path.of("sprite", "single_sprites", "hair-mask-back.png")),
                butterHelmSource = ResourceLoader.loadImageResource(Path.of("sprite", "spritesheets", "butter-helm.png")),
                helmetDamageSource = ResourceLoader.loadImageResource(Path.of("sprite", "spritesheets", "helmet-damage.png"));

        assert helmetDamageSource != null && butterHelmSource != null;

        final GameImage composedRunningFrames = SpriteComposer.compose(spriteSheet, colorNet, palette);

        final SpriteSheet runningSpriteSheet = new SpriteSheet(composedRunningFrames, WIDTH, HEIGHT);
        final InterpretedSpriteSheet<String> body = new InterpretedSpriteSheet<>(
                runningSpriteSheet, id -> {
                    final int x = Integer.parseInt(SpriteStates.extractContributor(FRAME, id)) - 1;
                    return new Coord2D(x, SpriteStates.extractContributor(DIRECTION, id).equals("front") ? 0 : 1);
        });

        final Function<String, Coord2D> headBobbing =
                x -> SpriteStates.matchesAllContributors(new String[] { "running", "3" },
                        new int[] { STATE, FRAME }, x) ? new Coord2D(0, 1) : new Coord2D();
        final DecisionSpriteConstituent<String> head = new DecisionSpriteConstituent<>(
                x -> SpriteStates.extractContributor(DIRECTION, x).equals("front") ? 0 : 1,
                new OffsetSingleSpriteConstituent<>(headFront, headBobbing),
                new OffsetSingleSpriteConstituent<>(headBack, headBobbing));
        final DecisionSpriteConstituent<String> hairMask = new DecisionSpriteConstituent<>(
                x -> SpriteStates.extractContributor(DIRECTION, x).equals("front") ? 0 : 1,
                new OffsetSingleSpriteConstituent<>(hairMaskFront, headBobbing),
                new OffsetSingleSpriteConstituent<>(hairMaskBack, headBobbing));
        final InterpretedSpriteSheet<String> helmet = new InterpretedSpriteSheet<>(
                new SpriteSheet(butterHelmSource, WIDTH, HEIGHT),
                id -> new Coord2D(
                        SpriteStates.extractContributor(DIRECTION, id).equals("front") ? 0 : 1,
                        SpriteStates.extractContributor(FRAME, id).equals("3") ? 1 : 0
                ));
        final InterpretedSpriteSheet<String> helmetDamage = new InterpretedSpriteSheet<>(
                new SpriteSheet(helmetDamageSource, WIDTH, HEIGHT),
                id -> new Coord2D(
                        SpriteStates.extractContributor(DIRECTION, id).equals("front") ? 0 : 1,
                        SpriteStates.extractContributor(FRAME, id).equals("3") ? 1 : 0
                ));

        final SpriteAssembler<Layer, String> assembler = new SpriteAssembler<>(WIDTH, HEIGHT);
        assembler.addLayer(Layer.BODY, body);
        assembler.addLayer(Layer.HEAD, head);

        if (hasHelmet) {
            assembler.addMask(Layer.HAIR_MASK, hairMask, Layer.HEAD);
            assembler.addLayer(Layer.HELMET, helmet);
            assembler.addFilter(Layer.HELMET_FILTER, RNG.flipCoin() ? ODDBALL : NONE, Layer.HELMET);

            if (hasHelmetDamage)
                assembler.addMask(Layer.HELMET_MASK, helmetDamage, Layer.HELMET);
        }

        return assembler;
    }

    private static void saveSpriteSheet(
            final SpriteMap<Layer> spriteMap, final List<String> spriteIDs, final String prefix
    ) {
        final int s = 5, w = WIDTH * s, h = HEIGHT * s, intervalMillis = 250, reps = 5;

        final GameImage spriteSheet = new GameImage(w * spriteIDs.size(), h);
        final GameImage[] animation = new GameImage[spriteIDs.size()];
        final int BUFFER = 10;

        for (int i = 0; i < spriteIDs.size(); i++) {
            final GameImage sprite = spriteMap.getSprite(spriteIDs.get(i));
            animation[i] = sprite;

            spriteSheet.draw(ImageProcessing.scale(sprite, s), i * w, 0);
            spriteSheet.draw(drawText(spriteIDs.get(i)), BUFFER + (i * w), 0);
        }

        final Path basePath = Path.of("sprite", "test_out");

        GameImageIO.writeImage(basePath.resolve(Path.of(prefix + "-spritesheet.png")), spriteSheet.submit());
        GIFWriter.get().write(basePath.resolve(Path.of(prefix + "-anim.gif")), animation, intervalMillis, reps);
    }

    private static GameImage drawText(final String text) {
        return new TextBuilder(1.0, Text.Orientation.LEFT,
                new Color(0, 0, 0), DeltaTimeFonts.getDefault())
                .addText(text).build().draw();
    }
}
