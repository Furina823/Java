package pages.home.SysAdmin;

import datamodel.Emp;
import pages.MyPanel;
import utility.DisplayJoption;
import utility.FontUtils;
import utility.RoundedButton;
import utility.TextFileModifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterContent extends JPanel {

    private JPanel buttonPanel;
    private RoundedButton addButton = new RoundedButton("Add",Color.white);
    private JTextField emailText;
    private JPasswordField passwordText;

    public RegisterContent(String department) {
        this.setLayout(new BorderLayout()); // Set BorderLayout for main panel
        this.add(initializePanel(department));
    }

    public RegisterContent(Emp emp, String department) {
        this.setLayout(new BorderLayout()); // Set BorderLayout for main panel
        this.add(initializePanel(department));
        this.setBackground(Color.red);
        emailText.setEditable(false);
        emailText.setText(emp.getEmpEmail());
        emailText.setFocusable(false);
        passwordText.setEditable(false);
        passwordText.setFocusable(false);
        passwordText.setText(emp.getEmpPassword());
        changeButton(emp);
    }

    private JPanel initializePanel(String department) {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setPreferredSize(new Dimension(750, 80));
        addButton.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.black));

        // Create and align the title
        JPanel registerPanel = new JPanel();
        JLabel title = new JLabel("Employee Registration");

        registerPanel.add(title);
        registerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        registerPanel.setMaximumSize(new Dimension(750,16));
        title.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(14f,Color.white));
        registerPanel.setBackground(new Color(47,47,47));

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(47,47,47));
        contentPanel.setPreferredSize(new Dimension(750,100));
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        JLabel email = new JLabel("Email Account:");
        email.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.white));
        emailText = new JTextField(15);
        emailText.setBackground(Color.black);
        emailText.setCaretColor(Color.white);
        emailText.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));

        JLabel password = new JLabel("Password:");
        password.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.white));
        passwordText = new JPasswordField(15);
        passwordText.setBackground(Color.black);
        passwordText.setCaretColor(Color.white);
        passwordText.setForeground(Color.white);

        JPanel leftContentPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        leftContentPanel.add(email);
        leftContentPanel.add(emailText);
        leftContentPanel.add(password);
        leftContentPanel.add(passwordText);
        leftContentPanel.setBackground(new Color(47,47,47));

        buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.setPreferredSize(new Dimension(180,40));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(47,47,47));


        JPanel rightContentPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        addButton.addActionListener(e -> onAdd(department));
        rightContentPanel.add(buttonPanel);;
        rightContentPanel.setBackground(new Color(47,47,47));

        contentPanel.add(leftContentPanel);
        contentPanel.add(rightContentPanel);
        containerPanel.setBackground(Color.black);

        containerPanel.add(registerPanel); // Add title to the container panel
        containerPanel.add(contentPanel); // Add content to the container panel
        containerPanel.setBackground(Color.black);

        return containerPanel;
    }

    private void changeButton(Emp emp) {
        addButton.setText("Edit");
        for (ActionListener al : addButton.getActionListeners()) {
            addButton.removeActionListener(al);
        }
        addButton.addActionListener(e -> {
            emailText.setEditable(true);
            passwordText.setEditable(true);
            emailText.setFocusable(true);
            passwordText.setFocusable(true);
            AddButtons(emp);
        });
    }

    private void AddButtons(Emp em){

        RoundedButton saveButton = new RoundedButton("Save",Color.white);
        saveButton.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.black));

        RoundedButton cancelButton = new RoundedButton("Cancel",Color.white);
        cancelButton.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.black));

        saveButton.addActionListener((e)-> onSave(em));
        cancelButton.addActionListener((e) -> onCancel(em));

        buttonPanel.removeAll();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        buttonPanel.repaint();
        buttonPanel.revalidate();

    }

    private JButton formatButton(String text){

        JButton button = new JButton(text);
        button.setFont(FontUtils.getPoppinsFontWithColor(10f,Color.white));
        button.setPreferredSize(new Dimension(70,25));
        button.setBackground(Color.black);
        return button;

    }

    private void onCancel(Emp e){

        buttonPanel.removeAll();
        buttonPanel.add(addButton);
        buttonPanel.repaint();
        buttonPanel.revalidate();

        emailText.setEditable(false);
        emailText.setFocusable(false);

        passwordText.setEditable(false);
        passwordText.setFocusable(false);

        emailText.setText(e.getEmpEmail());
        passwordText.setText(e.getEmpPassword());

    }

    private void onSave(Emp emp){

        String newemail = emailText.getText();
        String newpassword = new String(passwordText.getPassword());

        String[] newcontent = {
                newemail,newpassword,emp.getIsBan(),emp.getDateJoin(),emp.getDateLeave(),
            emp.getSalary(),emp.getPosition(),emp.getDepartment(),emp.getRole()
        };

        if(newemail.contains(",") || newemail.contains(";") || newemail.contains(" ")
        || newpassword.contains(",") || newpassword.contains(";") || newpassword.contains(" ")
        ){
            JOptionPane.showMessageDialog(null,"','/';' and empty space are not allowed, the email and password will be reset to the initial value", "Error Input", JOptionPane.WARNING_MESSAGE);
            emailText.setText(emp.getEmpEmail());
            passwordText.setText(emp.getEmpPassword());
        }else{
            TextFileModifier tfm = new TextFileModifier("employee");
            tfm.updateRecord(emp.getEmpID(), newcontent);
            this.repaint();
            this.revalidate();

            emp.setEmpEmail(newemail);
            emp.setEmpPassword(newpassword);

            onCancel(emp);

        }

    }

    private void onAdd(String department){

        String empID = "";
        String newemail = emailText.getText();
        String newpassword = new String(passwordText.getPassword());

        Emp em = new Emp();

        boolean valid = true;

        for(Emp e : em.getEmpRecords()){
            if(e.getEmpEmail().equals(newemail)){
                DisplayJoption.showMessage("Email had been registered");
                MyPanel.replaceRightPanel(new Register(department));
                valid = false;
                break;
            }else if(newemail.contains(",") || newemail.contains(";") || newemail.isEmpty()){
                DisplayJoption.showMessage("Email cannot contain ',' or ';' and cannot be empty space");
                MyPanel.replaceRightPanel(new Register(department));
                valid = false;
                break;
            }else if(newpassword.contains(",") || newpassword.contains(";") || newpassword.isEmpty()){
                DisplayJoption.showMessage("Password cannot be empty, contain ',' and ';'");
                MyPanel.replaceRightPanel(new Register(department));
                valid = false;
                break;
            }
        }

        if(valid) {

            // Employee
            String[] array = {newemail, newpassword, "null", "null", "null", "null", "null", department, "null"};
            TextFileModifier tfm = new TextFileModifier("employee");
            tfm.createRecord(array);

            Emp emp = new Emp();
            for (Emp e : emp.getEmpRecords()) {
                if (e.getEmpEmail().equals(newemail)) {
                    empID = e.getEmpID();
                } else {
                    empID = null;
                }
            }

            // Personal Information
            String[] info = {empID, "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", newemail, "null"};
            tfm = new TextFileModifier("personal_information");
            tfm.manualCreateRecord(info);

            String[] nok = {empID, "null", "null", "null"};
            tfm = new TextFileModifier("next_of_kin");
            tfm.createRecord(nok);

            String[] background = {empID, "null", "null", "null", "null", "null", "null"};
            tfm = new TextFileModifier("background");
            tfm.manualCreateRecord(background);

            String[] leave = {empID, "2024", "null", "null", "null", "null", "null"};
            tfm = new TextFileModifier("leave");
            tfm.createRecord(leave);

            // Payslip not settled yet
            String[] paid = {empID, "null", "null", "null", "null", "null", "null",
                    "null", "null", "null", "null", "null", "null", "null", "null"};
            tfm = new TextFileModifier("paid");
            tfm.createRecord(paid);

            String[] salary = {empID, "null", "null", "null"};
            tfm = new TextFileModifier("salary");
            tfm.createRecord(salary);

            DisplayJoption.showMessage("An new account had created");
            MyPanel.replaceRightPanel(new Register(department));

        }
    }

}
