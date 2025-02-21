package com.jordanbunke.delta_time.io;

import com.jordanbunke.delta_time.image.GameImage;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;

public class GIFReadTest {
    @Test
    public void readGIF() {
        final GameImage[] frames = GameImageIO.readGIFAsFrames(
                Path.of("C:\\Users\\Jordan Bunke\\Videos\\LICEcap\\tdsm\\lock-layers.gif"));

        if (frames == null)
            Assert.fail();

        Assert.assertEquals(185, frames.length);
    }
}
