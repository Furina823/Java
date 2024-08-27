package test;

import utility.RoundedBorder;

import javax.swing.*;
import java.awt.*;

public class RightCalendarGUI extends JPanel {

    JLabel dateLabel;

    public RightCalendarGUI() {

        JLabel titleLabel = new JLabel("Time Attendance");

        JLabel clockInTitleLabel = new JLabel("Clock in:");
        JLabel clockInLabel = new JLabel(" 00-00 ");

        JLabel clockOutTitleLabel = new JLabel("Clock out:");
        JLabel clockOutLabel = new JLabel(" 00-00 ");

        JLabel detailLabel = new JLabel("Details:");
        JPanel detailPanel = getjPanel();

        // Clock In Label
        clockInLabel.setBorder(new RoundedBorder(Color.white, 2,10));

        // Clock Out label
        clockOutLabel.setBorder(new RoundedBorder(Color.white,2,10));

        // Clock In Panel
        JPanel clockInPanel = new JPanel();
        clockInPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Align labels to the left within the panel
        clockInPanel.setBackground(Color.red);
        clockInPanel.setOpaque(true);
        clockInPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        clockInPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the panel in the main panel

        JPanel clockOutPanel = new JPanel();
        clockOutPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Align labels to the left within the panel
        clockOutPanel.setBackground(Color.blue);
        clockOutPanel.setOpaque(true);
        clockOutPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        clockOutPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the panel in the main panel

        // Adding components to the panels
        clockInPanel.add(clockInTitleLabel);
        clockInPanel.add(clockInLabel);

        clockOutPanel.add(clockOutTitleLabel);
        clockOutPanel.add(clockOutLabel);

        // Layout for the main panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 250));
        add(titleLabel);
        add(clockInPanel);
        add(clockOutPanel);
        add(detailLabel);
        add(detailPanel);
    }

    private JPanel getjPanel() {
        JPanel detailPanel = new JPanel(new GridLayout(2,3));
        JLabel dateTitleLabel = new JLabel("Date");
        JLabel descriptionTitleLabel = new JLabel("Description");
        JLabel workTimeTitleLabel = new JLabel("Work Time");
        dateLabel = new JLabel("");
        JLabel descriptionLabel = new JLabel("Holiday/Workday");
        JLabel workTimeLabel = new JLabel("xx:xx - xx:xx");

        // detail Panel
        detailPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,60));
        detailPanel.add(dateTitleLabel);
        detailPanel.add(descriptionTitleLabel);
        detailPanel.add(workTimeTitleLabel);
        detailPanel.add(dateLabel);
        detailPanel.add(descriptionLabel);
        detailPanel.add(workTimeLabel);
        return detailPanel;
    }

    public void updateDate(String date){
        dateLabel.setText(date);
        repaint();
        revalidate();
    }

}
