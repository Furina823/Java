package pages.home.SysAdmin;

import rolemodel.SysAdministrator;
import utility.RoundedBorder;

import javax.swing.*;
import java.awt.*;

public class DepartmentGUI extends JPanel {

    public DepartmentGUI(SysAdministrator administrator,SysAddGUI panel) {

        Departments dep = new Departments();

        JLabel headerLabel = new JLabel("   Account Management:");
        headerLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        headerPanel.add(headerLabel);
        headerPanel.setBackground(Color.black);
        headerPanel.setPreferredSize(new Dimension(850, 50));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setPreferredSize(new Dimension(850,600));
        contentPanel.setBackground(null);

        for(String s : dep.getUniqueDepartments()){

            contentPanel.add(new EmptyPanel());
            contentPanel.add(new DepartmentLabel(s,panel,administrator));

        }

        this.setLayout(new BorderLayout());
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);

        this.setBackground(Color.black);

    }

}
