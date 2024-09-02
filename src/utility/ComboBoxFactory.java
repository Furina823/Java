package utility;

import javax.swing.*;

public class ComboBoxFactory {

    // Method that returns a JComboBox based on a String array
    public static JComboBox<String> createComboBox(String[] items) {
        // Create a JComboBox with the provided String array
        return new JComboBox<>(items);
    }
}