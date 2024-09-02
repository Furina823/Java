package calendar;

import rolemodel.BaseModel;
import utility.FontUtils;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class CalendarGUI extends JPanel {

    JPanel leftPanel;
    JPanel rightPanel;
    JPanel gridPanel;
    JLabel titleLabel;
    JPanel leftBackgroundPanel;
    JPanel bottomBackgroundPanel;
    JPanel rightBackgroundPanel;
    RightCalendarGUI rightCalendarGUI;

    public CalendarGUI(BaseModel bm) {

        rightCalendarGUI = new RightCalendarGUI(bm);

        titleLabel = new JLabel("          Time Table:");
        titleLabel.setFont(FontUtils.getPoppinsFontWithColor(20f,Color.white));

        titleLabel.setForeground(Color.white);
        titleLabel.setBackground(Color.black);
        titleLabel.setOpaque(true);
        titleLabel.setPreferredSize(new Dimension(0,50));

        leftBackgroundPanel = new JPanel();
        leftBackgroundPanel.setPreferredSize(new Dimension(50,0));
        leftBackgroundPanel.setBackground(Color.black);
        leftBackgroundPanel.setOpaque(true);

        rightBackgroundPanel = new JPanel();
        rightBackgroundPanel.setPreferredSize(new Dimension(50,0));
        rightBackgroundPanel.setBackground(Color.black);
        rightBackgroundPanel.setOpaque(true);

        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout());

        leftPanel.add(new Cells(bm, rightCalendarGUI));
        leftPanel.add(new Legends());

        leftPanel.setBackground(new Color(47,47,47));
        leftPanel.setBorder(new MatteBorder(0, 0, 0, 1, Color.WHITE));
        leftPanel.setOpaque(true);

        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(47,47,47));
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
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.CENTER);


    }

}
