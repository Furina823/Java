package pages.leave.FirstPage;

import utility.FontUtils;

import javax.swing.*;
import java.awt.*;

public class LeaveHeader extends JPanel {

    public LeaveHeader(){

        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel title = new JLabel("Leave:");
        title.setFont(FontUtils.getPoppinsFontWithColor(24f,Color.white));

        JPanel headerPanel = new JPanel();
        headerPanel.add(title);
        headerPanel.setOpaque(false);

        this.setPreferredSize(new Dimension(700,50));
        this.setOpaque(true);
        this.setBackground(Color.black);
        this.add(headerPanel);


    }


}
