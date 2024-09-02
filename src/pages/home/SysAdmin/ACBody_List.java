package pages.home.SysAdmin;

import datamodel.Emp;
import pages.MyPanel;
import utility.FontUtils;
import utility.RoundedBorder;
import utility.RoundedButton;
import utility.TextFileModifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ACBody_List extends JPanel {

    private RoundedButton actionButton;
    private Emp emp;
    private TextFileModifier tfm;
    private RoundedButton viewButton;

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabelText(String s) {
        this.usernameLabel.setText(s);
    }

    private JLabel usernameLabel;

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    private JPanel leftPanel;

    public ActionListener getActionListener() {
        return actionListener;
    }

    private ActionListener actionListener;

    public JLabel getIDLabel() {
        return IDLabel;
    }

    private JLabel IDLabel;

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public JButton getActionButton() {
        return actionButton;
    }

    private JPanel rightPanel;

    public ACBody_List(String email, String empID) {
        emp = Emp.getRecord(empID);
        tfm = new TextFileModifier("employee");

        // Left panel with user information
        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEADING)); // Vertical layout
        leftPanel.setBackground(new Color(47, 47, 47));

        usernameLabel = new JLabel(email);
        usernameLabel.setFont(FontUtils.getPoppinsFontWithColor(16f, Color.white));
        IDLabel = new JLabel(empID);
        IDLabel.setFont(FontUtils.getPoppinsFontWithColor(16f, Color.white));

        leftPanel.add(usernameLabel);
        leftPanel.add(IDLabel);

        // Right panel (contains the action button)
        rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.TRAILING)); // Horizontal layout
        rightPanel.setBackground(new Color(47, 47, 47));

        actionListener = e -> MyPanel.replaceRightPanel(new Register(emp.getDepartment(),emp));

        viewButton = new RoundedButton("View",Color.black);
        viewButton.setFont(FontUtils.getPoppinsFontWithColor(14f, Color.white));
        viewButton.addActionListener(actionListener);

        actionButton = new RoundedButton("",Color.white);
        actionButton.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.black));
        updateButton(); // Set initial button text and functionality

        rightPanel.add(viewButton);
        rightPanel.add(actionButton);

        // Set layout and add panels
        this.setLayout(new BorderLayout());
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);

        // Set preferred and maximum sizes for scrolling
        this.setPreferredSize(new Dimension(850, 50));
        this.setMaximumSize(new Dimension(850, 50));
        this.setBackground(new Color(47, 47, 47));
        this.setBorder(new RoundedBorder(new Color(47,47,47),2,8));

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

    }
}
