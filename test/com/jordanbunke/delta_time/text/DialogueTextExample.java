package com.jordanbunke.delta_time.text;

import com.jordanbunke.delta_time.fonts.Font;
import com.jordanbunke.delta_time.fonts.FontFamily;
import com.jordanbunke.delta_time.fonts.FontsForTests;
import com.jordanbunke.delta_time.game.Game;
import com.jordanbunke.delta_time.game.GameManager;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.menus.Menu;
import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menus.menu_elements.visual.AnimationMenuElement;
import com.jordanbunke.delta_time.utility.Coord2D;
import com.jordanbunke.delta_time.window.GameWindow;

import java.awt.*;

public class DialogueTextExample {
    private static final Color DEFAULT_COLOR = new Color(0, 0, 0);
    private static final FontFamily DEFAULT_FONT = FontsForTests.CLASSIC;
    private static final int SCALE_UP = 1, PADDING = 10, EXTRA_FRAMES = 50;
    private static final double TEXT_SIZE = 3d;
    private static final double TICK_HZ = 15d, FPS = 60d;

    public static void main(String[] args) {
        final String TITLE = "Dialogue text example";

        final String[] sections = {
                "This is an example of game text\nthat includes ", "emphasis",
                " as well as ", "\nsomething written in a different tone.\nCapisce?"
        };
        final Color[] colorsPerSection = {
                DEFAULT_COLOR, new Color(255, 0, 0),
                DEFAULT_COLOR, DEFAULT_COLOR
        };
        final Font[] fontsPerSection = {
                DEFAULT_FONT.getStandard(),
                DEFAULT_FONT.getStandard(),
                DEFAULT_FONT.getStandard(),
                DEFAULT_FONT.getItalics()
        };

        final AnimationMenuElement animation = generateAnimationFromTextInput(sections,
                colorsPerSection, fontsPerSection);

        final GameWindow gw = new GameWindow(TITLE, animation.getWidth() * SCALE_UP,
                animation.getHeight() * SCALE_UP, GameImage.dummy(), false);
        final GameManager gm = new GameManager(0, new Menu(animation));
        final Game ge = new Game(gw, gm, TICK_HZ, FPS);
        ge.setCanvasSize(animation.getWidth(), animation.getHeight());
        ge.getDebugger().hideBoundingBoxes();
    }

    private static AnimationMenuElement generateAnimationFromTextInput(
            final String[] sections, final Color[] colorsPerSection,
            final Font[] fontsPerSection
    ) {
        final Text text = DialogueUtils.generateSingleLetterDialogue(
                TEXT_SIZE, 0.6, sections, colorsPerSection, fontsPerSection);

        final int length = text.getComponentSize();
        final GameImage[] frames = new GameImage[length + EXTRA_FRAMES];

        for (int i = 0; i < length; i++)
            frames[i] = text.draw(i + 1);

        final GameImage last = frames[length - 1];

        for (int i = 0; i < EXTRA_FRAMES; i++)
            frames[i + length] = last;

        return new AnimationMenuElement(new Coord2D(PADDING, PADDING),
                new Coord2D(last.getWidth() + (2 * PADDING), last.getHeight() + (2 * PADDING)),
                MenuElement.Anchor.LEFT_TOP, 1, frames);
    }
}
