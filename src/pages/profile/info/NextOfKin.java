package pages.profile.info;

import datamodel.EmpNOK;
import datamodel.PersonalInfo;
import pages.profile.FirstPage.Header;
import pages.profile.panelBuilder;
import rolemodel.BaseModel;
import utility.FontUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class NextOfKin extends JPanel {

    private JPanel gridBagPanel;

    public NextOfKin(BaseModel baseModel) {
        // Initialize title label

        List<EmpNOK> nok = baseModel.getEmpNextOfKin();

        JLabel titleLabel = new JLabel("Employee Information");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700,40));
        titleLabel.setMinimumSize(new Dimension(700,40));
        titleLabel.setPreferredSize(new Dimension(700,40));
        titleLabel.setBackground(new Color(47,47,47));

        ArrayList<LinkedHashMap<String, String>> maps = new ArrayList<>();

        for (EmpNOK emp : nok) {
            LinkedHashMap<String, String> nokInfo = new LinkedHashMap<>();
            nokInfo.put("Employee Next of Kin:", emp.getEmpNextOfKinName());
            nokInfo.put("Relation:", emp.getRelationship());
            nokInfo.put("Next of Kin Contact No.:", emp.getContactNumber());
            maps.add(nokInfo);
        }

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


        for (LinkedHashMap<String, String> map : maps) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String label = entry.getKey();
                String value = entry.getValue();

                panelBuilder builder = new panelBuilder(label);
                builder.setTextContent(value); // Set the text content
                gridBagPanel.add(builder, gbc);

                gbc.gridx++;
                if (gbc.gridx > 2) {
                    gbc.gridx = 0;
                    gbc.gridy++; // Move to the next row
                }
            }

            // Reset to the next row after finishing one map
            gbc.gridx = 0;
            gbc.gridy++;
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
}
