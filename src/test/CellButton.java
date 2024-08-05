package test;

import javax.swing.*;
import java.awt.*;

public class CellButton extends JButton {

    public CellButton(String text) {
        super(text);
        setFont(new Font("Arial", Font.PLAIN, 16));
        setForeground(Color.white);
        setMargin(new Insets(5, 5, 5, 5));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
}
