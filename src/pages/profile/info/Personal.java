package pages.profile.info;

import datamodel.PersonalInfo;
import pages.profile.FirstPage.Header;
import pages.profile.panelBuilder;
import rolemodel.BaseModel;
import utility.FontUtils;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Personal extends JPanel {

    private JPanel gridBagPanel;
    private PersonalInfo personalinfo;

    public Personal(String empID){

        personalinfo = new PersonalInfo();
        PersonalInfo info = personalinfo.getPersonalInfo(empID);

        JLabel titleLabel = new JLabel("Employee Information");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700,40));
        titleLabel.setMinimumSize(new Dimension(700,40));
        titleLabel.setPreferredSize(new Dimension(700,40));
        titleLabel.setBackground(new Color(47,47,47));

        LinkedHashMap<String, String> maps = getStringStringLinkedHashMap(info);


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
                builder.setTextContent(value); // Set the text content
                gridBagPanel.add(builder, gbc);
                gbc.gridwidth = 1; // Reset gridwidth for next items
                gbc.gridy++; // Move to the next row
            } else {
                panelBuilder builder = new panelBuilder(label);
                builder.setTextContent(value); // Set the text content
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


    public Personal(BaseModel baseModel) {
        // Initialize title label

        PersonalInfo info = baseModel.getEmpPersonalInformation();

        JLabel titleLabel = new JLabel("Employee Information");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700,40));
        titleLabel.setMinimumSize(new Dimension(700,40));
        titleLabel.setPreferredSize(new Dimension(700,40));
        titleLabel.setBackground(new Color(47,47,47));

        LinkedHashMap<String, String> maps = getStringStringLinkedHashMap(info);

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
                gridBagPanel.add(builder, gbc);
                gbc.gridwidth = 1; // Reset gridwidth for next items
                gbc.gridy++; // Move to the next row
            } else {
                panelBuilder builder = new panelBuilder(label);
                builder.setTextContent(value); // Set the text content
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
