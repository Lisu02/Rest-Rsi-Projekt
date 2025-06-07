package org.example.restrsiprojekt.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    /**
     * @param filename is a path to an image file
     * @throws IOException when no file was found at provided location
     *
     */
    public Image loadImageFromFilename(String filename) throws IOException {
        try {
            File file = new File(filename);
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
