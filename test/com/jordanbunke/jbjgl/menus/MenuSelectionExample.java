package com.jordanbunke.jbjgl.menus;

import com.jordanbunke.jbjgl.contexts.MenuManager;
import com.jordanbunke.jbjgl.fonts.Font;
import com.jordanbunke.jbjgl.fonts.FontsForTests;
import com.jordanbunke.jbjgl.game.Game;
import com.jordanbunke.jbjgl.game.GameManager;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.menus.menu_elements.MenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.MenuElementGrouping;
import com.jordanbunke.jbjgl.menus.menu_elements.StaticMenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.button.SimpleMenuButton;
import com.jordanbunke.jbjgl.text.Text;
import com.jordanbunke.jbjgl.text.TextBuilder;

import java.awt.*;

public class MenuSelectionExample {
    private static final int CANVAS_W = 320, CANVAS_H = 180, WINDOW_SCALE_UP = 6;
    private static final double REFRESH_RATE_HZ = 60.;

    public static void main(String[] args) {
        launch();
    }

    private static void launch() {
        final GameManager gameManager = new GameManager(0,
                new MenuManager(new MenuBuilder(generateMenuElements())
                        .build(MenuSelectionLogic.basic()), "menu"));
        final Game exampleGame = Game.assemble("Example", gameManager,
                CANVAS_W * WINDOW_SCALE_UP, CANVAS_H * WINDOW_SCALE_UP,
                GameImage.dummy(), true, true,
                REFRESH_RATE_HZ, REFRESH_RATE_HZ);
        exampleGame.getGameEngine().setRenderDimension(CANVAS_W, CANVAS_H);
        // exampleGame.getGameEngine().getDebugger().hideBoundingBoxes();
    }

    private static MenuElementGrouping generateMenuElements() {
        return MenuElementGrouping.generateOf(
                generateBackground(),
                generateButton("Play", 0.3, 0.2),
                generateButton("Settings", 0.5, 0.5),
                generateButton("Quit", 0.7, 0.8)
        );
    }

    private static StaticMenuElement generateBackground() {
        final GameImage background = new GameImage(CANVAS_W, CANVAS_H);
        final Graphics g = background.getGraphics();

        g.setColor(new Color(100, 0, 0, 255));
        g.fillRect(0, 0, CANVAS_W, CANVAS_H);

        g.dispose();

        return StaticMenuElement.generate(new int[] { 0, 0 },
                MenuElement.Anchor.LEFT_TOP, background);
    }

    private static SimpleMenuButton generateButton(
            final String text, final double x, final double y
    ) {
        final Font font = FontsForTests.CLASSIC.getItalics(); // BASIC.getBold() / CLASSIC.getItalics()
        final double textSize = 1.0;
        final Color nhc = new Color(0, 0, 0, 255),
                hc = new Color(255, 255, 255, 255);

        final GameImage nhi = new TextBuilder(textSize,
                Text.Orientation.CENTER, nhc, font).addText(text).build().draw(),
                hi = new TextBuilder(textSize,
                        Text.Orientation.CENTER, hc, font).addText(text).build().draw();

        return new SimpleMenuButton(
                new int[] {
                        (int)(CANVAS_W * x), (int)(CANVAS_H * y)
                }, new int[] { nhi.getWidth() + 2, hi.getHeight() + 2 },
                MenuElement.Anchor.CENTRAL,
                true, () -> System.exit(0),
                nhi, hi);
    }
}
