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

    public CalendarGUI(BaseModel bm) {

        titleLabel = new JLabel("TimeTable");

        leftBackgroundPanel = new JPanel();
        leftBackgroundPanel.setPreferredSize(new Dimension(50,0));

        bottomBackgroundPanel = new JPanel();
        bottomBackgroundPanel.setPreferredSize(new Dimension(0,50));

        rightBackgroundPanel = new JPanel();
        rightBackgroundPanel.setPreferredSize(new Dimension(50,0));

        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout());

        leftPanel.add(new Cells(bm));

        leftPanel.setBackground(Color.gray);
        leftPanel.setOpaque(true);

        rightPanel = new JPanel();
        rightPanel.add(new JLabel("Right"));
        rightPanel.setBackground(Color.lightGray);
        rightPanel.setOpaque(true);

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
