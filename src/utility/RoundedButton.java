package utility;

import javax.swing.JButton;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private int radius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;
        setOpaque(false); // Necessary for custom painting
        setContentAreaFilled(false); // Prevents the button's background from being painted
        setFocusPainted(false); // Prevents the focus border from being painted
        setBorderPainted(false); // Prevents the button border from being painted
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));

        // Text
        FontMetrics fm = g2d.getFontMetrics();
        Rectangle textRect = new Rectangle(0, 0, getWidth(), getHeight());
        int textX = (textRect.width - fm.stringWidth(getText())) / 2;
        int textY = (textRect.height - fm.getHeight()) / 2 + fm.getAscent();
        g2d.setColor(getForeground());
        g2d.drawString(getText(), textX, textY);

        g2d.dispose();
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width = Math.max(size.width, size.height);
        return size;
    }
}