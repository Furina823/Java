package calendar;

import datamodel.Attendance;
import datamodel.WorkSchedule;
import rolemodel.BaseModel;
import utility.FontUtils;
import utility.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RightCalendarGUI extends JPanel {

    private JLabel dateLabel;
    private JLabel descriptionLabel;
    private JLabel workTimeLabel;
    private BaseModel bm;
    private JLabel clockInLabel;
    private JLabel clockOutLabel;

    public RightCalendarGUI(BaseModel model) {

        bm = model;

        JLabel titleLabel = new JLabel("Time Attendance");

        JLabel clockInTitleLabel = new JLabel("Clock in:");
        clockInLabel = new JLabel("                         ");

        JLabel clockOutTitleLabel = new JLabel("Clock out:");
        clockOutLabel = new JLabel("                         ");

        JLabel detailLabel = new JLabel("Details:");
        JPanel detailPanel = getjPanel();

        titleLabel.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));
        clockInTitleLabel.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));
        clockInLabel.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));
        clockOutTitleLabel.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));
        clockOutLabel.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));
        detailLabel.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));

        JPanel titleLabelPanel = new JPanel();
        titleLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Align labels to the left within the panel
        titleLabelPanel.setBackground(new Color(47,47,47));
        titleLabelPanel.setOpaque(true);
        titleLabelPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        titleLabelPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the panel in the main panel
        titleLabelPanel.add(titleLabel);

        JPanel detailLabelPanel = new JPanel();
        detailLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Align labels to the left within the panel
        detailLabelPanel.setBackground(new Color(47,47,47));
        detailLabelPanel.setOpaque(true);
        detailLabelPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        detailLabelPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the panel in the main panel
        detailLabelPanel.add(detailLabel);

        clockInLabel.setBorder(new RoundedBorder(Color.white, 2,10));

        clockOutLabel.setBorder(new RoundedBorder(Color.white,2,10));

        JPanel clockInPanel = new JPanel();
        clockInPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Align labels to the left within the panel
        clockInPanel.setBackground(new Color(47,47,47));
        clockInPanel.setOpaque(true);
        clockInPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        clockInPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the panel in the main panel

        JPanel clockOutPanel = new JPanel();
        clockOutPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Align labels to the left within the panel
        clockOutPanel.setBackground(new Color(47,47,47));
        clockOutPanel.setOpaque(true);
        clockOutPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        clockOutPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the panel in the main panel

        // Adding components to the panels
        clockInPanel.add(clockInTitleLabel);
        clockInPanel.add(clockInLabel);

        clockOutPanel.add(clockOutTitleLabel);
        clockOutPanel.add(clockOutLabel);

        // Layout for the main panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(47,47,47));
        setPreferredSize(new Dimension(300, 250));
        add(titleLabelPanel);
        add(clockInPanel);
        add(clockOutPanel);
        add(detailLabelPanel);
        add(detailPanel);
    }

    private JPanel getjPanel() {
        JPanel detailPanel = new JPanel(new GridLayout(2,3));
        JLabel dateTitleLabel = new JLabel("Date");
        JLabel descriptionTitleLabel = new JLabel("Description");
        JLabel workTimeTitleLabel = new JLabel("Work Time");
        dateLabel = new JLabel("");
        descriptionLabel = new JLabel("");
        workTimeLabel = new JLabel("");

        // detail Panel
        detailPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,60));
        detailPanel.add(dateTitleLabel);
        detailPanel.add(descriptionTitleLabel);
        detailPanel.add(workTimeTitleLabel);
        detailPanel.add(dateLabel);
        detailPanel.add(descriptionLabel);
        detailPanel.add(workTimeLabel);

        for(Component c : detailPanel.getComponents()) {
            if(c instanceof JLabel label) {
                label.setFont(FontUtils.getPoppinsFontWithColor(12f,Color.white));
            }
        }

        detailPanel.setBackground(new Color(47,47,47));

        return detailPanel;
    }

    public void updateDate(String date){
        dateLabel.setText("<html><body style='color: white;'>"+date+"</body></html");

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate d = LocalDate.parse(date, inputFormatter);

        String formattedDate = d.format(outputFormatter);

        for (Attendance attendance : Attendance.getAttendanceInfo(bm.getEmpCompany().getEmpID())){
            if (attendance.getDate().equals(formattedDate)){
                clockInLabel.setText("  "+attendance.getClockInTime()+" ");
                clockOutLabel.setText(" "+attendance.getClockOutTime()+"    ");
            }else {
                clockInLabel.setText("                         ");
                clockOutLabel.setText("                         ");
            }
        }

        for(WorkSchedule s : WorkSchedule.getRecords()){
            if(s.getDate().equals(formattedDate)){
                if(s.getIsHoliday().equals("1")){
                    descriptionLabel.setText("Holiday");
                }else{
                    descriptionLabel.setText("Workday");
                }
                workTimeLabel.setText(s.getStandardizedStartWorkTime()+" : "+s.getStandardizedEndWorkTime());
                break;
            }else {
                descriptionLabel.setText("Not Labeled");
                workTimeLabel.setText("Not Labeled");
            }
        }

        repaint();
        revalidate();

    }

}
