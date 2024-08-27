package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DateButtonsPanel extends JPanel {

    private static final int TOTAL_CELLS = 42; // 6 rows * 7 columns
    private List<JButton> buttons;
    RightCalendarGUI rightCalendarGUI;

    public DateButtonsPanel(RightCalendarGUI rightCalendarGUI) {

        this.rightCalendarGUI = rightCalendarGUI;

        setLayout(new GridLayout(6, 7)); // 6 rows, 7 columns
        buttons = new ArrayList<>(TOTAL_CELLS);

        // Initialize all 42 buttons
        for (int i = 0; i < TOTAL_CELLS; i++) {
            JButton button = createButton();
            buttons.add(button);
            add(button);
        }
    }

    public void colorUnderlineBorder(int index,Color color){
        if (index >= 0 && index < buttons.size()) {
            JButton button = buttons.get(index);
            button.setBorder(BorderFactory.createCompoundBorder(
                    new CustomRoundedBorder(4, color,18,18, CustomRoundedBorder.BorderSide.BOTTOM),
                    BorderFactory.createEmptyBorder(3, 3, 3, 3)
            ));
            button.setOpaque(true);
            button.setBackground(Color.gray);
        }
    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setOpaque(true); // Required for background color
        button.setBackground(Color.gray); // Set the background color
        button.setContentAreaFilled(true); // Required for background color
        return button;
    }

    public void updateButton(int index, String text, boolean isCurrentMonth,String monthYear) {
        if (index >= 0 && index < buttons.size()) {
            JButton button = buttons.get(index);
            button.setText(text);

            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }

            // Apply font color based on whether the day is from the current month or not
            if (isCurrentMonth) {
                button.setForeground(Color.white); // Font color for current month days
                button.addActionListener( e -> this.rightCalendarGUI.updateDate(text + " " + monthYear));

            } else {
                button.setForeground(Color.white); // Font color for other month days
            }
        }
    }

    public void setButtonEnabled(int index, boolean enabled) {
        if (index >= 0 && index < buttons.size()) {
            buttons.get(index).setEnabled(enabled);

        }
    }

}
