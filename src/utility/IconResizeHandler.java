package utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class IconResizeHandler {

    final private ImageIcon icon;

    public IconResizeHandler(int width, int height, ImageIcon icon) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive numbers.");
        }
        Image image = icon.getImage();
        Image scaledImage = getScaledImage(image, width, height);
        this.icon = new ImageIcon(scaledImage);
    }

    public IconResizeHandler(int width, int height, Image image) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive numbers.");
        }
        Image scaledImage = getScaledImage(image, width, height);
        this.icon = new ImageIcon(scaledImage);
    }

    private Image getScaledImage(Image srcImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();

        // Set rendering hints for high-quality rendering
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the image with the scaling applied
        g2d.drawImage(srcImage, 0, 0, width, height, null);
        g2d.dispose();

        return resizedImage;
    }

    public ImageIcon getImageIcon() {
        return this.icon;
    }
}
