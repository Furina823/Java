package pages.profile.info;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import pages.MyPanel;
import pages.home.HROfficer.ProfileCategories;
import pages.profile.FirstPage.Header;
import pages.profile.panelBuilder;
import rolemodel.BaseModel;
import utility.*;
import datamodel.EmpBackground;

public class Background extends JPanel {

    private JPanel gridBagPanel;

    public Background(String empID){

        MyPanel.setButtonAction(MyPanel.createListenerEvent(new ProfileCategories(empID)));

        EmpBackground ebg = new EmpBackground();
        EmpBackground background = ebg.getRecordByID(empID);

        JLabel titleLabel = new JLabel("    Employee Background");
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
        addPanelBuilder2("Education Level:", background.getEducationLevel(), gbc);

        gbc.gridy = 3;

        addPanelBuilder2("Working Experience:", background.getWorkingExperience(), gbc);

        addPanelBuilder2("Professional Certification:", background.getProfessionalCertification(), gbc);

        gbc.gridy = 3;

        addPanelBuilder2("Skills:", background.getSkills(), gbc);

        addPanelBuilder2("Professional Membership:", background.getProfessionalMembership(), gbc);

        gbc.gridy = 3;

        addPanelBuilder2("Language:", background.getLanguageProficiency(), gbc);

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

        RoundedButton saveButton = new RoundedButton("Save",Color.white);
        saveButton.setFont(FontUtils.getPoppinsFontWithColor(14f, Color.black));
        saveButton.addActionListener(
                (_) -> onSave(empID)
        );

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        bottomPanel.setPreferredSize(new Dimension(0,150));
        bottomPanel.setBackground(new Color(47, 47, 47));
        bottomPanel.add(saveButton);

        this.setLayout(new BorderLayout()); // Set layout to BorderLayout
        this.add(header, BorderLayout.NORTH); // Add Header at the top
        this.add(contentPanel, BorderLayout.CENTER); // Add GridBagPanel in the center
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel,BorderLayout.SOUTH);

    }

    private void onSave(String empID){

        ArrayList<String> edu = new ArrayList<>();
        ArrayList<String> prof = new ArrayList<>();
        ArrayList<String> skill = new ArrayList<>();
        ArrayList<String> profe = new ArrayList<>();
        ArrayList<String> lang = new ArrayList<>();
        ArrayList<String> exp = new ArrayList<>();

        for(Component component : gridBagPanel.getComponents()){
            if(component instanceof panelBuilder2){
                GridBagConstraints gbc = ((GridBagLayout)gridBagPanel.getLayout()).getConstraints(component);
                switch(gbc.gridx){

                    case 0 -> {
                        switch (gbc.gridy) {
                            case 2, 3 -> edu.add(((panelBuilder2) component).getTextContent());
                            case 4, 5 -> exp.add(((panelBuilder2) component).getTextContent());
                            default -> throw new RuntimeException();
                        }
                    }
                    case 1 -> {
                           switch (gbc.gridy){
                               case 2, 3 -> prof.add(((panelBuilder2) component).getTextContent());
                               case 4, 5 -> skill.add(((panelBuilder2) component).getTextContent());
                               default -> throw new RuntimeException();
                           }
                    }
                    case 2 -> {
                        switch (gbc.gridy){
                            case 2, 3 -> profe.add(((panelBuilder2) component).getTextContent());
                            case 4, 5 -> lang.add(((panelBuilder2) component).getTextContent());
                            default -> throw new RuntimeException();
                        }
                    }
                    default -> throw new RuntimeException();
                }

            }
        }

        String edus = String.join(";",edu);
        String profs = String.join(";",prof);
        String profes = String.join(";", profe);
        String skills = String.join(";",skill);
        String langs = String.join(";",lang);
        String exps = String.join(";",exp);

        String[] content = {exps,edus,profs,profes,skills,langs};

        TextFileModifier tfm = new TextFileModifier("background");
        tfm.updateRecord(empID,content);
        DisplayJoption.showMessage("Employee Background had updated");

    }

    private void addPanelBuilder2(String label, String value, GridBagConstraints gbc) {
        // Split the value if there is a semicolon
        String[] items = value.split(";");

        int rowCount = 1;
        int size = items.length;

        for (String item : items) {
            panelBuilder2 builder = new panelBuilder2(label);
            builder.setTextContent(item.trim()); // Set the text content

            if (rowCount != 1) {
                builder.setLabel("");
            }

            if(rowCount == 1 && rowCount == size){
                builder.addIconLabel(builder.getAddLabel());

                builder.getAddLabel().addMouseListener(
                        new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                onAdd(builder);
                                builder.removeIconLabel(builder.getAddLabel());
                            }
                        }
                );
            }else if( rowCount == 2 && size == 2){
                builder.addIconLabel(builder.getBinLabel());
                builder.getBinLabel().addMouseListener(
                        new MouseAdapter(){
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                onBin(builder);
                            }
                        }
                );
            }

            gbc.gridy++; // Set row for the current item
            gridBagPanel.add(builder, gbc);
            rowCount++;
        }

        if (label.equals("Working Experience:") || label.equals("Skills:")) {
            // Move to the next row after all items for this label have been added
            gbc.gridx++;
            gbc.gridy = 1;
        }
    }

    private void onBin(panelBuilder2 builder3) {
        // Get the constraints of the panel to be removed
        GridBagConstraints gbc2 = ((GridBagLayout) gridBagPanel.getLayout()).getConstraints(builder3);

        // Loop through all components in the gridBagPanel
        for (Component component : gridBagPanel.getComponents()) {
            if (component instanceof panelBuilder2) {
                GridBagConstraints gbc3 = ((GridBagLayout) gridBagPanel.getLayout()).getConstraints(component);

                // Check if this is the component we want to remove (matching both gridx and gridy)
                if (gbc2.gridy == gbc3.gridy && gbc2.gridx == gbc3.gridx) {
                    // Remove the component from the gridBagPanel

                    setAddIcon(builder3);
                    gridBagPanel.remove(component);

                    // Set the AddIcon on the component above this one
                }
            }
        }

        // Revalidate and repaint the panel to update the UI
        revalidate();
        repaint();
    }

    private void setAddIcon(panelBuilder2 builder) {
        // Get the GridBagConstraints of the given builder
        GridBagConstraints gbc5 = ((GridBagLayout) gridBagPanel.getLayout()).getConstraints(builder);
        int targetGridY = gbc5.gridy - 1; // Target the row above

        // Loop through all components in the gridBagPanel
        for (Component component : gridBagPanel.getComponents()) {
            if (component instanceof panelBuilder2) {
                // Get the constraints of the current component
                GridBagConstraints gbc4 = ((GridBagLayout) gridBagPanel.getLayout()).getConstraints(component);

                // Check if this component is located directly above the removed one
                if (gbc4.gridx == gbc5.gridx && gbc4.gridy == targetGridY) {
                    panelBuilder2 targetBuilder = (panelBuilder2) component;

                    for(MouseListener l : targetBuilder.getAddLabel().getMouseListeners()){
                        targetBuilder.getAddLabel().removeMouseListener(l);
                    }

                    // Add the AddLabel to the component and set up its mouse listener
                    targetBuilder.addIconLabel(targetBuilder.getAddLabel());
                    targetBuilder.getAddLabel().addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            onAdd(targetBuilder);
                            targetBuilder.removeIconLabel(targetBuilder.getAddLabel());
                        }
                    });

                    // Revalidate and repaint to refresh the UI
                    revalidate();
                    repaint();
                }
            }
        }
    }



    private void onAdd(panelBuilder2 builder3) {

        GridBagConstraints gbc2 = ((GridBagLayout) gridBagPanel.getLayout()).getConstraints(builder3);

        gbc2.gridy++;

        panelBuilder2 builder2 = new panelBuilder2("");
        gridBagPanel.add(builder2, gbc2);
        builder2.addIconLabel(builder2.getBinLabel());
        builder2.getBinLabel().addMouseListener(
                new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        onBin(builder2);
                    }
                }
        );
        revalidate();
        repaint();

    }

    public Background(BaseModel baseModel) {
        // Initialize title label
        EmpBackground background = baseModel.getEmpBackground();

        JLabel titleLabel = new JLabel("    Employee Background");
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

        int rowCount = 0;

        for (String item : items) {
            panelBuilder builder = new panelBuilder(label);
            builder.setTextContent(item.trim()); // Set the text content
            builder.setTextEditable(false);

            if(rowCount != 0){
                builder.setLabel("");
            }

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