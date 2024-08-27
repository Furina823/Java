package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarHeader extends JPanel {

    private JLabel monthYearLabel;
    private JButton leftArrowButton;
    private JButton rightArrowButton;
    private CalendarHeaderListener listener;

    public CalendarHeader() {
        setLayout(new BorderLayout());

        monthYearLabel = new JLabel("Month - Year", SwingConstants.CENTER);
        monthYearLabel.setForeground(Color.white);
        monthYearLabel.setFont(new Font("Arial", Font.BOLD, 16));
        monthYearLabel.setPreferredSize(new Dimension(140, 30));

        leftArrowButton = new JButton("<");
        leftArrowButton.setForeground(Color.white);
        leftArrowButton.setBackground(Color.gray);
        leftArrowButton.setOpaque(true);

        rightArrowButton = new JButton(">");
        rightArrowButton.setForeground(Color.white);

        leftArrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.onPreviousMonth();
                }
            }
        });

        rightArrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.onNextMonth();
                }
            }
        });

        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        navigationPanel.add(leftArrowButton);
        navigationPanel.add(monthYearLabel);
        navigationPanel.add(rightArrowButton);
        navigationPanel.setBackground(Color.gray);

        add(navigationPanel, BorderLayout.CENTER);
    }

    public void setMonthYear(String monthYear) {
        monthYearLabel.setText(monthYear);
    }

    public void setListener(CalendarHeaderListener listener) {
        this.listener = listener;
    }

    public interface CalendarHeaderListener {
        void onPreviousMonth();
        void onNextMonth();
    }
}
