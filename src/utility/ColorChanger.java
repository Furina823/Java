package utility;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorChanger {

    private Color newColor;

    public ColorChanger(Color newColor) {
        this.newColor = newColor;
    }

    public BufferedImage changeColor(BufferedImage image) {
        BufferedImage result = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                if ((pixel >> 24) != 0x00) { // Check if the pixel is not transparent
                    result.setRGB(x, y, newColor.getRGB());
                } else {
                    result.setRGB(x, y, pixel);
                }
            }
        }

        return result;
    }
}
