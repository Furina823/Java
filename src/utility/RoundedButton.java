package utility;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {

    private Color originalColor;
    private Color hoverColor;

    public RoundedButton(String text, Color color) {
        super(text);
        this.originalColor = color;

        // Set button properties
        this.setContentAreaFilled(false);  // Make the button transparent
        this.setBorderPainted(false);      // Remove the border
        this.setFocusPainted(false);       // Remove the focus border

        // Add mouse listener for hover effect
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Calculate the hover color
                int red = originalColor.getRed();
                int green = originalColor.getGreen();
                int blue = originalColor.getBlue();

                // Adjust the color based on the condition
                if (red < 128) {
                    hoverColor = new Color(Math.min(red + 128, 255),
                            Math.min(green + 128, 255),
                            Math.min(blue + 128, 255));
                } else {
                    hoverColor = new Color(Math.max(red - 128, 0),
                            Math.max(green - 128, 0),
                            Math.max(blue - 128, 0));
                }

                setHoverColor(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Revert to the original color when the mouse exits
                setHoverColor(originalColor);
            }
        });
    }

    private void setHoverColor(Color color) {
        // Update the button's color and repaint
        this.setBackground(color);
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Set rounded shape
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground()); // Use the background color
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Adjust the 20 to change the roundness

        // Draw the text
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Optional: Draw a border if needed
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20); // Adjust the 20 to match the rounding
    }
}
