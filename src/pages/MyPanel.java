package pages;

import rolemodel.BaseModel;
import utility.ColorChanger;
import utility.FontUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MyPanel extends JPanel {

    private static JSplitPane splitPane;
    private static JPanel rightPanel;
    private static NavigationalBar nav;
    private static JPanel headerPane;
    private static JButton undoButton;
    private static JLabel appName;

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

        headerPane = new JPanel(new FlowLayout(FlowLayout.LEADING));
        headerPane.setPreferredSize(new Dimension(1100, 40));

        ImageIcon originalIcon = new ImageIcon("src/picture/back.png");
        originalIcon.setImage(originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        BufferedImage image = new BufferedImage(
                originalIcon.getIconWidth(),
                originalIcon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        originalIcon.paintIcon(null, g, 0, 0);
        g.dispose();

        ColorChanger colorChanger = new ColorChanger(Color.white);
        BufferedImage newImage = colorChanger.changeColor(image);
        ImageIcon icon = new ImageIcon(newImage);

        undoButton = new JButton();
        undoButton.setIcon(icon);
        undoButton.setContentAreaFilled(false);
        undoButton.setFocusPainted(false);
        undoButton.setBorderPainted(false);

        appName = new JLabel("As You Wish");
        appName.setFont(FontUtils.getPoppinsFontWithColor(18f, Color.white));

        headerPane.add(undoButton);
        headerPane.add(appName);
        headerPane.setOpaque(false);

        this.setOpaque(false);

        splitPane.setDividerSize(0);
        splitPane.setBackground(Color.black);

        // Set the layout to FlowLayout
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        // Add components to the panel in the order they should appear
        this.add(headerPane);
        this.add(splitPane);

        this.setPreferredSize(new Dimension(1100, 600));
        this.setBackground(Color.black);
    }

    public static void replaceRightPanel(JPanel newPanel) {
        splitPane.setRightComponent(newPanel);
        rightPanel = newPanel;
        rightPanel.setPreferredSize(new Dimension(850, 550));
        splitPane.revalidate();
        splitPane.repaint();
    }

    public static void setButtonAction(ActionListener listener) {
        // Remove all existing listeners
        for (ActionListener al : undoButton.getActionListeners()) {
            undoButton.removeActionListener(al);
        }
        // Add the new listener
        undoButton.addActionListener(listener);
    }

    // Example ActionListener implementation
    public static ActionListener createListenerEvent(JPanel panel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyPanel.replaceRightPanel(panel);
            }
        };
    }

}
