package com.jordanbunke.jbjgl;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.contexts.ProgramContext;
import com.jordanbunke.jbjgl.events.JBJGLEvent;
import com.jordanbunke.jbjgl.events.JBJGLKey;
import com.jordanbunke.jbjgl.events.JBJGLKeyEvent;
import com.jordanbunke.jbjgl.fonts.JBJGLFonts;
import com.jordanbunke.jbjgl.game.JBJGLGame;
import com.jordanbunke.jbjgl.game.JBJGLGameManager;
import com.jordanbunke.jbjgl.game.JBJGLGameEngine;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.menus.JBJGLMenu;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLAnimationMenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLClickableMenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLMenuElement;
import com.jordanbunke.jbjgl.text.JBJGLText;
import com.jordanbunke.jbjgl.text.JBJGLTextBuilder;
import com.jordanbunke.jbjgl.text.JBJGLTextComponent;

import java.awt.*;
import java.util.List;

public class Example {
    private static final int width = 1200, height = 675;

    public static void main(String[] args) {
        example1();
        example2();
    }

    private static JBJGLImage button(final boolean highlighted) {
        Color c = highlighted ?
                new Color(255, 255, 255, 255) :
                new Color(0, 0, 0, 255);
        return JBJGLText.createOf(
                3, JBJGLText.Orientation.CENTER,
                JBJGLTextComponent.add("QUIT", JBJGLFonts.CLASSIC(), c)
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
                new int[] { 0, 0 }, new int[] { 1200, 675 }, JBJGLMenuElement.Anchor.LEFT_TOP,
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
                new int[]{ button.getWidth(), button.getHeight() },
                JBJGLMenuElement.Anchor.CENTRAL, button, highlightedButton,
                () -> System.exit(0)
        );
        JBJGLMenu menu = JBJGLMenu.of(
                animationMenuElement, clickableMenuElement
        );
        JBJGLGameManager manager = JBJGLGameManager.createOf(
                0, JBJGLMenuManager.initialize(menu, "instant quit")
        );
        JBJGLGame.create(
                "Example 1", manager, width, height,
                JBJGLImage.create(20, 20),
                true, false
        );
    }

    private static void example2() {

        ProgramContext b = new ProgramContext() {
            private int i = 0;
            private boolean right = true;
            private int textSize = 1;
            private JBJGLImage text = drawText();

            private JBJGLImage drawText() {
                return JBJGLTextBuilder.initialize(
                                textSize, JBJGLText.Orientation.CENTER,
                                new Color(0, 0, 0, 255), JBJGLFonts.CLASSIC()
                        ).addText("EXAMPLE TEXT")
                        .addLineBreak().addText("[UP / DOWN] TEXT SIZE - [M] MOVEMENT DIRECTION").build().draw();
            }

            @Override
            public void update() {
                if (right) {
                    i += 2;
                    if (i > width - (text.getWidth() + 20))
                        right = false;
                } else {
                    i -= 2;
                    if (i <= 0)
                        right = true;
                }
            }

            @Override
            public void render(Graphics g) {
                g.drawImage(text, i + 20, 100, null);
            }

            @Override
            public void process(final JBJGLListener listener) {
                List<JBJGLEvent> eventList = listener.getUnprocessedEvents();
                for (JBJGLEvent event : eventList) {
                    if (event.isProcessed())
                        continue;

                    if (event.equals(
                            JBJGLKeyEvent.generate(JBJGLKey.M, JBJGLKeyEvent.Action.PRESS)
                    )) {
                        right = !right;

                        event.markAsProcessed();
                    } else if (event.equals(
                            JBJGLKeyEvent.generate(JBJGLKey.UP_ARROW, JBJGLKeyEvent.Action.PRESS)
                    )) {
                        textSize++;
                        text = drawText();

                        event.markAsProcessed();
                    } else if (event.equals(
                            JBJGLKeyEvent.generate(JBJGLKey.DOWN_ARROW, JBJGLKeyEvent.Action.PRESS)
                    )) {
                        if (textSize > 1) textSize--;
                        text = drawText();

                        event.markAsProcessed();
                    }
                }
            }
        };

        JBJGLGameManager manager = JBJGLGameManager.createOf(JBJGLGameManager.PLAY, b);
        JBJGLGameEngine.newWindowed(
                "Example 2", width, height, JBJGLImage.create(20, 20),
                false, false, manager, manager, manager,
                60.0, 60.0, 5);
    }
}
