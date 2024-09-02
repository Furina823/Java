package pages.home.DepManager;

import datamodel.*;
import pages.MyPanel;
import rolemodel.DepManager;
import utility.DisplayJoption;
import utility.TextFileModifier;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DepManagerLabel extends JPanel {

    private final LeaveRequest leaveRequest;
    private final DepManager manager;

    public DepManagerLabel(LeaveRequest leaveRequest, DepManager manager) {

        this.leaveRequest = leaveRequest;
        this.manager = manager;

        JButton approveButton = new JButton("Approve");
        approveButton.addActionListener(_->onApprove());

        JButton rejectButton = new JButton("Reject");
        rejectButton.addActionListener(_->onReject());

        PersonalInfo info = new PersonalInfo();
        String name = info.getPersonalInfo(leaveRequest.getEmpId()).getFirstname() + " " + info.getPersonalInfo(leaveRequest.getEmpId()).getLastname();

        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(500, 50));
        label.setText(name +"       "+ leaveRequest.getLeaveType()+"        "+leaveRequest.getLeaveStartDate()+"        "+leaveRequest.getLeaveEndDate());

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        leftPanel.add(label);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        rightPanel.add(approveButton);
        rightPanel.add(rejectButton);

        this.setLayout(new BorderLayout());
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.setPreferredSize(new Dimension(700, 50));
        this.setMaximumSize(new Dimension(700, 50));
        this.setBackground(new Color(47, 47, 47));

    }

    private void calculateSubtraction() {
        // Get leave type and initialize data structures
        String leaveType = leaveRequest.getLeaveType();
        ArrayList<LocalDate> holidayDates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse start and end dates
        LocalDate startDate = LocalDate.parse(leaveRequest.getLeaveStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(leaveRequest.getLeaveEndDate(), formatter);

        // Calculate days between start and end date (inclusive)
        int daysBetween = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1; // +1 to include both start and end dates

        // Collect holiday dates
        for (WorkSchedule s : WorkSchedule.getRecords()) {
            if ("1".equals(s.getIsHoliday())) {
                holidayDates.add(LocalDate.parse(s.getDate(), formatter));
            }
        }

        // Subtract holidays within the leave period
        for (LocalDate holidayDate : holidayDates) {
            if (!holidayDate.isBefore(startDate) && !holidayDate.isAfter(endDate)) {
                daysBetween--;
            }
        }

        // Update leave balance based on leave type
        Leave leave = Leave.getRecordByID(leaveRequest.getEmpId()).getFirst();
        switch (leaveType) {
            case "Annual" -> leave.setAnnualLeave(String.valueOf(Integer.parseInt(leave.getAnnualLeave()) - daysBetween));
            case "Maternity" -> leave.setMaternityLeave(String.valueOf(Integer.parseInt(leave.getMaternityLeave()) - daysBetween));
            case "Unpaid" -> leave.setUnpaidLeave(String.valueOf(Integer.parseInt(leave.getUnpaidLeave()) - daysBetween));
            case "Medical" -> leave.setMedicalLeave(String.valueOf(Integer.parseInt(leave.getMedicalLeave()) - daysBetween));
            default -> throw new RuntimeException("Invalid leave type: " + leaveType);
        }

        int total = Integer.parseInt(leave.getAnnualLeave()) +
                Integer.parseInt(leave.getMedicalLeave()) + Integer.parseInt(leave.getMaternityLeave()) +
                Integer.parseInt(leave.getUnpaidLeave());

        TextFileModifier tfm = new TextFileModifier("leave");
        String[] content = {leave.getEmpID(),leave.getYear(),
                leave.getAnnualLeave(),leave.getMedicalLeave(),leave.getMaternityLeave(),
                leave.getUnpaidLeave(),String.valueOf(total)};

        tfm.updateRecord(leave.getLeaveID(),content);

        // Notify user
        DisplayJoption.showMessage("The Leave Request Has Been Approved");
    }


    private void onApprove(){

        TextFileModifier tfm = new TextFileModifier("leave_request");

        String[] content = {leaveRequest.getEmpId(), leaveRequest.getLeaveType(),
        "Approved", leaveRequest.getLeaveRequestDate(),leaveRequest.getLeaveStartDate(), leaveRequest.getLeaveEndDate()};

        tfm.updateRecord(leaveRequest.getLeaveId(), content);
        calculateSubtraction();
        revalidate();
        repaint();
        MyPanel.replaceRightPanel(new DepHome(manager));

    }

    private void onReject(){

        TextFileModifier tfm = new TextFileModifier("leave_request");

        String[] content = {leaveRequest.getEmpId(), leaveRequest.getLeaveType(),
                "Rejected", leaveRequest.getLeaveRequestDate(),leaveRequest.getLeaveStartDate(), leaveRequest.getLeaveEndDate()};

        tfm.updateRecord(leaveRequest.getLeaveId(), content);
        DisplayJoption.showMessage("The Leave Request had been rejected");
        revalidate();
        repaint();
        MyPanel.replaceRightPanel(new DepHome(manager));

    }


}
