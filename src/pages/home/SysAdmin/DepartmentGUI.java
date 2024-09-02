package pages.home.SysAdmin;

import pages.MyPanel;
import utility.EmptyPanel;
import utility.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DepartmentGUI extends JPanel {

    private JLabel headerLabel;
    private JPanel contentPanel;

    public JLabel getNext() {
        return next;
    }

    private JLabel next;

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
        contentPanel.setBackground(Color.black);

        for(String s : dep.getUniqueDepartments()){

            contentPanel.add(new EmptyPanel());
            contentPanel.add(new DepartmentLabel(s));

        }

        contentPanel.add(formatlabel(""));
        contentPanel.add(formatlabel(""));

        next = formatlabel("Set Standardized Working Hour");
        contentPanel.add(next);
        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MyPanel.replaceRightPanel(new SetWorkingHour());
            }
        });


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

    private JLabel formatlabel(String text){

        JLabel label = new JLabel();

        label.setBorder(new RoundedBorder(Color.black, 2,10));
        label.setText(text);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
        label.setBackground(Color.black);
        label.setOpaque(true);
        label.setFont(new Font("Poppins", Font.PLAIN,20));
        label.setForeground(Color.white);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));

        return label;

    }

}
