package com.jordanbunke.jbjgl.menus;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.fonts.Font;
import com.jordanbunke.jbjgl.fonts.FontsForTests;
import com.jordanbunke.jbjgl.game.JBJGLGame;
import com.jordanbunke.jbjgl.game.JBJGLGameManager;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLMenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLMenuElementGrouping;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLStaticMenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.button.JBJGLSimpleMenuButton;
import com.jordanbunke.jbjgl.text.JBJGLText;
import com.jordanbunke.jbjgl.text.JBJGLTextBuilder;

import java.awt.*;

public class MenuSelectionExample {
    private static final int CANVAS_W = 320, CANVAS_H = 180, WINDOW_SCALE_UP = 4;
    private static final double REFRESH_RATE_HZ = 60.;

    public static void main(String[] args) {
        launch();
    }

    private static void launch() {
        final JBJGLGameManager gameManager = JBJGLGameManager.createOf(
                0, JBJGLMenuManager.initialize(
                        JBJGLMenuBuilder.build(generateMenuElements(),
                                JBJGLMenuSelectionLogic.basic()), "menu"));
        final JBJGLGame exampleGame = JBJGLGame.create("Example", gameManager,
                CANVAS_W * WINDOW_SCALE_UP, CANVAS_H * WINDOW_SCALE_UP,
                JBJGLImage.create(1, 1), true, false,
                REFRESH_RATE_HZ, REFRESH_RATE_HZ);
        exampleGame.getGameEngine().setRenderDimension(CANVAS_W, CANVAS_H);
        exampleGame.getGameEngine().getDebugger().hideBoundingBoxes();
    }

    private static JBJGLMenuElementGrouping generateMenuElements() {
        return JBJGLMenuElementGrouping.generateOf(
                generateBackground(),
                generateButton("Play", 0.5, 0.2),
                generateButton("Achievements", 0.5, 0.4),
                generateButton("Collectibles", 0.5, 0.6),
                generateButton("Settings", 0.2, 0.9),
                generateButton("Quit", 0.8, 0.9)
        );
    }

    private static JBJGLStaticMenuElement generateBackground() {
        final JBJGLImage background = JBJGLImage.create(CANVAS_W, CANVAS_H);
        final Graphics g = background.getGraphics();

        g.setColor(new Color(100, 0, 0, 255));
        g.fillRect(0, 0, CANVAS_W, CANVAS_H);

        g.dispose();

        return JBJGLStaticMenuElement.generate(new int[] { 0, 0 },
                JBJGLMenuElement.Anchor.LEFT_TOP, background);
    }

    private static JBJGLSimpleMenuButton generateButton(
            final String text, final double x, final double y
    ) {
        final Font font = FontsForTests.BASIC.getBold();
        final double textSize = 0.8;
        final Color nhc = new Color(0, 0, 0, 255),
                hc = new Color(255, 0, 0, 255);

        final JBJGLImage nhi = JBJGLTextBuilder.initialize(textSize,
                JBJGLText.Orientation.CENTER, nhc, font).addText(text).build().draw(),
                hi = JBJGLTextBuilder.initialize(textSize,
                        JBJGLText.Orientation.CENTER, hc, font).addText(text).build().draw();

        return JBJGLSimpleMenuButton.generate(
                new int[] {
                        (int)(CANVAS_W * x), (int)(CANVAS_H * y)
                }, new int[] { nhi.getWidth() + 2, hi.getHeight() + 2 },
                JBJGLMenuElement.Anchor.CENTRAL,
                true, () -> System.exit(0),
                nhi, hi);
    }
}
