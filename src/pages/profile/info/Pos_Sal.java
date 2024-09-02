package pages.profile.info;

import datamodel.Emp;
import datamodel.PositionHistory;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Pos_Sal extends JPanel {

    private JPanel gridBagPanel;
    private JPanel bottomPanel;
    private RoundedButton editButton;

    public Pos_Sal(String empID) {
        this(Emp.getRecord(empID));
        initializeButton(empID);
        MyPanel.setButtonAction(MyPanel.createListenerEvent(new ProfileCategories(empID)));
    }

    public Pos_Sal(BaseModel baseModel) {
        this(baseModel.getEmpCompany());
    }

    private Pos_Sal(Emp emp) {
        // Initialize title label
        JLabel titleLabel = createTitleLabel();

        // Initialize GridBagPanel with GridBagLayout
        gridBagPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gridBagPanel.setBackground(new Color(47, 47, 47));
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0.1;

        // Add title label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.EAST; // Spans entire width
        gridBagPanel.add(titleLabel, gbc);

        gbc.weighty = 1.0;
        gbc.gridwidth = 1; // Reset to default
        gbc.gridy = 1;
        gbc.gridx = 0;

        // Add labels and components
        LinkedHashMap<String, String> maps = getStringStringLinkedHashMap(emp);
        addComponents(maps, gbc);

        // Set up layout
        setupLayout();
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("   Position / Salary");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700, 40));
        titleLabel.setMinimumSize(new Dimension(700, 40));
        titleLabel.setPreferredSize(new Dimension(700, 40));
        titleLabel.setBackground(new Color(47, 47, 47));
        return titleLabel;
    }

    private void addComponents(LinkedHashMap<String, String> maps, GridBagConstraints gbc) {
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            String label = entry.getKey();
            String value = (entry.getValue().equals("null")) ? "" : entry.getValue();

            panelBuilder builder = new panelBuilder(label);
            builder.setTextEditable(false);
            builder.setTextContent(value); // Set the text content
            gridBagPanel.add(builder, gbc);
            gbc.gridx++;
            if (gbc.gridx > 1) {
                gbc.gridx = 0;
                gbc.gridy++; // Move to the next row
            }
        }
    }

    private void initializeButton(String empID) {
        // Create and set up the bottom panel
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        bottomPanel.setBackground(new Color(47,47,47));
        editButton = new RoundedButton("Edit",Color.white);
        editButton.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.black));
        RoundedButton saveButton = new RoundedButton("Save",Color.white);
        saveButton.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.black));
        RoundedButton cancelButton = new RoundedButton("Cancel",Color.black);
        cancelButton.setFont(FontUtils.getPoppinsFontWithColor(14f,Color.white));

        saveButton.addActionListener(_ -> onSave(empID));
        cancelButton.addActionListener(_ -> onCancel(empID));

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEdit();
                bottomPanel.remove(editButton);
                bottomPanel.add(saveButton);
                bottomPanel.add(cancelButton);
                bottomPanel.revalidate();
                bottomPanel.repaint();
            }
        });

        bottomPanel.add(editButton);
        bottomPanel.setPreferredSize(new Dimension(0,150));
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void onCancel(String empID) {
        MyPanel.replaceRightPanel(new Pos_Sal(empID));
    }

    private void onSave(String empID) {
        boolean valid;

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<PositionHistory> records = PositionHistory.getRecordsByEmpId(empID);

        LocalDate latestDate = LocalDate.MIN;

        for (PositionHistory h : records) {
            LocalDate date2 = LocalDate.parse(h.getDate()); // Assuming getDate() returns a valid date string

            if (date2.isAfter(latestDate)) {
                latestDate = date2;
            }
        }

        String formattedCurrentDate = currentDate.format(formatter);

        if (latestDate.equals(currentDate)) {
            DisplayJoption.showMessage("Unable to update position twice on the same day");
            valid = false;
        } else {
            valid = true;
        }

        String textfield = "";
        String salary = "";

        for (Component component : gridBagPanel.getComponents()) {
            if (component instanceof panelBuilder) {
                if (((panelBuilder) component).getLabelText().equals("Current Position:")) {
                    textfield = ((panelBuilder) component).getTextField().getText();
                }
                if (((panelBuilder) component).getLabelText().equals("Salary:")) {
                    salary = ((panelBuilder) component).getTextField().getText();
                }
            }
        }

        if (valid) {
            TextFileModifier tfm = new TextFileModifier("position");
            String[] content = new String[]{formattedCurrentDate, empID,"null",textfield}; // Both Position Missing
            tfm.createRecord(content);
            tfm = new TextFileModifier("employee");
            Emp emp = Emp.getRecord(empID);
            String[] content2 = {emp.getEmpEmail(),emp.getEmpPassword(),
            emp.getIsBan(),emp.getDateJoin(),emp.getDateLeave(),salary,textfield,
            emp.getDepartment(),emp.getRole()};
            tfm.updateRecord(empID,content2);
            DisplayJoption.showMessage("Employee Position/Salary had updated");
        }

        initializeButton(empID); // Reset buttons
    }

    private void onEdit() {
        for (Component component : gridBagPanel.getComponents()) {
            if (component instanceof panelBuilder) {
                if (((panelBuilder) component).getLabelText().equals("Current Position:")) {
                    ((panelBuilder) component).setTextEditable(true);
                }
                if (((panelBuilder) component).getLabelText().equals("Salary:")) {
                    if (((panelBuilder) component).getTextField().getText().isEmpty())
                    {
                        ((panelBuilder) component).setTextEditable(true);
                    }
                }
            }
        }
    }

    private void setupLayout() {
        Header header = new Header();
        header.setPreferredSize(new Dimension(0, 50));

        JPanel leftPanel = createSidePanel(30);
        JPanel rightPanel = createSidePanel(50);

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

    private JPanel createSidePanel(int width) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, 0));
        panel.setBackground(new Color(47, 47, 47));
        return panel;
    }

    private static LinkedHashMap<String, String> getStringStringLinkedHashMap(Emp emp) {
        LinkedHashMap<String, String> maps = new LinkedHashMap<>();
        maps.put("Current Position:", emp.getPosition());
        maps.put("Salary:", emp.getSalary());
        return maps;
    }
}
