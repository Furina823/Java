package pages;

import rolemodel.BaseModel;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    private static JSplitPane splitPane;
    private static JPanel rightPanel;
    private static NavigationalBar nav;

    public MyPanel(JPanel panel, BaseModel bm) {

        nav = new NavigationalBar(this, panel, bm);
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.red);

        rightPanel = new JPanel();
        rightPanel.setBackground(Color.blue);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, nav, panel);
        splitPane.setDividerLocation(250);
        splitPane.setResizeWeight(0.0);
        splitPane.setBackground(Color.black);

        splitPane.setDividerSize(0);
        this.setLayout(new FlowLayout());
        this.add(splitPane, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(1100, 600));

    }

    public static void replaceRightPanel(JPanel newPanel) {

        splitPane.setRightComponent(newPanel);
        rightPanel = newPanel;
        rightPanel.setPreferredSize(new Dimension(850, 600));
        splitPane.revalidate();
        splitPane.repaint();

    }



}