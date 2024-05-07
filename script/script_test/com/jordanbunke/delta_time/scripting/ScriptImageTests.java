package com.jordanbunke.delta_time.scripting;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.HeadFuncNode;
import com.jordanbunke.delta_time.scripting.util.ScriptEquality;
import org.junit.Assert;
import org.junit.Test;

import static com.jordanbunke.delta_time.scripting.ScriptTestHelper.getImage;
import static com.jordanbunke.delta_time.scripting.ScriptTestHelper.getScript;

public final class ScriptImageTests {
    @Test
    public void reflectYTest() {
        imageOpTest("reflect_y");
    }

    @Test
    public void redChannelTest() {
        imageOpTest("red_channel");
    }

    private void imageOpTest(final String code) {
        final GameImage base = getImage("base"),
                reflectY = getImage(code);
        final HeadFuncNode script = getScript(code);
        final GameImage res = (GameImage) ScriptRunner.get().run(script, base);

        Assert.assertTrue(ScriptEquality.equal(reflectY, res));
    }
}
