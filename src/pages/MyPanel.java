package pages;

import rolemodel.BaseModel;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    private final JSplitPane splitPane;
    private JPanel rightPanel;

    public MyPanel(JPanel panel, BaseModel bm) {

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.red);

        rightPanel = new JPanel();
        rightPanel.setBackground(Color.blue);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new NavigationalBar(this, panel, bm), panel);
        splitPane.setDividerLocation(250);
        splitPane.setResizeWeight(0.0);
        splitPane.setBackground(Color.black);

        splitPane.setDividerSize(0);
        this.setLayout(new BorderLayout());
        this.add(splitPane, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(1100, 600));
    }

    public void replaceRightPanel(JPanel newPanel) {
        splitPane.setRightComponent(newPanel);
        rightPanel = newPanel;
        rightPanel.setPreferredSize(new Dimension(400, 600));
        splitPane.revalidate();
        splitPane.repaint();
    }
}
