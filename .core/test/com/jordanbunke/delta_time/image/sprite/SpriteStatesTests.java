package com.jordanbunke.delta_time.image.sprite;

import com.jordanbunke.delta_time.game.Game;
import com.jordanbunke.delta_time.game.GameManager;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.image.sprite.constituents.InterpretedSpriteSheet;
import com.jordanbunke.delta_time.io.ResourceLoader;
import com.jordanbunke.delta_time.menus.Menu;
import com.jordanbunke.delta_time.menus.MenuBuilder;
import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menus.menu_elements.visual.AnimationMenuElement;
import com.jordanbunke.delta_time.utility.math.Coord2D;
import com.jordanbunke.delta_time.window.GameWindow;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class SpriteStatesTests {
    public static void main(String[] args) {
        final int s = 16, scaleUp = 20;

        final int DIRECTION = 0, STATE = 1, FRAME = 2;
        final SpriteStates<String> states = new SpriteStates<>(
                new String[] { "l", "r" },
                new String[] { "idle", "dead", "walking", "running" },
                new String[] { "1", "2", "3", "4" }
        );
        states.removeMutuallyExclusiveContributors("idle", "3");
        states.removeMutuallyExclusiveContributors("idle", "4");

        final GameImage source = ResourceLoader.loadImageResource(Path.of("sprite", "spritesheets", "stickman.png")),
                colorMap = ResourceLoader.loadImageResource(Path.of("sprite", "spritemaps", "stickman-color-net.png")),
                lookup = ResourceLoader.loadImageResource(Path.of("sprite", "spritemaps", "stickman-palette.png"));
        final SpriteSheet sheet = new SpriteSheet(SpriteComposer.compose(source, colorMap, lookup), s, s);

        final Function<String, Coord2D> coordinateLogic = id -> {
            int x = 0, y;

            if (SpriteStates.extractContributor(DIRECTION, id).equals("r"))
                x += 4;

            x += Integer.parseInt(SpriteStates.extractContributor(FRAME, id)) - 1;

            y = switch (SpriteStates.extractContributor(STATE, id)) {
                case "dead" -> 1;
                case "walking" -> 2;
                case "running" -> 3;
                default -> 0;
            };

            return new Coord2D(x, y);
        };
        final InterpretedSpriteSheet<String> is = new InterpretedSpriteSheet<>(sheet, coordinateLogic);

        final SpriteMap<String> spriteMap = new SpriteMap<>(new SpriteAssembler<>(s, s, List.of("1"), is), states);

        final GameImage[] animation = new GameImage[] {
                spriteMap.getSprite("l-running-1"),
                spriteMap.getSprite("l-running-2"),
                spriteMap.getSprite("l-running-3"),
                spriteMap.getSprite("l-running-4"),
                spriteMap.getSprite("l-running-1"),
                spriteMap.getSprite("l-running-2"),
                spriteMap.getSprite("l-running-3"),
                spriteMap.getSprite("l-running-4"),
                spriteMap.getSprite("l-running-1"),
                spriteMap.getSprite("l-running-2"),
                spriteMap.getSprite("l-running-3"),
                spriteMap.getSprite("l-running-4"),
                spriteMap.getSprite("l-walking-1"),
                spriteMap.getSprite("l-walking-2"),
                spriteMap.getSprite("l-walking-3"),
                spriteMap.getSprite("l-walking-4"),
                spriteMap.getSprite("l-idle-1"),
                spriteMap.getSprite("l-idle-2"),
                spriteMap.getSprite("l-idle-1"),
                spriteMap.getSprite("l-idle-2"),
                spriteMap.getSprite("r-idle-1"),
                spriteMap.getSprite("r-idle-2"),
                spriteMap.getSprite("r-idle-1"),
                spriteMap.getSprite("r-idle-2"),
                spriteMap.getSprite("r-idle-1"),
                spriteMap.getSprite("r-idle-2"),
                spriteMap.getSprite("r-walking-1"),
                spriteMap.getSprite("r-walking-2"),
                spriteMap.getSprite("r-idle-1"),
                spriteMap.getSprite("r-idle-2"),
                spriteMap.getSprite("r-walking-1"),
                spriteMap.getSprite("r-walking-2"),
                spriteMap.getSprite("r-walking-3"),
                spriteMap.getSprite("r-walking-4"),
                spriteMap.getSprite("r-walking-1"),
                spriteMap.getSprite("r-walking-2"),
                spriteMap.getSprite("r-walking-3"),
                spriteMap.getSprite("r-walking-4"),
                spriteMap.getSprite("r-walking-1"),
                spriteMap.getSprite("r-walking-2"),
                spriteMap.getSprite("r-dead-1"),
                spriteMap.getSprite("r-dead-2"),
                spriteMap.getSprite("r-dead-3"),
                spriteMap.getSprite("r-dead-4"),
                spriteMap.getSprite("r-dead-4"),
                spriteMap.getSprite("r-dead-4"),
                spriteMap.getSprite("r-dead-4"),
                spriteMap.getSprite("r-dead-4"),
                spriteMap.getSprite("r-dead-4"),
        };

        final String title = "Animation test";
        final Menu menu = new MenuBuilder().add(new AnimationMenuElement(
                new Coord2D(s / 2, s / 2), new Coord2D(s, s),
                MenuElement.Anchor.LEFT_TOP, 2,
                animation)).build();
        final GameManager gm = new GameManager(0, menu);
        final Game ge = new Game(new GameWindow(
                title, 2 * s * scaleUp, 2 * s * scaleUp,
                GameImage.dummy(), false), gm, 10d, 60d);
        ge.setCanvasSize(2 * s, 2 * s);
        ge.getDebugger().hideBoundingBoxes();
    }

    @Test
    public void mutualExclusivityTest() {
        final SpriteStates<String> states = new SpriteStates<>(
                new String[] { "l", "r" },
                new String[] { "idle", "dead", "walking", "running" },
                new String[] { "1", "2", "3", "4" }
        );

        System.out.println("Initial:");
        System.out.println(states.getValidSpriteIDs());
        System.out.println();

        states.removeMutuallyExclusiveContributors("idle", "3");
        states.removeMutuallyExclusiveContributors("idle", "4");

        final List<String> validSpriteIDs = states.getValidSpriteIDs();
        final Set<String> expected = Set.of(
                "l-running-1", "l-running-2", "l-running-3", "l-running-4",
                "r-running-1", "r-running-2", "r-running-3", "r-running-4",
                "l-walking-1", "l-walking-2", "l-walking-3", "l-walking-4",
                "r-walking-1", "r-walking-2", "r-walking-3", "r-walking-4",
                "l-idle-1", "l-idle-2", "r-idle-1", "r-idle-2",
                "l-dead-1", "l-dead-2", "l-dead-3", "l-dead-4",
                "r-dead-1", "r-dead-2", "r-dead-3", "r-dead-4"
        );

        System.out.println("After mutually exclusive removals:");
        System.out.println(validSpriteIDs);

        Assert.assertEquals(expected, new HashSet<>(validSpriteIDs));
    }
}
