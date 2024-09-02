package pages.profile.info;

import datamodel.Leave;
import pages.MyPanel;
import pages.profile.FirstPage.Header;
import pages.profile.panelBuilder;
import rolemodel.BaseModel;
import utility.FontUtils;
import utility.TextFileModifier;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class LeavePage extends JPanel {

    private JPanel gridBagPanel;
    private Leave le;
    private JPanel buttonPanel;

    public LeavePage(String empID){

        // Initialize title label
        ArrayList<Leave> leave = Leave.getRecordByID(empID);

        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear());

        for(Leave l : leave){
            if(l.getYear().equals(year)){
                le = l;
            }
        }

        JLabel titleLabel = new JLabel("    Leave");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700,40));
        titleLabel.setMinimumSize(new Dimension(700,40));
        titleLabel.setPreferredSize(new Dimension(700,40));
        titleLabel.setBackground(new Color(47,47,47));

        LinkedHashMap<String, String> maps = getStringStringLinkedHashMap(le);

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
            String value = (entry.getValue().equals("null") ? "" : entry.getValue());

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

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.setPreferredSize(new Dimension(0,110));
        buttonPanel.add(editButton());

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
            MyPanel.replaceRightPanel(new LeavePage(le.getEmpID()));
        });
        return cancelButton;
    }

    private JButton editButton(){
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> onEdit(editButton));
        return editButton;
    }

    private void onSave(){

        String[] array = new String[4];

        int i = 0;
        for(Component component : gridBagPanel.getComponents()){
            if(component instanceof panelBuilder builder){
                array[i] = builder.getTextField().getText();
                i++;
            }
        }

        int total = 0;

        for(String s : array){
            total += Integer.parseInt(s);
        }

        array = new String[]{le.getEmpID(),"2024",String.join(",",array),String.valueOf(total)};

        TextFileModifier tfm = new TextFileModifier("leave");
        tfm.updateRecord(le.getLeaveID(),array);

        MyPanel.replaceRightPanel(new LeavePage(le.getEmpID()));
    }

    private void onEdit(JButton button){

        for(Component component : gridBagPanel.getComponents()){
            if(component instanceof panelBuilder){
                panelBuilder builder = (panelBuilder) component;
                builder.setTextEditable(true);
            }
        }
        buttonPanel.remove(button);
        buttonPanel.add(saveButton());
        buttonPanel.add(cancelButton());

        revalidate();
        repaint();
    }

    public LeavePage(BaseModel baseModel) {

        // Initialize title label
        ArrayList<Leave> leave = baseModel.getEmpLeave();

        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear());

        for(Leave l : leave){
            if(l.getYear().equals(year)){
                le = l;
            }
        }

        JLabel titleLabel = new JLabel("    Leave");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700,40));
        titleLabel.setMinimumSize(new Dimension(700,40));
        titleLabel.setPreferredSize(new Dimension(700,40));
        titleLabel.setBackground(new Color(47,47,47));

        LinkedHashMap<String, String> maps = getStringStringLinkedHashMap(le);

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

        this.setLayout(new BorderLayout()); // Set layout to BorderLayout
        this.add(header, BorderLayout.NORTH); // Add Header at the top
        this.add(contentPanel, BorderLayout.CENTER); // Add GridBagPanel in the center
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);

    }

    private static LinkedHashMap<String, String> getStringStringLinkedHashMap(Leave leave) {
        LinkedHashMap<String, String> maps = new LinkedHashMap<>();

        maps.put("Annual Leave:", leave.getAnnualLeave());
        maps.put("Medical Leave:", leave.getMedicalLeave());
        maps.put("Maternity Leave:",leave.getMaternityLeave());
        maps.put("Unpaid Leave:",leave.getUnpaidLeave());

        return maps;

    }
}
