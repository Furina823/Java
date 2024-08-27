package test;
import rolemodel.BaseModel;
import datamodel.LeaveRequest;
import datamodel.WorkSchedule;
import datamodel.Attendance;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Cells extends JPanel {

    private CalendarHeader calendarHeader;
    private DaysHeaderPanel daysHeaderPanel;
    private DateButtonsPanel dateButtonsPanel;
    private LocalDate currentDate;
    private BaseModel baseModel;
    private ArrayList<LeaveRequest> leaveRequests;
    private ArrayList<WorkSchedule> workSchedules;
    private ArrayList<Attendance> attendances;

    public Cells(BaseModel baseModel,RightCalendarGUI rightCalendarGUI) {
        this.baseModel = baseModel;
        this.leaveRequests = baseModel.getLeaveRequest();
        this.attendances = baseModel.getEmpAttendance();

        WorkSchedule schedule = new WorkSchedule();
        this.workSchedules = schedule.getRecords();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(245, 240));

        currentDate = LocalDate.now();
        calendarHeader = new CalendarHeader();
        daysHeaderPanel = new DaysHeaderPanel();
        dateButtonsPanel = new DateButtonsPanel(rightCalendarGUI);

        calendarHeader.setListener(new CalendarHeader.CalendarHeaderListener() {
            @Override
            public void onPreviousMonth() {
                currentDate = currentDate.minusMonths(1);
                updateCalendar();
            }

            @Override
            public void onNextMonth() {
                currentDate = currentDate.plusMonths(1);
                updateCalendar();
            }
        });

        updateCalendar();

        add(calendarHeader, BorderLayout.NORTH);
        add(daysHeaderPanel, BorderLayout.CENTER);
        add(dateButtonsPanel, BorderLayout.SOUTH);
    }

    private void updateCalendar() {
        YearMonth yearMonth = YearMonth.from(currentDate);
        String monthYear = yearMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
        calendarHeader.setMonthYear(monthYear);

        LocalDate firstOfMonth = yearMonth.atDay(1);
        int startDay = (firstOfMonth.getDayOfWeek().getValue() + 6) % 7; // Monday = 0, Sunday = 6
        int daysInMonth = yearMonth.lengthOfMonth();

        YearMonth prevMonth = yearMonth.minusMonths(1);
        int daysInPrevMonth = prevMonth.lengthOfMonth();
        int prevMonthDays = daysInPrevMonth - (startDay - 1);

        int totalCells = 42; // 6 rows * 7 columns
        for (int i = 0; i < totalCells; i++) {
            LocalDate date;
            boolean isCurrentMonth;
            if (i < startDay) {
                // Previous month's days
                int day = prevMonthDays + i;
                date = prevMonth.atDay(day);
                isCurrentMonth = false;
            } else if (i >= startDay + daysInMonth) {
                // Next month's days
                int day = i - startDay - daysInMonth + 1;
                date = yearMonth.plusMonths(1).atDay(day);
                isCurrentMonth = false;
            } else {
                // Current month's days
                int day = i - startDay + 1;
                date = yearMonth.atDay(day);
                isCurrentMonth = true;
            }

            dateButtonsPanel.updateButton(i, String.valueOf(date.getDayOfMonth()), isCurrentMonth,monthYear);
            dateButtonsPanel.setButtonEnabled(i, isCurrentMonth);

            if (isCurrentMonth) {
                Color borderColor = getDateColor(date);
                dateButtonsPanel.colorUnderlineBorder(i, borderColor);
            } else {
                dateButtonsPanel.colorUnderlineBorder(i,Color.gray);
            }
        }
    }

    private Color getDateColor(LocalDate date) {
        if (isLeaveOnDate(date)) {
            return Color.RED; // Leave
        }

        if (isAttendanceOnDate(date)) {
            return Color.GREEN;
        }

        for (WorkSchedule workSchedule : workSchedules) {
            String workScheduleDateStr = workSchedule.getDate();
            if (workScheduleDateStr != null && !workScheduleDateStr.isEmpty()) {
                try {
                    LocalDate scheduleDate = LocalDate.parse(workScheduleDateStr);
                    if (scheduleDate.equals(date)) {
                        if (workSchedule.getIsHoliday().equals("1")) {
                            return Color.MAGENTA;
                        }
                        return Color.YELLOW;
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing work schedule date: " + workScheduleDateStr);
                    e.printStackTrace();
                }
            }
        }

        return Color.WHITE;
    }

    private boolean isLeaveOnDate(LocalDate date) {
        for (LeaveRequest leaveRequest : leaveRequests) {
            String leaveStartDateStr = leaveRequest.getLeaveStartDate();
            String leaveEndDateStr = leaveRequest.getLeaveEndDate();

            if (leaveStartDateStr != null && !leaveStartDateStr.isEmpty() && leaveEndDateStr != null && !leaveEndDateStr.isEmpty()) {
                try {
                    LocalDate startDate = LocalDate.parse(leaveStartDateStr);
                    LocalDate endDate = LocalDate.parse(leaveEndDateStr);
                    if (!date.isBefore(startDate) && !date.isAfter(endDate) && leaveRequest.getStatus().equals("Approved")) {
                        return true;
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing leave request dates: " + leaveStartDateStr + ", " + leaveEndDateStr);
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private boolean isAttendanceOnDate(LocalDate date) {
        for (Attendance attendance : attendances) {
            String attendanceDateStr = attendance.getDate();
            if (attendanceDateStr != null && !attendanceDateStr.isEmpty()) {
                try {
                    LocalDate attendanceDate = LocalDate.parse(attendanceDateStr);
                    if (attendanceDate.equals(date) && attendance.getAttendanceStatus().equals("Present")) {
                        return true;
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing attendance date: " + attendanceDateStr);
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
