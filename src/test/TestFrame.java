package test;

import javax.swing.*;
import java.awt.*;

public class TestFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Button Color");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 100);
            frame.setLayout(new FlowLayout());

            JButton testButton = new JButton("Test");
            testButton.setFont(new Font("Arial", Font.BOLD, 12));
            testButton.setForeground(Color.white); // Set the text color to white
            testButton.setBackground(Color.gray); // Set the background color to gray
            testButton.setOpaque(false); // Make sure the background color is visible
            testButton.setBorderPainted(false);

            frame.add(testButton);
            frame.setVisible(true);
        });
    }
}
