package pages.profile.info;

import pages.home.SysAdmin.*;
import rolemodel.BaseModel;
import rolemodel.SysAdministrator;
import utility.EmptyPanel;

import javax.swing.*;
import java.awt.*;

public class Historical extends JPanel {

    private JLabel headerLabel;

    public Historical(BaseModel model) {

        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        flowPanel.add(new ACHeader("Historical"));
        flowPanel.add(new History_Label("Position",model));
        flowPanel.add(new History_Label("Salary Increment",model));
        flowPanel.setBackground(Color.black);
        flowPanel.setOpaque(true);

        this.setLayout(new BorderLayout());
        this.add(flowPanel, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(830, 600));
        this.setBackground(Color.black);

    }

}