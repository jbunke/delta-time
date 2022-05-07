package com.jordanbunke.jbjgl;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.fonts.JBJGLFonts;
import com.jordanbunke.jbjgl.game.JBJGLGame;
import com.jordanbunke.jbjgl.game.JBJGLGameManager;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.menus.JBJGLMenu;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLAnimationMenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLClickableMenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLMenuElement;
import com.jordanbunke.jbjgl.text.JBJGLText;
import com.jordanbunke.jbjgl.text.JBJGLTextComponent;

import java.awt.*;

public class Example {
    private static final int width = Toolkit.getDefaultToolkit().getScreenSize().width,
            height = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static void main(String[] args) {
        example1();
    }

    private static JBJGLImage button(final boolean highlighted) {
        Color c = highlighted ?
                new Color(255, 255, 255, 255) :
                new Color(0, 0, 0, 255);
        return JBJGLText.createOf(
                3, JBJGLText.Orientation.CENTER,
                JBJGLTextComponent.add("i WANT 2 quit", JBJGLFonts.CLASSIC(), c)
        ).draw();
    }

    private static JBJGLImage drawBackground(final int index) {
        JBJGLImage image = JBJGLImage.create(width, height);
        Graphics g = image.getGraphics();
        g.setColor(new Color(index * 17, 0, 255 - (index * 17), 255));
        g.fillRect(0, 0, width, height);
        g.dispose();
        return image;
    }

    private static void example1() {
        JBJGLAnimationMenuElement animationMenuElement = JBJGLAnimationMenuElement.generate(
                new int[] { 0, 0 }, new int[] { width, height }, JBJGLMenuElement.Anchor.LEFT_TOP,
                5, new JBJGLImage[] {
                        drawBackground(0), drawBackground(1), drawBackground(2),
                        drawBackground(3), drawBackground(4), drawBackground(5),
                        drawBackground(6), drawBackground(7), drawBackground(8),
                        drawBackground(9), drawBackground(10), drawBackground(11),
                        drawBackground(12), drawBackground(13), drawBackground(14),
                        drawBackground(15)
                }
        );
        JBJGLImage button = button(false), highlightedButton = button(true);
        JBJGLClickableMenuElement clickableMenuElement = JBJGLClickableMenuElement.generate(
                new int[]{ width / 2, height / 2 },
                new int[]{ button.getWidth() + 100, button.getHeight() - 20 },
                JBJGLMenuElement.Anchor.CENTRAL, button, highlightedButton,
                () -> System.exit(0)
        );
        JBJGLMenu menu = JBJGLMenu.of(
                animationMenuElement, clickableMenuElement
        );
        JBJGLGameManager manager = JBJGLGameManager.createOf(
                0, JBJGLMenuManager.initialize(menu, "instant quit")
        );
        JBJGLGame game = JBJGLGame.create(
                "Example 1", manager, width, height,
                JBJGLImage.create(20, 20),
                true, true
        );
        // game.getGameEngine().getDebugger().hideBoundingBoxes();
    }
}
