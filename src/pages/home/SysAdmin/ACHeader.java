package pages.home.SysAdmin;

import utility.FontUtils;

import javax.swing.*;
import java.awt.*;

public class ACHeader extends JPanel {

    private JLabel title;

    public ACHeader(String department){

        this.setLayout(new FlowLayout(FlowLayout.LEADING,10,0));

        title = new JLabel("Account Management");
        title.setFont(FontUtils.getPoppinsFont(24f));

        JLabel description = new JLabel(department);
        description.setFont(FontUtils.getPoppinsFontUnderlined(16f));

        JPanel headerPanel = new JPanel();
        headerPanel.add(title);
        headerPanel.add(description);
        headerPanel.setLayout(new BoxLayout(headerPanel,BoxLayout.Y_AXIS));

        this.add(headerPanel);

    }

    public void setTitle(String text){
        title.setText(text);
    }

}
