package pages.home.SysAdmin;

import utility.FontUtils;

import javax.swing.*;
import java.awt.*;

public class ACHeader extends JPanel {

    private JLabel title;
    private JLabel description;
    private JPanel headerPanel;

    public ACHeader(String department){

        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,0));

        title = new JLabel("Account Management");
        title.setFont(FontUtils.getPoppinsFontWithColor(24f,Color.white));
        title.setBackground(Color.black);

        description = new JLabel(department);
        description.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f,Color.white));
        description.setBackground(Color.black);

        headerPanel = new JPanel();
        headerPanel.add(title);
        headerPanel.add(description);
        headerPanel.setLayout(new BoxLayout(headerPanel,BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.black);

        this.add(headerPanel);
        this.setBackground(Color.black);

    }

    public void setTitle(String text){
        title.setText(text);
    }

    public void removeDescription(){
        headerPanel.remove(description);
    }

}
