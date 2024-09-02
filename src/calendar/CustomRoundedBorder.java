package calendar;

import java.awt.*;
import javax.swing.border.AbstractBorder;

public class CustomRoundedBorder extends AbstractBorder {
    private final int thickness;
    private final Color color;
    private final int radius;
    private final int additionalWidth;
    private final BorderSide side;

    public enum BorderSide {
        BOTTOM,
        LEFT
    }

    public CustomRoundedBorder(int thickness, Color color, int radius, int additionalWidth, BorderSide side) {
        this.thickness = thickness;
        this.color = color;
        this.radius = radius;
        this.additionalWidth = additionalWidth;
        this.side = side;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (side == BorderSide.BOTTOM) {
            int underlineY = height - thickness / 2;
            int underlineXStart = x - additionalWidth / 2;
            int underlineXEnd = x + width + additionalWidth / 2;

            g2d.drawLine(underlineXStart + radius, underlineY, underlineXEnd - radius, underlineY);
        } else if (side == BorderSide.LEFT) {
            int leftX = x + thickness / 2;
            int topY = y + radius;
            int bottomY = height - radius;

            g2d.drawLine(leftX, topY, leftX, bottomY);
            if (radius > 0) {
                g2d.drawArc(leftX - radius, y, 2 * radius, 2 * radius, 90, 90);
                g2d.drawArc(leftX - radius, height - 2 * radius, 2 * radius, 2 * radius, 180, 90);
            }
        }

        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        if (side == BorderSide.BOTTOM) {
            return new Insets(0, 0, thickness, 0);
        } else if (side == BorderSide.LEFT) {
            return new Insets(0, thickness, 0, 0);
        }
        return new Insets(0, 0, 0, 0); // Default case, shouldn't be reached
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        if (side == BorderSide.BOTTOM) {
            insets.left = insets.right = insets.top = 0;
            insets.bottom = thickness;
        } else if (side == BorderSide.LEFT) {
            insets.left = thickness;
            insets.right = insets.top = insets.bottom = 0;
        }
        return insets;
    }
}
