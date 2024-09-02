package utility;

import javax.swing.*;

public class DisplayJoption {

    // Private constructor to prevent instantiation
    private DisplayJoption() {}

    // Static method to display the message
    public static void showMessage(String information) {
        JOptionPane.showMessageDialog(null, information);
    }

    public static void showMessage(String information, String title) {
        JOptionPane.showMessageDialog(null, information, title, JOptionPane.INFORMATION_MESSAGE);
    }

}
