package pages.home.SysAdmin;

import datamodel.Emp;
import pages.MyPanel;
import utility.FontUtils;
import utility.TextFileModifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ACBody_List extends JPanel {

    private JButton actionButton;
    private Emp emp;
    private TextFileModifier tfm;
    private JButton viewButton;

    public ACBody_List(String username, String empID) {
        emp = Emp.getRecord(empID);
        tfm = new TextFileModifier("employee");

        // Left panel with user information
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEADING)); // Vertical layout
        leftPanel.setBackground(new Color(47, 47, 47));

        JLabel usernameLabel = new JLabel(username);
        usernameLabel.setFont(FontUtils.getPoppinsFontWithColor(12f, Color.white));
        JLabel IDLabel = new JLabel(empID);
        IDLabel.setFont(FontUtils.getPoppinsFontWithColor(12f, Color.white));

        leftPanel.add(usernameLabel);
        leftPanel.add(IDLabel);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Add padding

        // Right panel (contains the action button)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.TRAILING)); // Horizontal layout
        rightPanel.setBackground(new Color(47, 47, 47));

        viewButton = new JButton("View");
        viewButton.addActionListener((e)->{
            MyPanel.replaceRightPanel(new Register(emp.getDepartment(),emp));
        });

        actionButton = new JButton();
        updateButton(); // Set initial button text and functionality

        rightPanel.add(viewButton);
        rightPanel.add(actionButton);

        // Set layout and add panels
        this.setLayout(new BorderLayout());
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);

        // Set preferred and maximum sizes for scrolling
        this.setPreferredSize(new Dimension(700, 50));
        this.setMaximumSize(new Dimension(700, 50));
        this.setBackground(new Color(47, 47, 47));
    }

    private void updateButton() {
        // Determine if the account is banned
        boolean isBanned = emp.getIsBan().equals("1");

        for (ActionListener al : actionButton.getActionListeners()){
            actionButton.removeActionListener(al);
        }

        if (isBanned) {
            actionButton.setText("Unlock");
            actionButton.addActionListener(e -> unlockAccount());
        } else {

            actionButton.setText("Lock");
            actionButton.addActionListener(e -> lockAccount());
        }
    }

    private void unlockAccount() {
        // Update the account status to unlock
        String[] newContent = {
                emp.getEmpEmail(), emp.getEmpPassword(),
                "0", emp.getDateJoin(), emp.getDateLeave(), emp.getSalary(),
                emp.getPosition(), emp.getDepartment(), emp.getRole()
        };

        tfm.updateRecord(emp.getEmpID(), newContent);
        emp.setIsBan("0"); // Update local record if necessary
        updateButton(); // Refresh the button state
        System.out.println("Account unlocked");
    }

    private void lockAccount() {
        // Update the account status to lock
        String[] newContent = {
                emp.getEmpEmail(), emp.getEmpPassword(),
                "1", emp.getDateJoin(), emp.getDateLeave(), emp.getSalary(),
                emp.getPosition(), emp.getDepartment(), emp.getRole()
        };

        tfm.updateRecord(emp.getEmpID(), newContent);
        emp.setIsBan("1"); // Update local record if necessary
        updateButton(); // Refresh the button state
        System.out.println("Account locked");
    }
}
