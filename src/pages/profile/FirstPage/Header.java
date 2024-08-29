package pages.profile.FirstPage;

import pages.home.SysAdmin.AC;
import utility.FontUtils;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel{

    private JLabel headerLabel;

    public Header(){

        headerLabel = new JLabel();
        headerLabel.setFont(FontUtils.getPoppinsFontWithColor(18f,Color.white));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        setHeaderLabel("Profile:");
        this.add(headerLabel);
        this.setPreferredSize(new Dimension(700,50));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setBackground(Color.black);

    }

    private void setHeaderLabel(String text){

        headerLabel.setText(text);

    }

}
