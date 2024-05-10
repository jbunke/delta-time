package com.jordanbunke.delta_time.scripting;

import com.jordanbunke.delta_time.scripting.ast.nodes.function.HeadFuncNode;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static com.jordanbunke.delta_time.scripting.ScriptTestHelper.getScript;

public final class ScriptColorTests {
    private final Color
            BLACK = new Color(0, 0, 0),
            WHITE = new Color(255, 255, 255),
            RED = new Color(255, 0, 0),
            GREEN = new Color(0, 255, 0),
            BLUE = new Color(0, 0, 255);

    // TODO

    @Test
    public void colorSummationTest() {
        final HeadFuncNode script = getScript("color_sum");

        Assert.assertNotNull(script);
        Assert.assertEquals(BLACK, Interpreter.get()
                .run(script, of(BLACK, BLACK, BLACK)));
        Assert.assertEquals(WHITE, Interpreter.get()
                .run(script, of(RED, GREEN, BLUE)));
    }

    private Object of(final Color... cs) {
        return cs;
    }
}
