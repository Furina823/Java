package test;

import rolemodel.BaseModel;

import javax.swing.*;
import java.awt.*;

public class CalendarGUI extends JPanel {

    JPanel leftPanel;
    JPanel rightPanel;
    JLabel leftLabel;
    JPanel gridPanel;
    JLabel titleLabel;
    JPanel leftBackgroundPanel;
    JPanel bottomBackgroundPanel;
    JPanel rightBackgroundPanel;
    RightCalendarGUI rightCalendarGUI = new RightCalendarGUI();

    public CalendarGUI(BaseModel bm) {

        titleLabel = new JLabel("                         Time Table:");

        titleLabel.setForeground(Color.white);
        titleLabel.setBackground(Color.black);
        titleLabel.setOpaque(true);
        titleLabel.setPreferredSize(new Dimension(0,50));

        leftBackgroundPanel = new JPanel();
        leftBackgroundPanel.setPreferredSize(new Dimension(50,0));
        leftBackgroundPanel.setBackground(Color.black);
        leftBackgroundPanel.setOpaque(true);

        bottomBackgroundPanel = new JPanel();
        bottomBackgroundPanel.setPreferredSize(new Dimension(0,50));
        bottomBackgroundPanel.setBackground(Color.black);
        bottomBackgroundPanel.setOpaque(true);

        rightBackgroundPanel = new JPanel();
        rightBackgroundPanel.setPreferredSize(new Dimension(50,0));
        rightBackgroundPanel.setBackground(Color.black);
        rightBackgroundPanel.setOpaque(true);

        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout());

        leftPanel.add(new Cells(bm, rightCalendarGUI));
        leftPanel.add(new Legends());

        leftPanel.setBackground(Color.gray);
        leftPanel.setOpaque(true);

        rightPanel = new JPanel();
        rightPanel.setBackground(Color.lightGray);
        rightPanel.setOpaque(true);
        rightPanel.add(rightCalendarGUI);
        rightPanel.setLayout(new FlowLayout());

        gridPanel = new JPanel();

        gridPanel.setLayout(new GridLayout(1,1));
        gridPanel.add(leftPanel);
        gridPanel.add(rightPanel);

        this.setLayout(new BorderLayout());
        this.add(leftBackgroundPanel, BorderLayout.WEST);
        this.add(rightBackgroundPanel, BorderLayout.EAST);
        this.add(bottomBackgroundPanel, BorderLayout.SOUTH);
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.CENTER);


    }

}
