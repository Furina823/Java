package pages.profile.info;

import datamodel.Emp;
import pages.MyPanel;
import pages.home.SysAdmin.Departments;
import pages.profile.FirstPage.Header;
import pages.profile.panelBuilder;
import rolemodel.BaseModel;
import utility.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Account extends JPanel {

    private JPanel gridBagPanel;
    private JPanel buttonPanel;
    private Emp emp;
    private RoundedButton editButton;
    private JPanel bottomPanel;
    private BaseModel model;

    private String[] role = {"Department Manager",
    "Employee","Human Resource Officer", "Payroll Officer"};

    Departments dep = new Departments();
    String[] depart = dep.getUniqueDepartments().toArray(new String[0]);

    public Account(String empID){

        revalidate();
        repaint();
        emp = Emp.getRecord(empID);

        depart = ReArrangeArray.rearrangeArray(depart, emp.getDepartment());

        role = ReArrangeArray.rearrangeArray(role, emp.getRole());

        JLabel titleLabel = new JLabel("    Account");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700,40));
        titleLabel.setMinimumSize(new Dimension(700,40));
        titleLabel.setPreferredSize(new Dimension(700,40));
        titleLabel.setBackground(new Color(47,47,47));

        LinkedHashMap<String, String> maps = getStringStringLinkedHashMap(emp);

// Initialize GridBagPanel with GridBagLayout
        gridBagPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gridBagPanel.setBackground(new Color(47, 47, 47));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;

// Add title label
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = GridBagConstraints.REMAINDER; // Spans entire width
        gridBagPanel.add(titleLabel, gbc);

        gbc.weighty = 1.0;
        gbc.gridwidth = 1; // Reset to default
        gbc.gridy = 1;
        gbc.gridx = 0;

// Add labels and components
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            String label = entry.getKey();
            String value = entry.getValue();

            panelBuilder builder = new panelBuilder(label);

            builder.setTextContent(value); // Set the text content
            if(label.equals("Department:")){
                builder.setComboBox(depart);
            }else if(label.equals("Role:")){
                builder.setComboBox(role);
            }
            builder.setTextEditable(false);

            gridBagPanel.add(builder, gbc);
            gbc.gridx++;
            if (gbc.gridx > 2) {
                gbc.gridx = 0;
                gbc.gridy++; // Move to the next row
            }

        }

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setPreferredSize(new Dimension(0,100));
        buttonPanel.add(saveButton());
        buttonPanel.add(cancelButton());

        Header header = new Header();
        header.setPreferredSize(new Dimension(0,50));

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(30,0));
        leftPanel.setBackground(new Color(47,47,47));

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(50,0));
        rightPanel.setBackground(new Color(47,47,47));

        JPanel contentPanel = new JPanel();
        contentPanel.add(gridBagPanel);
        contentPanel.setBackground(new Color(47,47,47));

        this.setLayout(new BorderLayout()); // Set layout to BorderLayout
        this.add(header, BorderLayout.NORTH); // Add Header at the top
        this.add(contentPanel, BorderLayout.CENTER); // Add GridBagPanel in the center
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(buttonPanel, BorderLayout.SOUTH);

    }

    private JButton saveButton(){

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(_->{onSave();});
        return saveButton;
    }

    private JButton cancelButton(){
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(_->{
            MyPanel.replaceRightPanel(new Account(emp.getEmpID()));
        });
        return cancelButton;
    }

    private void onSave(){

        String[] array = new String[2];

        for(Component component : gridBagPanel.getComponents()){
            if(component instanceof panelBuilder builder){
                if(builder.getLabelText().equals("Department:")){
                    array[0] = builder.getComboBox().getSelectedItem().toString();
                }else if(builder.getLabelText().equals("Role:")){
                    array[1] = builder.getComboBox().getSelectedItem().toString();
                }
            }
        }
        boolean valid = !array[1].equals("null");

        if(valid) {

            array = new String[]{emp.getEmpEmail(), emp.getEmpPassword(),
                    emp.getIsBan(), emp.getDateJoin(), emp.getDateLeave(),
                    emp.getSalary(), emp.getPosition(), array[0], array[1]};

            TextFileModifier tfm = new TextFileModifier("employee");
            tfm.updateRecord(emp.getEmpID(), array);

            MyPanel.replaceRightPanel(new Account(emp.getEmpID()));
        } else {
            DisplayJoption.showMessage("Please select a valid role");
            MyPanel.replaceRightPanel(new Account(emp.getEmpID()));
        }
    }

    public Account(BaseModel baseModel) {
        // Initialize title label

        emp = baseModel.getEmpCompany();
        model = baseModel;

        JLabel titleLabel = new JLabel("    Account");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700,40));
        titleLabel.setMinimumSize(new Dimension(700,40));
        titleLabel.setPreferredSize(new Dimension(700,40));
        titleLabel.setBackground(new Color(47,47,47));

        LinkedHashMap<String, String> maps = getStringStringLinkedHashMap(emp);

// Initialize GridBagPanel with GridBagLayout
        gridBagPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gridBagPanel.setBackground(new Color(47, 47, 47));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;

// Add title label
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = GridBagConstraints.REMAINDER; // Spans entire width
        gridBagPanel.add(titleLabel, gbc);

        gbc.weighty = 1.0;
        gbc.gridwidth = 1; // Reset to default
        gbc.gridy = 1;
        gbc.gridx = 0;

// Add labels and components
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            String label = entry.getKey();
            String value = entry.getValue();

            panelBuilder builder = new panelBuilder(label);
            builder.setTextContent(value); // Set the text content
            builder.setTextEditable(false);
            gridBagPanel.add(builder, gbc);
            gbc.gridx++;
            if (gbc.gridx > 2) {
                gbc.gridx = 0;
                gbc.gridy++; // Move to the next row
            }

        }

        Header header = new Header();
        header.setPreferredSize(new Dimension(0,50));

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(30,0));
        leftPanel.setBackground(new Color(47,47,47));

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(50,0));
        rightPanel.setBackground(new Color(47,47,47));

        JPanel contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(700,500));
        contentPanel.add(gridBagPanel);
        contentPanel.setBackground(new Color(47,47,47));

        editButton = new RoundedButton("Edit Password", Color.black);
        editButton.setFont(FontUtils.getPoppinsFontWithColor(14f, Color.white));
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEditPassword();
            }
        });

        bottomPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        bottomPanel.setBackground(new Color(47,47,47));
        bottomPanel.setPreferredSize(new Dimension(700,100));
        bottomPanel.add(editButton);

        this.setLayout(new BorderLayout()); // Set layout to BorderLayout
        this.add(header, BorderLayout.NORTH); // Add Header at the top
        this.add(contentPanel, BorderLayout.CENTER); // Add GridBagPanel in the center
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }

    private void onEditPassword(){

        for(Component c : gridBagPanel.getComponents()){
            if(c instanceof  panelBuilder){
                if (((panelBuilder)c).getLabelText().equals("Password:")){
                    ((panelBuilder) c).setTextEditable(true);
                }
            }
        }
        bottomPanel.remove(editButton);


        RoundedButton saveButton = new RoundedButton("Save", Color.black);
        saveButton.setFont(FontUtils.getPoppinsFontWithColor(14f, Color.white));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSavePassword();
            }
        });

        RoundedButton cancelButton = new RoundedButton("Cancel", Color.black);
        cancelButton.setFont(FontUtils.getPoppinsFontWithColor(14f, Color.white));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        bottomPanel.add(saveButton);
        bottomPanel.add(cancelButton);

        revalidate();
        repaint();

    }

    private void onCancel(){
        MyPanel.replaceRightPanel(new Account(model));
    }

    private void onSavePassword(){

        String password = "";

        TextFileModifier tfm = new TextFileModifier("employee");
        for(Component component : gridBagPanel.getComponents()){
            if(component instanceof panelBuilder){
                if(((panelBuilder)component).getLabelText().equals("Password:")){
                    password = ((panelBuilder) component).getTextField().getText();
                }
            }
        }
        boolean valid = true;
        if (password.contains(",") || password.isEmpty()){
            valid = false;
            DisplayJoption.showMessage("Password cannot be an empty value or include ','");
            onCancel();
        }

        if(valid) {
            emp.setEmpPassword(password);
            String[] content = {
                    emp.getEmpEmail(), password, emp.getIsBan(),
                    emp.getDateJoin(), emp.getDateLeave(), emp.getSalary(),
                    emp.getPosition(), emp.getDepartment(), emp.getRole()
            };

            tfm.updateRecord(emp.getEmpID(), content);
            DisplayJoption.showMessage("Password had updated!");
            onCancel();
        }

    }

    private static LinkedHashMap<String, String> getStringStringLinkedHashMap(Emp emp) {
        LinkedHashMap<String, String> maps = new LinkedHashMap<>();

        maps.put("Email:", emp.getEmpEmail());
        maps.put("Password:", emp.getEmpPassword());
        maps.put("Position:", emp.getPosition());
        maps.put("Department:", emp.getDepartment());
        maps.put("Role:", emp.getRole());

        return maps;

    }
}
