package utility;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BorderUtils {

    // Static method to create a border with a specified color, thickness, and padding
    public static Border createCustomBorder(Color borderColor, int thickness, int padding) {
        // Create a line border with the specified color and thickness
        Border lineBorder = new LineBorder(borderColor, thickness);

        // Create an empty border for padding
        Border emptyBorder = new EmptyBorder(padding, padding, padding, padding);

        // Combine the line border and the empty border
        return new CompoundBorder(lineBorder, emptyBorder);
    }

    public static void main(String[] args) {
        // Example usage
        JFrame frame = new JFrame("Border Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderUtils.createCustomBorder(Color.BLACK, 2, 5));

        JLabel label = new JLabel("This is a label with a custom border.");
        label.setBorder(BorderUtils.createCustomBorder(Color.RED, 1, 3));

        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
    }
}

