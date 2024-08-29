package pages.profile.info;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

import pages.profile.FirstPage.Header;
import pages.profile.panelBuilder;
import rolemodel.BaseModel;
import utility.*;
import datamodel.EmpBackground;

public class Background extends JPanel {

    private JPanel gridBagPanel;

    public Background(BaseModel baseModel) {
        // Initialize title label
        EmpBackground background = baseModel.getEmpBackground();

        JLabel titleLabel = new JLabel("Employee Information");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700, 40));
        titleLabel.setMinimumSize(new Dimension(700, 40));
        titleLabel.setPreferredSize(new Dimension(700, 40));
        titleLabel.setBackground(new Color(47, 47, 47));

        LinkedHashMap<String, String> maps = getStringStringLinkedHashMap(background);

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

        // Manually construct the layout based on known data
        addPanelBuilder("Education Level:", background.getEducationLevel(), gbc);
        addPanelBuilder("Working Experience:", background.getWorkingExperience(), gbc);

        addPanelBuilder("Professional Certification:", background.getProfessionalCertification(), gbc);
        addPanelBuilder("Skills:", background.getSkills(), gbc);

        addPanelBuilder("Professional Membership:", background.getProfessionalMembership(), gbc);
        addPanelBuilder("Language:", background.getLanguageProficiency(), gbc);

        Header header = new Header();
        header.setPreferredSize(new Dimension(0, 50));

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(30, 0));
        leftPanel.setBackground(new Color(47, 47, 47));

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(50, 0));
        rightPanel.setBackground(new Color(47, 47, 47));

        JPanel contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(700, 500));
        contentPanel.add(gridBagPanel);
        contentPanel.setBackground(new Color(47, 47, 47));

        this.setLayout(new BorderLayout()); // Set layout to BorderLayout
        this.add(header, BorderLayout.NORTH); // Add Header at the top
        this.add(contentPanel, BorderLayout.CENTER); // Add GridBagPanel in the center
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
    }

    private void addPanelBuilder(String label, String value, GridBagConstraints gbc) {
        // Split the value if there is a semicolon
        String[] items = value.split(";");

        int rowCount = 1;

        for (String item : items) {
            panelBuilder builder = new panelBuilder(label);
            builder.setTextContent(item.trim()); // Set the text content

            gbc.gridy++; // Set row for current item
            gridBagPanel.add(builder, gbc);
            rowCount++;
        }

        if(label.equals("Working Experience:") || label.equals("Skills:")) {
            // Move to the next row after all items for this label have been added
            gbc.gridx++;
            gbc.gridy = 1;
        }

    }

    private static LinkedHashMap<String, String> getStringStringLinkedHashMap(EmpBackground background) {
        LinkedHashMap<String, String> maps = new LinkedHashMap<>();

        // Example values; adjust according to actual data
        maps.put("Education Level:", background.getEducationLevel()); // E.g., "Level 1; Level 2; Level 3"
        maps.put("Professional Certification:", background.getProfessionalCertification()); // E.g., "Cert 1; Cert 2"
        maps.put("Professional Membership:", background.getProfessionalMembership()); // E.g., "Membership 1"
        maps.put("Working Experience:", background.getWorkingExperience()); // E.g., "Job 1; Job 2"
        maps.put("Skills:", background.getSkills()); // E.g., "Skill 1; Skill 2"
        maps.put("Language:", background.getLanguageProficiency()); // E.g., "Language 1; Language 2"

        return maps;
    }
}