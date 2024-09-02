package utility;

import javax.swing.*;
import java.awt.*;

public class ScrollPaneUtil {

    public static JScrollPane createScrollPane(JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(850,400));
        scrollPane.setBackground(Color.black);
        scrollPane.setOpaque(true);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.setBackground(null);
        verticalBar.setUI(new InvisibleScrollBarUI());

        JScrollBar horizontalBar = scrollPane.getHorizontalScrollBar();
        horizontalBar.setBackground(null);
        horizontalBar.setUI(new InvisibleScrollBarUI());

        return scrollPane;
    }


}
