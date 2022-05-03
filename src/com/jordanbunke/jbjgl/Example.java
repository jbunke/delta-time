package com.jordanbunke.jbjgl;

import com.jordanbunke.jbjgl.contexts.ProgramContext;
import com.jordanbunke.jbjgl.events.JBJGLEvent;
import com.jordanbunke.jbjgl.events.JBJGLKeyEvent;
import com.jordanbunke.jbjgl.fonts.JBJGLFonts;
import com.jordanbunke.jbjgl.game_manager.JBJGLGameManager;
import com.jordanbunke.jbjgl.game_manager.JBJGLGameEngine;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.text.JBJGLText;
import com.jordanbunke.jbjgl.text.JBJGLTextBuilder;
import com.jordanbunke.jbjgl.utility.CollectionProcessing;

import java.awt.*;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        final int width = 1200, height = 675;
        ProgramContext b = new ProgramContext() {
            private int i = 0;
            private boolean right = true;
            private int textSize = 1;
            private JBJGLImage text = drawText();

            private JBJGLImage drawText() {
                return JBJGLTextBuilder.initialize(
                        textSize, JBJGLText.Orientation.CENTER,
                        new Color(0, 0, 0, 255), JBJGLFonts.BASIC_BOLD()
                ).addText("OITIOitOooOiT").build().draw();
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
            public void process(List<JBJGLEvent> eventList) {
                for (int i = 0; i < eventList.size(); i++) {
                    JBJGLEvent event = eventList.get(i);
                    if (event.isProcessed())
                        continue;

                    if (event.equals(
                            JBJGLKeyEvent.generate('m', JBJGLKeyEvent.Action.TYPE)
                    )) {
                        right = !right;

                        event.markAsProcessed();
                    } else if (event.equals(
                            JBJGLKeyEvent.generate('w', JBJGLKeyEvent.Action.TYPE)
                    )) {
                        textSize++;
                        text = drawText();

                        event.markAsProcessed();
                    } else if (event.equals(
                            JBJGLKeyEvent.generate('s', JBJGLKeyEvent.Action.TYPE)
                    )) {
                        if (textSize > 1) textSize--;
                        text = drawText();

                        event.markAsProcessed();
                    }
                }

                CollectionProcessing.emptyList(eventList);
            }
        };

        JBJGLGameManager manager = JBJGLGameManager.create(
                new ProgramContext[] {
                        b
                }, JBJGLGameManager.PLAY
        );
        JBJGLGameEngine.newWindowed(
                "Example", width, height, JBJGLImage.create(20, 20),
                true, false, manager, manager, manager,
                60.0, 60.0, 5);
    }
}
