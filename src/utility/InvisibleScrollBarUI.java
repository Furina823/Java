package utility;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class InvisibleScrollBarUI extends BasicScrollBarUI {
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        // Do not paint the thumb
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // Do not paint the track
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        // Return dimension with zero width/height for invisible scrollbar
        return new Dimension(0, 0);
    }
}

