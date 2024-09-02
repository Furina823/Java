package pages.home.SysAdmin;

import datamodel.Attendance;
import datamodel.Emp;
import datamodel.WorkSchedule;
import pages.MyPanel;
import utility.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetWorkingHour extends JPanel {

    private JPanel buttonPanel;
    private RoundedButton addButton = new RoundedButton("Add", Color.white);
    private JTextField dateText;
    private JComboBox<String> holidayText;
    private JTextField startText;
    private JTextField endText;

    public SetWorkingHour() {

        this.setBackground(Color.black);
        MyPanel.setButtonAction(MyPanel.createListenerEvent(new DepartmentGUI()));

        ACHeader ac = new ACHeader("");
        ac.setTitle("Standardized Working Period");

        String[] index = {"Holiday", "Workday"};

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setPreferredSize(new Dimension(750, 150));
        addButton.setFont(FontUtils.getPoppinsFontWithColor(14f, Color.black));

        // Create and align the title
        JPanel registerPanel = new JPanel();
        JLabel title = new JLabel("Information Fields");

        registerPanel.add(title);
        registerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        registerPanel.setMaximumSize(new Dimension(750, 16));
        title.setFont(FontUtils.getPoppinsFontUnderlinedWithColor(14f, Color.white));
        registerPanel.setBackground(new Color(47, 47, 47));

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(47, 47, 47));
        contentPanel.setPreferredSize(new Dimension(750, 100));
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        JLabel date = new JLabel("Date:             ");
        date.setFont(FontUtils.getPoppinsFontWithColor(14f, Color.white));
        dateText = new JTextField(15);
        dateText.setBackground(Color.black);
        dateText.setCaretColor(Color.white);
        dateText.setFont(FontUtils.getPoppinsFontWithColor(12f, Color.white));

        JLabel holiday = new JLabel("Status:        ");
        holiday.setFont(FontUtils.getPoppinsFontWithColor(14f, Color.white));
        holidayText = ComboBoxFactory.createComboBox(index);
        holidayText.setPreferredSize(new Dimension(100, 20));
        holidayText.setUI(new CustomComboBoxUI());

        JPanel leftContentPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        leftContentPanel.add(date);
        leftContentPanel.add(dateText);
        leftContentPanel.add(holiday);
        leftContentPanel.add(holidayText);
        leftContentPanel.setBackground(new Color(47, 47, 47));

        addButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        onAdd();
                    }
                }
        );

        buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.setPreferredSize(new Dimension(180, 40));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(47, 47, 47));

        JPanel rightContentPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        rightContentPanel.add(buttonPanel);
        rightContentPanel.setBackground(new Color(47, 47, 47));

        contentPanel.add(leftContentPanel);
        contentPanel.add(rightContentPanel);
        containerPanel.setBackground(Color.black);

        JPanel timePanel = new JPanel();
        timePanel.setBackground(new Color(47, 47, 47));
        timePanel.setPreferredSize(new Dimension(750, 100));
        timePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        JLabel start = new JLabel("Start Working:");
        start.setFont(FontUtils.getPoppinsFontWithColor(14f, Color.white));
        startText = new JTextField(15);
        startText.setBackground(Color.black);
        startText.setCaretColor(Color.white);
        startText.setForeground(Color.white);

        JLabel end = new JLabel("End Working:");
        end.setFont(FontUtils.getPoppinsFontWithColor(14f, Color.white));
        endText = new JTextField(15);
        endText.setBackground(Color.black);
        endText.setCaretColor(Color.white);
        endText.setForeground(Color.white);

        timePanel.add(start);
        timePanel.add(startText);
        timePanel.add(end);
        timePanel.add(endText);

        // Disable startText and endText if the status is not "Workday"
        holidayText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (holidayText.getSelectedItem().equals("Workday")) {
                    startText.setEditable(true);
                    endText.setEditable(true);
                } else {
                    startText.setEditable(false);
                    endText.setEditable(false);
                    startText.setText("");
                    endText.setText("");
                }
            }
        });

        containerPanel.add(registerPanel); // Add title to the container panel
        containerPanel.add(contentPanel); // Add content to the container panel
        containerPanel.add(timePanel);
        containerPanel.setBackground(Color.black);

        this.add(ac);
        this.add(containerPanel);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));

        // Initial state of text fields based on the initial selection
        if (!holidayText.getSelectedItem().equals("Workday")) {
            startText.setEditable(false);
            endText.setEditable(false);
        }
    }

    public boolean isValidDateFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isValidTimeFormat(String time) {
        // Regular expression for HH:mm format (24-hour format)
        String regex = "^([01]\\d|2[0-3]):([0-5]\\d)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    private void onAdd() {

        int response = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to add this entry?",
                "Confirm Add",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            // Validate date format
            if (!isValidDateFormat(dateText.getText())) {
                DisplayJoption.showMessage("Keep the date in yyyy-MM-dd format");
                return;
            }

            // Check if the date already exists
            boolean dateExists = WorkSchedule.getRecords().stream()
                    .anyMatch(schedule -> schedule.getDate().equals(dateText.getText()));

            if (dateExists) {
                DisplayJoption.showMessage("The date has already been assigned");
                return;
            }

            // Validate time format only if status is "Workday"
            if (Objects.equals(holidayText.getSelectedItem(), "Workday")) {
                if (!isValidTimeFormat(startText.getText()) || !isValidTimeFormat(endText.getText())) {
                    DisplayJoption.showMessage("Start and End times must be in HH:mm format");
                    return;
                }
                // Prepare data for Workday
                String[] record = {dateText.getText(), "0", startText.getText(), endText.getText()};
                TextFileModifier tfm = new TextFileModifier("work_schedule");
                tfm.manualCreateRecord(record);
            } else {
                // Prepare data for Holiday
                String[] record = {dateText.getText(), "1", "00:00", "00:00"};
                TextFileModifier tfm = new TextFileModifier("work_schedule");
                tfm.manualCreateRecord(record);
                Emp e = new Emp();
                tfm = new TextFileModifier("attendance");
                for (Emp emp : e.getEmpRecords()) {
                    String[] a = {emp.getEmpID(), dateText.getText(), "Holiday", "00:00",
                            "00:00", "00:00", "00:00"};
                    tfm.createRecord(a);
                }
            }

            // Refresh panel
            MyPanel.replaceRightPanel(new SetWorkingHour());
            DisplayJoption.showMessage("The date has been updated");

        } else {

        }
    }
}
