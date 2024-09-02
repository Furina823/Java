package pages;

import datamodel.Attendance;
import rolemodel.BaseModel;
import utility.TextFileModifier;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logout extends JPanel{

    private BaseModel model;

    public Logout(BaseModel baseModel, JPanel panel){

        model = baseModel;

        int option = JOptionPane.showConfirmDialog(
                null, // Parent component (null for no parent)
                "Do you want to Logout?", // Message to display
                "Confirm", // Dialog title
                JOptionPane.YES_NO_OPTION, // Option type (Yes and No)
                JOptionPane.QUESTION_MESSAGE // Message type (question mark icon)
        );

        if (option == JOptionPane.YES_OPTION) {

            updateClockOut();
            baseModel.getFrame().dispose();
            new MyFrame();

        } else {
            MyPanel.replaceRightPanel(panel);
        }

    }

    public void updateClockOut(){

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter timee = DateTimeFormatter.ofPattern("HH:mm");
        String timetoString = timee.format(time);

        TextFileModifier tfm = new TextFileModifier("attendance");

        for(Attendance attendance : Attendance.getAttendanceInfo(model.getEmpCompany().getEmpID())){
            if(attendance.getDate().equals(date.toString())){
                String[] content = {attendance.getEmpID(),attendance.getDate(),attendance.getAttendanceStatus()
                ,attendance.getStandardWorkTimeStart(),attendance.getStandardWorkTimeEnd(),attendance.getClockInTime(),timetoString};
                tfm.updateRecord(attendance.getAttendanceID(),content);
            }
        }

    }

}
