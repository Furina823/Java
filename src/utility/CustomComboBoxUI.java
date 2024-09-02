package utility;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class CustomComboBoxUI extends BasicComboBoxUI {
    private static final Color ARROW_COLOR = Color.WHITE; // Set the arrow color here

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);

        // Draw custom arrow icon
        JComboBox<?> comboBox = (JComboBox<?>) c;
        int width = comboBox.getWidth();
        int height = comboBox.getHeight();
        int arrowWidth = 16; // Width of the arrow
        int arrowHeight = 10; // Height of the arrow

        // Arrow position
        int x = width - arrowWidth - 10;
        int y = (height - arrowHeight) / 2;

        // Draw the arrow
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(ARROW_COLOR);
        int[] xPoints = {x, x + arrowWidth, x + arrowWidth / 2};
        int[] yPoints = {y, y, y + arrowHeight};
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.dispose();
    }
}