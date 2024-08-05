package test;

import java.awt.*;
import javax.swing.border.AbstractBorder;

public class CustomRoundedBorder extends AbstractBorder {
    private final int thickness;
    private final Color color;
    private final int radius;
    private final int additionalWidth; // New field for extending the underline width

    public CustomRoundedBorder(int thickness, Color color, int radius, int additionalWidth) {
        this.thickness = thickness;
        this.color = color;
        this.radius = radius;
        this.additionalWidth = additionalWidth;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int underlineY = height - thickness / 2;
        int underlineXStart = x - additionalWidth / 2;
        int underlineXEnd = x + width + additionalWidth / 2;

        g2d.drawLine(underlineXStart + radius, underlineY, underlineXEnd - radius, underlineY);

        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, thickness, 0);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = 0;
        insets.top = 0;
        insets.bottom = thickness;
        return insets;
    }
}
