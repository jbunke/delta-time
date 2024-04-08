package com.jordanbunke.delta_time.menus;

import com.jordanbunke.delta_time.fonts.Font;
import com.jordanbunke.delta_time.fonts.FontsForTests;
import com.jordanbunke.delta_time._core.Program;
import com.jordanbunke.delta_time._core.GameManager;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menus.menu_elements.button.SimpleMenuButton;
import com.jordanbunke.delta_time.menus.menu_elements.container.MenuElementGrouping;
import com.jordanbunke.delta_time.menus.menu_elements.visual.StaticMenuElement;
import com.jordanbunke.delta_time.text.Text;
import com.jordanbunke.delta_time.text.TextBuilder;
import com.jordanbunke.delta_time.utility.math.Coord2D;
import com.jordanbunke.delta_time.window.GameWindow;

import java.awt.*;

public class MenuSelectionExample {
    private static final int CANVAS_W = 320, CANVAS_H = 180;

    public static void main(String[] args) {
        launch();
    }

    private static void launch() {
        final GameManager gameManager = new GameManager(0,
                new MenuManager(new MenuBuilder(generateMenuElements())
                        .build(MenuSelectionLogic.basic()), "menu"));
        final Program program = new Program(new GameWindow("Example", GameImage.dummy()), gameManager);
        program.setCanvasSize(CANVAS_W, CANVAS_H);
    }

    private static MenuElementGrouping generateMenuElements() {
        return new MenuElementGrouping(
                generateBackground(),
                generateButton("Play", 0.3, 0.2),
                generateButton("Settings", 0.5, 0.5),
                generateButton("Quit", 0.7, 0.8)
        );
    }

    private static StaticMenuElement generateBackground() {
        final GameImage background = new GameImage(CANVAS_W, CANVAS_H);

        background.fillRectangle(new Color(100, 0, 0, 255),
                0, 0, CANVAS_W, CANVAS_H);

        return new StaticMenuElement(new Coord2D(), MenuElement.Anchor.LEFT_TOP, background.submit());
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
                new Coord2D((int)(CANVAS_W * x), (int)(CANVAS_H * y)),
                new Coord2D(nhi.getWidth() + 2, hi.getHeight() + 2),
                MenuElement.Anchor.CENTRAL, true, () -> System.exit(0),
                nhi, hi);
    }
}
