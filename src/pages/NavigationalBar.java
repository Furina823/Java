package pages;

import pages.leave.FirstPage.LeavePanel;
import rolemodel.BaseModel;
import utility.iconResizeHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavigationalBar extends JPanel {

    public NavigationalBar(MyPanel myPanel, JPanel panel, BaseModel bm) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.black);

        String[] icons = {"home.png", "walking.png", "profile.png", "schedule.png", "logout1.png"};
        iconResizeHandler[] iconResizeHandlers = new iconResizeHandler[icons.length];
        for(int i = 0; i < icons.length; i++){
            iconResizeHandlers[i] = new iconResizeHandler(50,50,new ImageIcon("src/picture/" + icons[i]));
        }

        // Adding action labels to the navigation bar
        this.add(createActionLabel(iconResizeHandlers[0].getImageIcon(),"Home", 70, () -> {
            MyPanel.replaceRightPanel(panel); bm.reset();}));
        this.add(createActionLabel(iconResizeHandlers[1].getImageIcon(),"Leave", 70, () -> MyPanel.replaceRightPanel(new LeavePanel(bm))));
        this.add(createActionLabel(iconResizeHandlers[2].getImageIcon(),"Profile", 70, () -> MyPanel.replaceRightPanel(new ProfilePanel(bm))));
        this.add(createActionLabel(iconResizeHandlers[3].getImageIcon(),"TimeTable", 70, () -> MyPanel.replaceRightPanel(new TimeTablePanel(bm))));
        this.add(createActionLabel(iconResizeHandlers[4].getImageIcon(),"Logout", 70, () -> MyPanel.replaceRightPanel(new Logout(bm))));
         
        this.add(Box.createVerticalGlue());

    }

    private static JLabel createActionLabel(ImageIcon icon, String text, int height, Runnable action) {
        JLabel label = new JLabel(text, SwingConstants.LEFT);
        Dimension size = new Dimension(Integer.MAX_VALUE, height);
        label.setIcon(icon);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        label.setMaximumSize(size);
        label.setPreferredSize(new Dimension(250, height));
        label.setMinimumSize(new Dimension(250, height));
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(Color.white);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add mouse listener to create hover shadow effect and handle click
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 5, 0, 0, Color.white),
                        BorderFactory.createEmptyBorder(4, 4, 4, 4)
                ));
                label.setOpaque(true);
                label.setBackground(new Color(51, 51, 51));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                label.setOpaque(false);
                label.setBackground(null);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 5, 0, 0, Color.white),
                        BorderFactory.createEmptyBorder(4, 4, 4, 4)
                ));
                label.setOpaque(true);
                label.setBackground(new Color(51, 51, 51));
                action.run();
            }
        });
        return label;
    }
}
