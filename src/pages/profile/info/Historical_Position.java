package pages.profile.info;

import datamodel.Emp;
import datamodel.PositionHistory;
import pages.MyPanel;
import pages.profile.FirstPage.Header;
import pages.profile.panelBuilder;
import rolemodel.BaseModel;
import utility.FontUtils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

public class Historical_Position extends JPanel {

    private JPanel gridBagPanel;
    private PositionHistory positionHistory;
    private LinkedHashMap<String, String> maps;

    public Historical_Position(BaseModel baseModel) {

        MyPanel.setButtonAction(MyPanel.createListenerEvent(new Historical(baseModel)));

        // Initialize title label
        ArrayList<PositionHistory> p = baseModel.getPosition();

        JLabel titleLabel = new JLabel(" Historical");
        titleLabel.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(16f, Color.white));
        titleLabel.setMaximumSize(new Dimension(700,40));
        titleLabel.setMinimumSize(new Dimension(700,40));
        titleLabel.setPreferredSize(new Dimension(700,40));
        titleLabel.setBackground(new Color(47,47,47));

        if(p.isEmpty()){

            maps = new LinkedHashMap<>();
            maps.put("Current:", baseModel.getEmpCompany().getPosition());

        }else {

            if (p.size() > 1) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate initial = LocalDate.parse(p.getFirst().getDate(), formatter);

                for (int j = 1; j < p.size(); j++) {

                    if (initial.isBefore(LocalDate.parse(p.get(j).getDate()))) {
                        initial = LocalDate.parse(p.get(j).getDate(), formatter);
                        positionHistory = p.get(j);
                    }

                }

            } else {
                positionHistory = p.getFirst();
            }

            maps = new LinkedHashMap<>();
            maps.put("Current:", positionHistory.getNewPosition());
            maps.put("Previous:", positionHistory.getCurrentPosition());

        }


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

}


