package rolemodel;

import datamodel.*;
import pages.MyFrame;
import pages.MyPanel;
import pages.home.DepManager.DepHome;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DepManager extends BaseModel {

    protected MyFrame frame;

    @Override
    JPanel Home() {
        return new DepHome(this);
    }

    public DepManager() {
        super();
    }

    public DepManager(
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
        frame.replacePanel(new MyPanel(Home(), this));
    }

    @Override
    public void reset() {
        frame.replacePanel(new MyPanel(Home(),this));
    }

}
