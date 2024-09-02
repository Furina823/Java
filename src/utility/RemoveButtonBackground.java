package utility;

import javax.swing.JButton;
import java.awt.*;

public class RemoveButtonBackground {


    public static void makeTransparent(JButton button, Color color) {
        button.setContentAreaFilled(true);   // Allow the content area to be filled (to show the background color)
        button.setBorderPainted(false);      // Optional: Remove the border if needed
        button.setBackground(color);         // Set the desired background color
        button.setFocusPainted(false);

    }

}
