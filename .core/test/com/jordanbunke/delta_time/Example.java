package com.jordanbunke.delta_time;

import com.jordanbunke.delta_time._core.GameManager;
import com.jordanbunke.delta_time._core.Program;
import com.jordanbunke.delta_time.fonts.DeltaTimeFonts;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.menu.Menu;
import com.jordanbunke.delta_time.menu.MenuManager;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menu.menu_elements.button.SimpleMenuButton;
import com.jordanbunke.delta_time.menu.menu_elements.visual.AnimationMenuElement;
import com.jordanbunke.delta_time.text.Text;
import com.jordanbunke.delta_time.text.TextComponent;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
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
                new TextComponent("Você tem que respeitar as leis do país!",
                        DeltaTimeFonts.getDefault(), c)).draw();
    }

    private static GameImage drawBackground(final int index) {
        final GameImage image = new GameImage(width, height);
        image.fillRectangle(new Color(index * 17, 0, 255 - (index * 17), 255), 0, 0, width, height);
        return image.submit();
    }

    private static void example1() {
        AnimationMenuElement animationMenuElement = new AnimationMenuElement(
                new Coord2D(), new Bounds2D(width, height), MenuElement.Anchor.LEFT_TOP,
                5, drawBackground(0),
                drawBackground(1), drawBackground(2), drawBackground(3),
                drawBackground(4), drawBackground(5), drawBackground(6),
                drawBackground(7), drawBackground(8), drawBackground(9),
                drawBackground(10), drawBackground(11), drawBackground(12),
                drawBackground(13), drawBackground(14), drawBackground(15));
        GameImage button = button(false), highlightedButton = button(true);
        SimpleMenuButton menuButton = new SimpleMenuButton(
                new Coord2D(width / 2, height / 2),
                new Bounds2D(button.getWidth() + 100, button.getHeight() - 20),
                MenuElement.Anchor.CENTRAL, true, () -> System.exit(0),
                button, highlightedButton);
        Menu menu = new Menu(animationMenuElement, menuButton);
        GameManager manager = new GameManager(0, new MenuManager(menu, "instant quit"));
        new Program(new GameWindow(
                "Example 1", width, height, GameImage.dummy(), false),
                manager, 30d, 60d);
    }
}
