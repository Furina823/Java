package calendar;

import javax.swing.*;
import java.awt.*;

public class DaysHeaderPanel extends JPanel {

    private static final String[] DAYS = {"M", "T", "W", "T", "F", "S", "S"};

    public DaysHeaderPanel() {
        setLayout(new GridLayout(1, 7)); // 1 row, 7 columns

        for (String day : DAYS) {
            JButton button = createButton(day);
            add(button);
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton();
        button.setText("<html><font color='white'>" + text + "</font></html>");
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Font size and style
        button.setForeground(Color.white);
        button.setOpaque(true);
        button.setBackground(new Color(47,47,47)); // Background color for day headers
        button.setMargin(new Insets(5, 5, 5, 5));
        button.setFocusable(false);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        return button;
    }
}
