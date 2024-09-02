package pages.home.Payroll;

import datamodel.Emp;
import datamodel.PersonalInfo;
import pages.MyPanel;
import pages.home.SysAdmin.Departments;
import pages.profile.FirstPage.Header;
import pages.profile.panelBuilder;
import utility.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class AddSalaryPage extends JPanel{

    private JPanel gridBagPanel;
    private JPanel buttonPanel;
    private Emp emp;

    Departments dep = new Departments();
    String[] depart = dep.getUniqueDepartments().toArray(new String[0]);

    public AddSalaryPage(String empID) {


    MyPanel.setButtonAction(MyPanel.createListenerEvent(new MonthPage("",empID)));

    this.emp = Emp.getRecord(empID);

    PersonalInfo info = PersonalInfo.getPersonalInfo(empID);

    JLabel titleLabel = new JLabel(info.getFirstname() +" " + info.getLastname());
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
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.WEST;
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
        for (
    Map.Entry<String, String> entry : maps.entrySet()) {
        String label = entry.getKey();
        String value = entry.getValue();

        panelBuilder builder = new panelBuilder(label);
        builder.setTextContent(value); // Set the text content
            if(builder.getLabelText().equals("Current Salary:")){
                builder.setTextEditable(false);
            }
        gridBagPanel.add(builder, gbc);
        gbc.gridy++;

    }

    RoundedButton button = new RoundedButton("Edit",Color.black);
    button.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.white));

    button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Validation();
        }
    });
    gbc.gridy = 4;
    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.EAST;
    gridBagPanel.add(button, gbc);

    panelBuilder builder = new panelBuilder("Increment Description:");
    gbc.gridy = 2;
    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.CENTER;
    gridBagPanel.add(builder, gbc);

    panelBuilder builder2 = new panelBuilder("Bonus Description:");
    gbc.gridy = 3;
    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.CENTER;
    gridBagPanel.add(builder2, gbc);


    Header header = new Header();
        header.setPreferredSize(new Dimension(0,50));

    JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(30,0));
        leftPanel.setBackground(new Color(47,47,47));

    JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(50,0));
        rightPanel.setBackground(new Color(47,47,47));

    JPanel contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(850,500));
        contentPanel.add(gridBagPanel);
        contentPanel.setBackground(new Color(47,47,47));

        this.setLayout(new BorderLayout()); // Set layout to BorderLayout
        this.add(header, BorderLayout.NORTH); // Add Header at the top
        this.add(contentPanel, BorderLayout.CENTER); // Add GridBagPanel in the center
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);

}

private static LinkedHashMap<String, String> getStringStringLinkedHashMap(Emp emp) {
    LinkedHashMap<String, String> maps = new LinkedHashMap<>();

    maps.put("Current Salary:", emp.getSalary());
    maps.put("Increment Salary:", "");
    maps.put("Bonus:", "");

    return maps;

}

private void Validation(){

        boolean valid = true;
        String[] array = new String[4];
        int i = 0;

        for(Component c : gridBagPanel.getComponents()){
            if(c instanceof panelBuilder){
                panelBuilder builder = (panelBuilder) c;

                if(builder.getLabelText().equals("Increment Salary:") || builder.getLabelText().equals("Bonus:")){

                    if(builder.getTextField().getText().isEmpty()){builder.getTextField().setText("0");}

                    if(!TextValidator.isNumber(builder.getTextField().getText())){
                        DisplayJoption.showMessage("Text is not Allowed in Increment Salary and Bonus");
                        valid = false;
                        break;
                    }else {
                        array[i] = builder.getTextField().getText();
                        i++;
                    }

                } else if (builder.getLabelText().equals("Increment Description:") || builder.getLabelText().equals("Bonus Description:")) {

                    if(!TextValidator.doesNotContainComma(builder.getTextField().getText())){
                        DisplayJoption.showMessage("Comma is not allowed in Increment Description and Bonus Description");
                        valid = false;
                        break;
                    }else {
                        array[i] = builder.getTextField().getText();
                        i++;
                    }
                }
            }
        }

        if (valid){

            MyPanel.replaceRightPanel(new PaySlipGenerator(emp.getEmpID(),array));
        }

}


}
