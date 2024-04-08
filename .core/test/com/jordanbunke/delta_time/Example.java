package com.jordanbunke.delta_time;

import com.jordanbunke.delta_time.contexts.MenuManager;
import com.jordanbunke.delta_time.fonts.FontsForTests;
import com.jordanbunke.delta_time.game.Game;
import com.jordanbunke.delta_time.game.GameManager;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.menus.Menu;
import com.jordanbunke.delta_time.menus.menu_elements.visual.AnimationMenuElement;
import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menus.menu_elements.button.SimpleMenuButton;
import com.jordanbunke.delta_time.text.Text;
import com.jordanbunke.delta_time.text.TextComponent;
import com.jordanbunke.delta_time.utility.math.Coord2D;
import com.jordanbunke.delta_time.window.GameWindow;

import java.awt.*;

public class Example {
    private static final int width = Toolkit.getDefaultToolkit().getScreenSize().width,
            height = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static void main(String[] args) {
        example1();
    }

    private static GameImage button(final boolean highlighted) {
        Color c = highlighted ?
                new Color(255, 255, 255, 255) :
                new Color(0, 0, 0, 255);
        return new Text(
                4.2, Text.Orientation.CENTER,
                new TextComponent(
                        "Você tem que respeitar as leis do país!",
                        FontsForTests.CLASSIC.getItalics(), c
                )).draw();
    }

    private static GameImage drawBackground(final int index) {
        final GameImage image = new GameImage(width, height);
        image.fillRectangle(new Color(index * 17, 0, 255 - (index * 17), 255), 0, 0, width, height);
        return image.submit();
    }

    private static void example1() {
        AnimationMenuElement animationMenuElement = new AnimationMenuElement(
                new Coord2D(), new Coord2D(width, height), MenuElement.Anchor.LEFT_TOP,
                5, drawBackground(0),
                drawBackground(1), drawBackground(2), drawBackground(3),
                drawBackground(4), drawBackground(5), drawBackground(6),
                drawBackground(7), drawBackground(8), drawBackground(9),
                drawBackground(10), drawBackground(11), drawBackground(12),
                drawBackground(13), drawBackground(14), drawBackground(15));
        GameImage button = button(false), highlightedButton = button(true);
        SimpleMenuButton menuButton = new SimpleMenuButton(
                new Coord2D(width / 2, height / 2),
                new Coord2D(button.getWidth() + 100, button.getHeight() - 20),
                MenuElement.Anchor.CENTRAL, true, () -> System.exit(0),
                button, highlightedButton);
        Menu menu = new Menu(animationMenuElement, menuButton);
        GameManager manager = new GameManager(0, new MenuManager(menu, "instant quit"));
        new Game(new GameWindow(
                "Example 1", width, height, GameImage.dummy(), false),
                manager, 30d, 60d);
    }
}
