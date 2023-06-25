package com.jordanbunke.jbjgl.text;

import com.jordanbunke.jbjgl.fonts.Font;
import com.jordanbunke.jbjgl.fonts.FontsForTests;
import com.jordanbunke.jbjgl.game.Game;
import com.jordanbunke.jbjgl.game.GameManager;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.menus.Menu;
import com.jordanbunke.jbjgl.menus.menu_elements.MenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.visual.AnimationMenuElement;
import com.jordanbunke.jbjgl.utility.Coord2D;
import com.jordanbunke.jbjgl.window.GameWindow;

import java.awt.*;

public class DialogueTextExample {
    private static final Color DEFAULT_COLOR = new Color(0, 0, 0);
    private static final Font DEFAULT_FONT = FontsForTests.CLASSIC.getStandard();
    private static final int SCALE_UP = 5, PADDING = 10;
    private static final double TICK_HZ = 15d, FPS = 60d;

    public static void main(String[] args) {
        final String TITLE = "Dialogue text example";

        final String[] sections = {
                "Different colours and fonts can\nbe used to convey ",
                "EMPHASIS", "\nand tone... ", "Isn't that something?"
        };
        final Color[] colorsPerSection = {
                DEFAULT_COLOR, new Color(255, 0, 0),
                DEFAULT_COLOR, new Color(255, 200, 0)
        };
        final Font[] fontsPerSection = {
                DEFAULT_FONT, FontsForTests.CLASSIC.getItalics(),
                DEFAULT_FONT, FontsForTests.BASIC.getBold()
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
                1d, 0.6, sections, colorsPerSection, fontsPerSection);

        final int length = text.getComponentSize(), extraFrames = 50;
        final GameImage[] frames = new GameImage[length + extraFrames];

        for (int i = 0; i < length; i++)
            frames[i] = text.draw(i + 1);

        final GameImage last = frames[length - 1];

        for (int i = 0; i < extraFrames; i++)
            frames[i + length] = last;

        return new AnimationMenuElement(new Coord2D(PADDING, PADDING),
                new Coord2D(last.getWidth() + (2 * PADDING), last.getHeight() + (2 * PADDING)),
                MenuElement.Anchor.LEFT_TOP, 1, frames);
    }
}
