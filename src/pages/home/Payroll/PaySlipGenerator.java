package pages.home.Payroll;

import datamodel.*;
import pages.MyPanel;
import pages.home.SysAdmin.ACHeader;
import utility.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class PaySlipGenerator extends JPanel {

    private String increment;
    private String bonus;
    private String incrementDescription;
    private String bonusDescription;
    private double salary;
    private double empEPF;
    private double empSOCSO;
    private double empEIS;
    private double empPCB;
    private JLabel label;
    private String empID;
    private double emprEPF;
    private double emprSOCSO;
    private double emprEIS;

    public PaySlipGenerator(String empID, String[] array){

        MyPanel.setButtonAction(MyPanel.createListenerEvent(new AddSalaryPage(empID)));

        this.empID = empID;
        increment = array[0];
        bonus = array[1];
        incrementDescription = array[2];
        bonusDescription = array[3];

        salary = Double.parseDouble(Emp.getRecord(empID).getSalary());

        if(!Objects.equals(increment, "0") && !increment.isEmpty()){ salary += Integer.parseInt(increment);}

        empEPF = Math.round((salary*0.11)*100.0)/100.0;
        empSOCSO = Math.round((salary*0.005)*100.0)/100.0;
        empEIS = Math.round((salary*0.002)*100.0)/100.0;
        empPCB = Math.round(((salary*0.05)/12)*100.0)/100.0;

        emprEPF = Math.round((salary*0.13)*100.0)/100.0;
        emprSOCSO = Math.round((salary*0.018)*100.0)/100.0;
        emprEIS = Math.round((salary*0.002)*100.0)/100.0;

        ACHeader header = new ACHeader("s");
        header.removeDescription();
        header.setTitle("Payslip Generator");
        header.setFont(FontUtils.getPoppinsFontWithColor(20f,Color.white));

        JPanel generatorPane = new JPanel(new GridLayout(5,4));
        generatorPane.setBackground(Color.black);
        for(String s : GridInformation()){

            label = formatLabel(s);
            JPanel centeredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            centeredPanel.setBackground(Color.black);
            centeredPanel.setOpaque(true);
            if(isValidText(label.getText(),RenderArray())){

            }else {
                label.setText("       "+label.getText());
                label.setBorder(new RoundedBorder(Color.white,2,25));
                label.setPreferredSize(new Dimension(100,50));
            }

            centeredPanel.add(label);
            generatorPane.add(centeredPanel);

        }

        RoundedButton submitButton = new RoundedButton("Submit Payslip", new Color(210,210,210));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmit();
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        bottomPanel.add(submitButton);
        bottomPanel.setBackground(Color.black);
        bottomPanel.setPreferredSize(new Dimension(800,50));

        JPanel contentPane = new JPanel();
        contentPane.add(header);
        contentPane.setLayout(new FlowLayout(FlowLayout.LEADING));
        contentPane.setPreferredSize(new Dimension(800, 500));
        contentPane.setBackground(Color.black);

        contentPane.add(generatorPane);
        contentPane.add(bottomPanel);
        generatorPane.setPreferredSize(new Dimension(800,400));

        this.add(contentPane);
        this.setPreferredSize(new Dimension(850,600));
        this.setBackground(Color.black);

    }

    private void onSubmit(){

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        TextFileModifier tfm = new TextFileModifier("paid");
        String[] content = {empID,date.toString(),String.valueOf(salary),
        bonus,"200",String.valueOf(IsLateAttendance()),
        String.valueOf(UnpaidLeave()),String.valueOf(empEPF),String.valueOf(emprSOCSO),
        String.valueOf(empEIS),String.valueOf(empPCB),String.valueOf(emprEPF),
        String.valueOf(emprSOCSO),String.valueOf(emprEIS),"0"};

        tfm.createRecord(content);
        updateSalary();
        revalidate();
        repaint();
        DisplayJoption.showMessage("Payslip had updated");
        MyPanel.replaceRightPanel(new MonthPage(Emp.getRecord(empID).getDepartment(),empID));

    }

    private void updateSalary(){

        Emp emp = Emp.getRecord(empID);
        TextFileModifier tfm = new TextFileModifier("employee");

        String[] array = {emp.getEmpEmail(),emp.getEmpPassword(),
        emp.getIsBan(),emp.getDateJoin(),emp.getDateLeave(),
        String.valueOf(salary),emp.getPosition(),emp.getDepartment()
        ,emp.getRole()};

        tfm.updateRecord(empID,array);

        if(!Objects.equals(increment, "0") && !increment.isEmpty()){
            tfm = new TextFileModifier("salary");
            String[] history = {empID, increment, previousMonth().toString(),incrementDescription};
            tfm.createRecord(history);
        }

        if(!Objects.equals(bonus, "0") && !bonus.isEmpty()){
            tfm = new TextFileModifier("bonus");
            String[] bonusHistory = {empID,previousMonth().toString(),bonus,bonusDescription};
            tfm.createRecord(bonusHistory);
        }

    }


    private int IsLateAttendance(){

        int lateAttendance = 0;

        for (Attendance attendance : Attendance.getAttendanceInfo(empID)) {
            if(isSameMonthAndYear(attendance.getDate(),previousMonth().toString())){
                if (attendance.getAttendanceStatus().equals("Present")) {
                    LocalTime standardWorkTimeStart = LocalTime.parse(attendance.getStandardWorkTimeStart());
                    LocalTime clockInTime = LocalTime.parse(attendance.getClockInTime());

                    // Calculate the duration between standard work start time and clock-in time
                    Duration duration = Duration.between(standardWorkTimeStart, clockInTime);

                    // Check if the duration is more than 30 minutes
                    if (duration.toMinutes() > 30) {
                        // Handle the case where the difference is more than 30 minutes
                        lateAttendance++;
                    }
                }
            }
        }

        if (lateAttendance > 2) { return 50;} else { return 0;}
    }

    private ArrayList<String> getUnpaidLeaveDate(){

        ArrayList<LeaveRequest> request = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for(LeaveRequest r : LeaveRequest.getRecordsByEmpId(empID)){
            if(isSameMonthAndYear(r.getLeaveStartDate(),previousMonth().toString())){
                request.add(r);
            }
        }

        ArrayList<String> unpaidDate = new ArrayList<>();
        for(LeaveRequest r : request){
            String[] array = DateRangeGenerator.generateDateRange(LocalDate.parse(r.getLeaveStartDate(),formatter), LocalDate.parse(r.getLeaveEndDate()));
            for(String s : array){
                if(isSameMonthAndYear(previousMonth().toString(),s)){
                    unpaidDate.add(s);
                }
            }
        }
        return unpaidDate;
    }

    private LocalDate previousMonth(){

        return LocalDate.now();

    }

    private double UnpaidLeave(){

        ArrayList<WorkSchedule> previousMonth_Holiday = new ArrayList<>();
        ArrayList<WorkSchedule> previousMonth_WorkDay = new ArrayList<>();

        for(WorkSchedule ws : WorkSchedule.getRecords()){
            if (isSameMonthAndYear(ws.getDate(),previousMonth().toString())){
                if(ws.getIsHoliday().equals("1"))
                {
                    previousMonth_Holiday.add(ws);
                }else {
                    previousMonth_WorkDay.add(ws);
                }
            }
        }

        int i = getUnpaidLeaveDate().size();

        for(String s : getUnpaidLeaveDate()){

            if(previousMonth_Holiday.contains(s)){
                i--;
            }
        }

        return salary/previousMonth_WorkDay.size()*i;

    }


    private JLabel formatLabel (String s){

        label = new JLabel(s);
        label.setFont(FontUtils.getPoppinsFontWithColor(16f,Color.white));
        return label;

    }

    private String[] GridInformation(){

        String[] infos = {"", "Employer Rate", "Employee Rate", "Employee Contribution",
        "EPF", "13%", "11%", String.valueOf(empEPF),
        "SOCSO", "1.8%", "0.5%", String.valueOf(empSOCSO),
        "EIS", "0.2%", "0.2%", String.valueOf(empEIS),
        "PCB", "0%", "5.0%", String.valueOf(empPCB)};

        return  infos;

    }

    private String[] RenderArray(){

        return new String[]{"","Employer Rate", "Employee Rate", "Employee Contribution","EPF","SOCSO","EIS","PCB"};

    }

    public static boolean isValidText(String text, String[] validArray) {
        for (String validText : validArray) {
            if (validText.equals(text)) {
                return true; // The text is valid if found in the array
            }
        }
        return false; // The text is invalid if not found in the array
    }

    public static boolean isSameMonthAndYear(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse the strings into LocalDate objects
        LocalDate localDate1 = LocalDate.parse(date1, formatter);
        LocalDate localDate2 = LocalDate.parse(date2, formatter);

        // Compare the year and month
        return localDate1.getYear() == localDate2.getYear() &&
                localDate1.getMonth() == localDate2.getMonth();
    }

}
