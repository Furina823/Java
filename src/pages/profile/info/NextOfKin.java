package pages.profile.info;

import datamodel.EmpNOK;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class NextOfKin extends JPanel {

    private final JPanel gridBagPanel;
    private int line;
    private ArrayList<LinkedHashMap<String, String>> maps;

    public NextOfKin(String empID){

        MyPanel.setButtonAction(MyPanel.createListenerEvent(new ProfileCategories(empID)));

        List<EmpNOK> nok = EmpNOK.getRecordByEmpID(empID);

        JLabel titleLabel = new JLabel("    Employee Next of Kin");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700,40));
        titleLabel.setMinimumSize(new Dimension(700,40));
        titleLabel.setPreferredSize(new Dimension(700,40));
        titleLabel.setBackground(new Color(47,47,47));

        maps = new ArrayList<>();

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

            line = maps.size();

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String label = entry.getKey();
                String value = entry.getValue();

                panelBuilder builder = new panelBuilder(label);
                builder.setTextContent(value); // Set the text content
                gridBagPanel.add(builder, gbc);

                addIconsBasedOnConditions(builder,gbc);

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

        RoundedButton saveButton = new RoundedButton("Save",Color.white);
        saveButton.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.black));
        saveButton.addActionListener(_ -> onSave(empID));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.setBackground(new Color(47,47,47));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        bottomPanel.setPreferredSize(new Dimension(0,150));
        bottomPanel.add(buttonPanel);
        bottomPanel.setBackground(new Color(47,47,47));

        this.setLayout(new BorderLayout()); // Set layout to BorderLayout
        this.add(header, BorderLayout.NORTH); // Add Header at the top
        this.add(contentPanel, BorderLayout.CENTER); // Add GridBagPanel in the center
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }

    private void handleAddIcon(panelBuilder builder) {
        builder.setIcontoIconLabel(builder.getAddIcon());
        builder.addIconLabel();
        builder.getIconLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                addAction();
                builder.removeIconLabel();
                gridBagPanel.revalidate();
                gridBagPanel.repaint();
            }
        });
    }

    private void handleBinIcon(panelBuilder builder) {
        builder.setIcontoIconLabel(builder.getBinIcon());
        builder.addIconLabel();
        builder.getIconLabel().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                binAction();
                gridBagPanel.revalidate();
                gridBagPanel.repaint();
            }
        });
    }

    private void addIconsBasedOnConditions(panelBuilder builder, GridBagConstraints gbc) {
        if (line < 2 && gbc.gridx == 1) {
            handleAddIcon(builder);
        } else if (line == 2 && gbc.gridx == 1 && gbc.gridy == 2) {
            handleBinIcon(builder);
        }
    }

    private void addAction(){

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridwidth = 1;
            gbc.gridy = 2;
            line = 2;

            for (gbc.gridx = 0; gbc.gridx <= 2; gbc.gridx++) {
                panelBuilder builder = new panelBuilder("");
                gridBagPanel.add(builder, gbc);
                addIconsBasedOnConditions(builder,gbc);
            }

    }

    private void binAction() {
        // Remove components at gridy = 2
        removeComponentAtGridY(gridBagPanel);

        // Adjust the line count and remove the last map entry
        line = 1;
        if (!maps.isEmpty()) {
            maps.removeLast();
        }

        // Revalidate and repaint the gridBagPanel
        gridBagPanel.revalidate();
        gridBagPanel.repaint();

        // Check for the component at gridy = 1 and gridx = 1 to set the Add icon
        for (Component component : gridBagPanel.getComponents()) {
            GridBagConstraints gbc = ((GridBagLayout) gridBagPanel.getLayout()).getConstraints(component);
            if (gbc.gridy == 1 && gbc.gridx == 1) {
                if (component instanceof panelBuilder builder) {
                    handleAddIcon(builder); // Set the Add icon instead of the Bin icon
                }
            }
        }
    }

    public void onSave(String empID) {
        boolean valid;
        String[] textFieldTextsAtGridY1 = getTextFieldTextsAtGridY(gridBagPanel, 1);

        boolean hasComponentsAtGridY2 = hasComponentsAtGridY(gridBagPanel);

        valid = isValid(true);
        if (hasComponentsAtGridY2) {
            // Iterate through components of gridBagPanel

            // Proceed only if all validations passed
            if (valid) {
                List<EmpNOK> nokRecords = EmpNOK.getRecordByEmpID(empID);
                TextFileModifier tfm = new TextFileModifier("next_of_kin");
                String[] newTextFieldTexts = getTextFieldTextsAtGridY(gridBagPanel, 2);

                // Join array elements into a single string without brackets
                String concatenatedText = String.join(",", newTextFieldTexts);
                String result = empID +","+concatenatedText;

                if (nokRecords.size() > 1) {
                    // Update existing record
                    updateNOK(empID, textFieldTextsAtGridY1);
                    tfm.updateRecord(nokRecords.getLast().getKinID(),new String[]{result});
                } else {
                    // Create new record
                    updateNOK(empID, textFieldTextsAtGridY1);
                    tfm.createRecord(new String[]{result});
                }
            }
        } else {
            // Iterate through components of gridBagPanel
            // Proceed only if all validations passed
            if (valid) {
                List<EmpNOK> nokRecords = EmpNOK.getRecordByEmpID(empID);
                TextFileModifier tfm = new TextFileModifier("next_of_kin");
                getTextFieldTextsAtGridY(gridBagPanel, 2);

                // Join array elements into a single string without brackets

                updateNOK(empID, textFieldTextsAtGridY1);
                // Further processing if needed

                if(nokRecords.size() > 1) {
                    tfm.deleteRecord(nokRecords.getLast().getKinID());
                }

            }
        }
    }

    private boolean isValid(boolean valid) {
        for (Component builder : gridBagPanel.getComponents()) {
            if (builder instanceof panelBuilder b) {
                String text = b.getTextField().getText();

                if (!ValidateText(text)) {
                    valid = false; // Set valid to false if validation fails
                    break; // Exit the loop as validation has failed
                }
            }
        }
        return valid;
    }

    private boolean ValidateText(String text){

        if(text.contains(",") || text.isEmpty()){
            DisplayJoption.showMessage("Information cannot include ',' or it cannot be an empty value");
            return false;
        }
        return true;
    }

    private void updateNOK(String empID, String[] content) {
        TextFileModifier tfm = new TextFileModifier("next_of_kin");

        List<EmpNOK> lists = EmpNOK.getRecordByEmpID(empID);

        // Join array elements into a single string without brackets
        String concatenatedContent = String.join(",", content);
        String[] newContent = {empID, concatenatedContent};

        // Update the record with the new content
        tfm.updateRecord(lists.getFirst().getKinID(), newContent);
        DisplayJoption.showMessage("Employee Next of kin had updated");
    }


    private String[] getTextFieldTextsAtGridY(JPanel panel, int targetGridY) {
        List<String> textContents = new ArrayList<>();

        for (Component component : panel.getComponents()) {
            GridBagConstraints gbc = ((GridBagLayout) panel.getLayout()).getConstraints(component);
            if (gbc.gridy == targetGridY && gbc.gridx >= 0 && gbc.gridx <= 2) {
                if (component instanceof panelBuilder) {
                    textContents.add(((panelBuilder) component).getTextField().getText());
                }
            }
        }

        return textContents.toArray(new String[0]);
    }

    // Method to check if there are any components at a specific gridy
    private boolean hasComponentsAtGridY(JPanel panel) {
        for (Component component : panel.getComponents()) {
            GridBagConstraints gbc = ((GridBagLayout) panel.getLayout()).getConstraints(component);
            if (gbc.gridy == 2) {
                return true; // A component with the specified gridy exists
            }
        }
        return false; // No component with the specified gridy was found
    }

    private void removeComponentAtGridY(Container container) {
        for (Component component : container.getComponents()) {
            GridBagConstraints gbc = ((GridBagLayout) container.getLayout()).getConstraints(component);
            if (gbc.gridy == 2) {
                container.remove(component);
            }
        }
    }

    public NextOfKin(BaseModel baseModel) {
        // Initialize title label

        List<EmpNOK> nok = baseModel.getEmpNextOfKin();

        JLabel titleLabel = new JLabel("    Employee Next of Kin");
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
                builder.setTextEditable(false);
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
