package rolemodel;

import datamodel.*;
import pages.MyFrame;
import pages.MyPanel;
import pages.home.PayRollOfficerHome;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PayRollOfficer extends BaseModel {


    protected MyFrame frame;

    @Override
    JPanel Home() {
        return new PayRollOfficerHome(this);
    }

    // Constructor for Account created but no information
    public PayRollOfficer(){}

    // Constructor for Account created and inserted by HQ officer
    public PayRollOfficer(
            MyFrame frame,
            Emp empCompany,
            PersonalInfo empPersonalInformation,
            List<EmpNOK> empNextOfKin,
            EmpBackground empBackground,
            ArrayList<Attendance> empAttendance,
            ArrayList<Leave> empLeave,
            ArrayList<LeaveRequest> leaveRequest,
            ArrayList<SalaryHistory> salary,
            ArrayList<Paid> tax,
            ArrayList<PositionHistory> position,
            ArrayList<Bonus> bonus
    ) {
        super(
                frame,
                empCompany,
                empPersonalInformation,
                empNextOfKin,
                empBackground,
                empAttendance,
                empLeave,
                leaveRequest,
                salary,
                tax,
                position,
                bonus
        );

        this.frame = frame;
        frame.replacePanel(new MyPanel(Home(),this));

    }


}
