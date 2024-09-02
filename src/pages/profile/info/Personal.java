package pages.profile.info;

import datamodel.PersonalInfo;
import pages.MyPanel;
import pages.home.HROfficer.ProfileCategories;
import pages.profile.FirstPage.Header;
import pages.profile.panelBuilder;
import rolemodel.BaseModel;
import utility.DisplayJoption;
import utility.FontUtils;
import utility.RoundedButton;
import utility.TextFileModifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class Personal extends JPanel {

    private JPanel gridBagPanel;
    private LinkedHashMap<String, String> maps;
    private RoundedButton button;
    private RoundedButton saveButton;
    private RoundedButton cancelButton;
    private JPanel buttonPanel;

    public Personal(String empID){

        PersonalInfo personalinfo = new PersonalInfo();
        PersonalInfo info = personalinfo.getPersonalInfo(empID);

        MyPanel.setButtonAction(MyPanel.createListenerEvent(new ProfileCategories(empID)));

        JLabel titleLabel = new JLabel("Employee Information");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700,40));
        titleLabel.setMinimumSize(new Dimension(700,40));
        titleLabel.setPreferredSize(new Dimension(700,40));
        titleLabel.setBackground(new Color(47,47,47));

        maps = getStringStringLinkedHashMap(info);

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

            if(value.equals("null")){
                value = "";
            }

            if (label.equals("Mailing Address:")) {
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                panelBuilder builder = new panelBuilder(label);
                builder.setTextField(55);
                builder.setTextEditable(false);
                builder.setTextContent(value); // Set the text content
                gridBagPanel.add(builder, gbc);
                gbc.gridwidth = 1; // Reset gridwidth for next items
                gbc.gridy++; // Move to the next row
            } else {

                panelBuilder builder = new panelBuilder(label);

                if(label.equals("E-mail:")){
                    builder.setTextEditable(false);
                }

                builder.setTextContent(value); // Set the text content
                builder.setTextEditable(false);
                gridBagPanel.add(builder, gbc);
                gbc.gridx++;
                if (gbc.gridx > 2) {
                    gbc.gridx = 0;
                    gbc.gridy++; // Move to the next row

                }
            }
        }

        Header header = new Header();
        header.setPreferredSize(new Dimension(0,50));

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(30,0));
        leftPanel.setBackground(new Color(47,47,47));

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(20,0));
        rightPanel.setBackground(new Color(47,47,47));

        JPanel bottomPanel = getPanel(empID);

        JPanel contentPanel = new JPanel();
        contentPanel.add(gridBagPanel);
        contentPanel.setBackground(new Color(47,47,47));
        contentPanel.add(bottomPanel);

        this.setLayout(new BorderLayout()); // Set layout to BorderLayout
        this.add(header, BorderLayout.NORTH); // Add Header at the top
        this.add(contentPanel, BorderLayout.CENTER); // Add GridBagPanel in the center
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);

    }

    private JPanel getPanel(String empID) {
        button = new RoundedButton("Edit",Color.white);
        button.setFont(FontUtils.getPoppinsFontWithColor(14f, Color.black));

        ActionListener listener = e -> setEdit(empID);
        button.addActionListener(listener);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(770, 100));
        bottomPanel.setBackground(new Color(47,47,47));

        // Creating a panel to hold the button and align it to the right
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(47,47,47)); // Keep the same background color
        buttonPanel.add(button);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        return bottomPanel;
    }

    private void setEdit(String empID){

        saveButton = new RoundedButton("Save",Color.black);
        saveButton.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.white));
        saveButton.addActionListener(e -> onSave(empID));
        cancelButton = new RoundedButton("Cancel",Color.white);
        cancelButton.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.black));
        cancelButton.addActionListener(e -> onCancel());

        for(Component builder : gridBagPanel.getComponents()){

            if(builder instanceof panelBuilder){

                if(!((panelBuilder) builder).getLabelText().equals("E-mail:")){
                    ((panelBuilder) builder).setTextEditable(true);
                    buttonPanel.remove(button);
                    buttonPanel.add(saveButton);
                    buttonPanel.add(cancelButton);
                }

            }

        }

    }

    private void onCancel(){
        for(Component builder : gridBagPanel.getComponents()){
            if (builder instanceof panelBuilder){
                panelBuilder pb = (panelBuilder) builder;
                String v = maps.get(pb.getLabelText());
                if(v.equals("null")){
                    v = "";
                }
                pb.setTextContent(v);
                pb.setTextEditable(false);
            }
        }
        buttonPanel.removeAll();
        buttonPanel.revalidate();
        buttonPanel.repaint();
        buttonPanel.add(button);
    }

    private void onSave(String empID){
        setNext(empID);
        buttonPanel.removeAll();
        buttonPanel.revalidate();
        buttonPanel.repaint();
        buttonPanel.add(button);
    }

    public void setNext(String empID) {
        boolean allValid = true;

        // Iterate over the components in the gridBagPanel
        for (Component builder : gridBagPanel.getComponents()) {
            if (builder instanceof panelBuilder) {
                panelBuilder pb = (panelBuilder) builder;
                String newValue = pb.getTextField().getText();

                // Validate the text content
                if (!validateText(newValue)) {
                    allValid = false;
                    break;
                } else {
                    // Update the map with the new value if validation passes
                    maps.put(pb.getLabelText(), newValue);
                    pb.setTextEditable(false);
                }
            }
        }

        // If all fields are valid, perform the necessary action
        if (allValid) {
            // Clear the maps and update PersonalInfo with new data
            PersonalInfo infos = new PersonalInfo();
            infos.setEmpid(empID);
            infos.setFirstname(maps.get("First Name:"));
            infos.setLastname(maps.get("Last Name:"));
            infos.setNric(maps.get("NRIC:"));
            infos.setGender(maps.get("Gender:"));
            infos.setDate_of_birth(maps.get("DOB:"));
            infos.setPassport(maps.get("Passport:"));
            infos.setPlace_of_birth(maps.get("Place:"));
            infos.setRace(maps.get("Race:"));
            infos.setReligious(maps.get("Religious:"));
            infos.setContact_number(maps.get("Contact No.:"));
            infos.setEmail(maps.get("E-mail:"));
            infos.setMarital_status(maps.get("Marital Status:"));
            infos.setMailing_address(maps.get("Mailing Address:"));
            onValidatePass(infos);
        }{
            MyPanel.replaceRightPanel(new Personal(empID));
        }
    }

    public boolean validateText(String s){

        for(Component builder : gridBagPanel.getComponents()){
            if(builder instanceof panelBuilder){
                panelBuilder pb = (panelBuilder) builder;
                if(pb.getLabelText().equals("Gender:")){
                    if (!pb.getTextField().getText().equals("Female") && !pb.getTextField().getText().equals("Male")){
                        DisplayJoption.showMessage("Gender must be 'Male' or 'Female'");
                        return false;
                    }
                }
            }
        }


        if(s.contains(",")){
            DisplayJoption.showMessage("Information cannot include ,");
            return false;
        }else if(s.isEmpty()){
            DisplayJoption.showMessage("Information cannot be a null value");
            return false;
        }
        return true;
    }

    private void onValidatePass(PersonalInfo info){
        TextFileModifier tfm = new TextFileModifier("personal_information");
        String[] content = {
                info.getFirstname(),info.getLastname(),info.getNric(),info.getPassport(),
                info.getGender(),info.getDate_of_birth(),info.getPlace_of_birth(),info.getRace(),
                info.getReligious(),info.getContact_number(),info.getMailing_address(),info.getEmail(),
                info.getMarital_status()
        };
        tfm.updateRecord(info.getEmpid(),content);
        DisplayJoption.showMessage("Employee Personal Information Updated");
    }


    public Personal(BaseModel baseModel) {
        // Initialize title label

        PersonalInfo info = baseModel.getEmpPersonalInformation();

        JLabel titleLabel = new JLabel("    Employee Information");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700,40));
        titleLabel.setMinimumSize(new Dimension(700,40));
        titleLabel.setPreferredSize(new Dimension(700,40));
        titleLabel.setBackground(new Color(47,47,47));

        maps = getStringStringLinkedHashMap(info);

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

            if (label.equals("Mailing Address:")) {
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                panelBuilder builder = new panelBuilder(label);
                builder.setTextField(55);
                builder.setTextContent(value); // Set the text content
                builder.setTextEditable(false);
                gridBagPanel.add(builder, gbc);
                gbc.gridwidth = 1; // Reset gridwidth for next items
                gbc.gridy++; // Move to the next row
            } else {
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

        this.setLayout(new BorderLayout()); // Set layout to BorderLayout
        this.add(header, BorderLayout.NORTH); // Add Header at the top
        this.add(contentPanel, BorderLayout.CENTER); // Add GridBagPanel in the center
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);

    }

    private static LinkedHashMap<String, String> getStringStringLinkedHashMap(PersonalInfo info) {
        LinkedHashMap<String, String> maps = new LinkedHashMap<>();

        maps.put("First Name:", info.getFirstname());
        maps.put("Last Name:", info.getLastname());
        maps.put("NRIC:", info.getNric());
        maps.put("Gender:", info.getGender());
        maps.put("DOB:", info.getDate_of_birth());
        maps.put("Passport:", info.getPassport());
        maps.put("Place:", info.getPlace_of_birth());
        maps.put("Race:", info.getRace());
        maps.put("Religious:", info.getReligious());
        maps.put("Contact No.:", info.getContact_number());
        maps.put("E-mail:", info.getEmail());
        maps.put("Marital Status:", info.getMarital_status());
        maps.put("Mailing Address:", info.getMailing_address());
        return maps;
    }
}
