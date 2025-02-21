package com.jordanbunke.delta_time.io;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.math.Coord2D;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class GameImageIO {
    public static GameImage readImage(final Path filepath) {
        try {
            return new GameImage(ImageIO.read(filepath.toFile()));
        } catch (IOException e) {
            GameError.send("Couldn't read image file: " + filepath);
        }

        return null;
    }

    public static void writeImage(final Path filepath, final GameImage image) {
        try {
            ImageIO.write(image, "PNG", filepath.toFile());
        } catch (IOException e) {
            GameError.send("Couldn't write to image file: " + filepath);
        }
    }

    public static GameImage[] readGIFAsFrames(final Path filepath) {
        try {
            ImageReader reader = ImageIO
                    .getImageReadersBySuffix("gif").next();
            ImageInputStream in = ImageIO
                    .createImageInputStream(filepath.toFile());
            reader.setInput(in);

            final int fc = reader.getNumImages(true);
            final GameImage[] frames = new GameImage[fc];

            final GameImage master = new GameImage(reader.read(0));
            frames[0] = master;

            for (int i = 1; i < fc; i++) {
                final GameImage frame = new GameImage(frames[i - 1]),
                        change = new GameImage(reader.read(i));

                final IIOMetadata metadata = reader.getImageMetadata(i);
                final Coord2D pos = getGIFFrameCoord(metadata);

                frame.draw(change, pos.x, pos.y);
                frames[i] = frame.submit();
            }

            return frames;
        } catch (IOException e) {
            GameError.send("Couldn't read GIF file: " + filepath);
        }

        return null;
    }

    private static Coord2D getGIFFrameCoord(final IIOMetadata metadata) {
        final String LEFT_CODE = "imageLeftPosition",
                TOP_CODE = "imageTopPosition";

        final Node tree = metadata.getAsTree("javax_imageio_gif_image_1.0");
        final NodeList children = tree.getChildNodes();

        for (int j = 0; j < children.getLength(); j++) {
            final Node child = children.item(j);

            if (child.getNodeName().equals("ImageDescriptor")) {
                final NamedNodeMap attr = child.getAttributes();

                final Node leftNode = attr.getNamedItem(LEFT_CODE),
                        topNode = attr.getNamedItem(TOP_CODE);

                return new Coord2D(
                        Integer.parseInt(leftNode.getNodeValue()),
                        Integer.parseInt(topNode.getNodeValue()));
            }
        }

        return new Coord2D();
    }
}
