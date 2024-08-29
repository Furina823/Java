package pages.leave.RegistrationPage;

import datamodel.Leave;
import datamodel.LeaveRequest;
import datamodel.WorkSchedule;
import rolemodel.BaseModel;
import utility.TextFileModifier;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class RegistrationBody extends JPanel {

    private String name;
    private String department;
    private BaseModel model;
    private JTextField dayEndField;
    private JTextField dayStartField;

    public RegistrationBody(BaseModel bm){

        this.model = bm;

        name = bm.getEmpPersonalInformation().getFirstname() + " " + bm.getEmpPersonalInformation().getLastname();
        department = bm.getEmpCompany().getDepartment();

        JPanel containerPanel = new JPanel();

        containerPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Leave Registration");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3,3));

        containerPanel.add(title, BorderLayout.NORTH);
        containerPanel.add(contentPanel, BorderLayout.SOUTH);

        // First Row First Column
        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Name:");
        JLabel nameDescription = new JLabel(name);

        namePanel.add(nameLabel);
        namePanel.add(nameDescription);

        // First Row Second Column
        JPanel departmentPanel = new JPanel();
        JLabel departmentLabel = new JLabel("Department:");
        JLabel departmentDescription = new JLabel(department);

        departmentPanel.add(departmentLabel);
        departmentPanel.add(departmentDescription);

        // First Row Third Column
        JPanel EmptyPanel = new JPanel();

        // Second Row First Column
        JPanel dayStartPanel = new JPanel();
        JLabel dayStartLabel = new JLabel("Day Start:");
        dayStartField = new JTextField(10);

        dayStartPanel.add(dayStartLabel);
        dayStartPanel.add(dayStartField);

        // Second Row Second Column
        JPanel leaveTypePanel = new JPanel();
        JLabel LeaveTypeLabel = new JLabel("Leave Type:");
        JComboBox<String> leaveTypeComboBox = new JComboBox<>(leaveType());

        leaveTypePanel.add(LeaveTypeLabel);
        leaveTypePanel.add(leaveTypeComboBox);

        // Second Row Third Column
        JPanel EmptyPanel2 = new JPanel();

        // Third Row First Column
        JPanel dayEndPanel = new JPanel();
        JLabel dayEndLabel = new JLabel("Day End:");
        dayEndField = new JTextField(10);

        // Third Row Second Column
        JPanel EmptyPanel3 = new JPanel();

        // Third Row third column
        JButton addbutton = new JButton("Add");
        addbutton.addActionListener((e)->{
            ValidateLeave((String)leaveTypeComboBox.getSelectedItem(),dayStartField.getText(),dayEndField.getText(),bm);
        });

        dayEndPanel.add(dayEndLabel);
        dayEndPanel.add(dayEndField);

        contentPanel.add(namePanel);
        contentPanel.add(departmentPanel);
        contentPanel.add(EmptyPanel);

        contentPanel.add(dayStartPanel);
        contentPanel.add(leaveTypePanel);
        contentPanel.add(EmptyPanel2);

        contentPanel.add(dayEndPanel);
        contentPanel.add(EmptyPanel3);
        contentPanel.add(addbutton);

        this.add(containerPanel);

    }

    public boolean isMaternity(){
        return model.getEmpPersonalInformation().getGender().equalsIgnoreCase("female");
    }

    public String[] leaveType() {
        String[] type = {"Annual", "Medical", "Unpaid"};

        if (isMaternity()) {
            String[] leavetype = new String[type.length + 1];

            System.arraycopy(type, 0, leavetype, 0, type.length);

            leavetype[type.length] = "Maternity";

            return leavetype;
        } else {
            return type;
        }
    }

    public void ValidateLeave(String leaveType, String initialLeave, String EndLeave, BaseModel bm){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");

        if (!isValidDate(initialLeave) || !isValidDate(EndLeave)) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Please enter dates in yyyy-MM-dd format.");
            initializeLeaveDate();
            return;  // Stop further processing if dates are invalid
        }

        LocalDate initialDate = LocalDate.parse(initialLeave, formatter);
        LocalDate endDate = LocalDate.parse(EndLeave, formatter);

        LocalDate currentDate = LocalDate.now();

        // Get the current year
        int currentYear = currentDate.getYear();

        String[] leaves = new String[4];

        // Convert the current year to a string
        String currentYearString = Integer.toString(currentYear);

        for(Leave leave : bm.getEmpLeave()){
            if(leave.getYear().equals(currentYearString)){
                leaves[0] = leave.getAnnualLeave();
                leaves[1] = leave.getMedicalLeave();
                leaves[2] = leave.getMaternityLeave();
                leaves[3] = leave.getUnpaidLeave();
            }
        }

        int number = switch(leaveType){
            case "Annual" -> Integer.parseInt(leaves[0]);
            case "Medical" -> Integer.parseInt(leaves[1]);
            case "Maternity" -> Integer.parseInt(leaves[2]);
            case "Unpaid" -> Integer.parseInt(leaves[3]);
            default -> 0;
        };

        if(!leaveType.equals("Maternity")) {


            // Assume that the leave have to be request earlier & cannot request leave for the same day
            if (initialDate.isEqual(currentDate) || initialDate.isBefore(currentDate)) {
                JOptionPane.showMessageDialog(null, "Request leave for current or previous date is unable");
                initializeLeaveDate();
            } else if (endDate.isBefore(initialDate) || endDate.isEqual(currentDate)) {
                JOptionPane.showMessageDialog(null, "The Leave End Date before Leave Start Date is not an appropriate format");
                initializeLeaveDate();
            } else if (number < calculateLeaveDay(initialDate, endDate)) {
                JOptionPane.showMessageDialog(null, "The Leave amount request is more than your remaining leave amount. There is "
                        + number + " of " + leaveType + " while you had request for " + calculateLeaveDay(initialDate, endDate) + " day.");
                initializeLeaveDate();
            } else if(notLabeled(initialDate.toString()) || notLabeled(endDate.toString())){
                JOptionPane.showMessageDialog(null,"The date is not labeled as work day or holiday, kindly contact Human Resource Officer");
                initializeLeaveDate();
            }else if(isHoliday(initialDate.toString())){
                JOptionPane.showMessageDialog(null, "Request leave for current date is unable due to initial date is holiday");
                initializeLeaveDate();
            } else if(isHoliday(endDate.toString())){
                JOptionPane.showMessageDialog(null, "Request leave for current date is unable due to end date is holiday");
                initializeLeaveDate();
            }else {

                LeaveRequest request = new LeaveRequest();
                request.setEmpId(bm.getEmpCompany().getEmpID());
                request.setLeaveType(leaveType);
                request.setStatus("Pending");
                request.setLeaveRequestDate(currentDate.toString());
                request.setLeaveStartDate(initialDate.toString());
                request.setLeaveEndDate(endDate.toString());
                request.setApprovalManager("0");

                sentLeaveRequest(request);

                bm.setLeaveRequest(new LeaveRequest().getRecordsByEmpId(bm.getEmpCompany().getEmpID()));

                Container parent = this.getParent();

                    parent.revalidate();
                    parent.repaint();

                JOptionPane.showMessageDialog(null, "The Leave Request had applied");
                initializeLeaveDate();
            }
        } else {

        }


    }

    public void initializeLeaveDate(){
        dayStartField.setText("");
        dayEndField.setText("");
    }

    public int calculateLeaveDay(LocalDate initialDate, LocalDate endDate){

        long days = ChronoUnit.DAYS.between(initialDate, endDate);
        return (int) days;

    }

    public boolean isHoliday(String date){

        WorkSchedule workSchedule = new WorkSchedule();
        return workSchedule.getRecordByDate(date).getIsHoliday().equals("1");

    }

    public boolean notLabeled(String date){

        WorkSchedule workSchedule = new WorkSchedule();
        if(workSchedule.getRecordByDate(date) == null){
            return true;
        }
        return false;
    }

    public void sentLeaveRequest(LeaveRequest r){

        TextFileModifier tfm = new TextFileModifier("leave_request");
        Field[] fields = r.getClass().getDeclaredFields();
        String[] array = new String[fields.length-1];

        int index = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(r);  // Get the value of the field
                if(value != null) {
                    array[index++] = value.toString();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        tfm.createRecord(array);

    }

    private boolean isValidDate(String dateStr) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(dateStr, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Overlapping is not validated yet

}
