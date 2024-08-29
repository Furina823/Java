package pages.home.SysAdmin;

import rolemodel.SysAdministrator;
import utility.EmptyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class DepartmentGUI extends JPanel {

    private JLabel headerLabel;
    private JPanel contentPanel;

    public DepartmentGUI() {

        Departments dep = new Departments();

        headerLabel = new JLabel("   Account Management:");
        headerLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        headerPanel.add(headerLabel);
        headerPanel.setBackground(Color.black);
        headerPanel.setPreferredSize(new Dimension(850, 50));

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setPreferredSize(new Dimension(850,600));
        contentPanel.setBackground(null);

        for(String s : dep.getUniqueDepartments()){

            contentPanel.add(new EmptyPanel());
            contentPanel.add(new DepartmentLabel(s));

        }

        this.setLayout(new BorderLayout());
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);

        this.setBackground(Color.black);

    }

    public void setHeaderLabel(String s){
        headerLabel.setText(s);
    }

    public JPanel getContentPanel(){
        return contentPanel;
    }


}
