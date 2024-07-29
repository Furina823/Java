package rolemodel;

import datamodel.*;
import pages.MyFrame;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseModel {

    private MyFrame frame;

    abstract JPanel Home();

    private Emp empCompany;
    private PersonalInfo empPersonalInformation;
    private List<EmpNOK> empNextOfKin;
    private EmpBackground empBackground;
    private ArrayList<Attendance> empAttendance;
    private ArrayList<Leave> empLeave;
    private ArrayList<LeaveRequest> leaveRequest;
    private ArrayList<SalaryHistory> salary;
    private ArrayList<Paid> tax;
    private ArrayList<PositionHistory> position;
    private ArrayList<Bonus> bonus;

    // Constructor for cases where no further information is provided
    public BaseModel() {
    }

    // Constructor when account is created and information is available
    public BaseModel(
            MyFrame frame,
            Emp empCompany,
            PersonalInfo empPersonalInformation,
            List<EmpNOK> empNextOfKin,
            EmpBackground empBackground,
            ArrayList<Attendance> empAttendance,
            ArrayList <Leave> empLeave,
            ArrayList<LeaveRequest> leaveRequest,
            ArrayList<SalaryHistory> salary,
            ArrayList<Paid> tax,
            ArrayList<PositionHistory> position,
            ArrayList<Bonus> bonus
    ) {
        this.frame = frame;
        this.empCompany = empCompany;
        this.empPersonalInformation = empPersonalInformation;
        this.empNextOfKin = empNextOfKin;
        this.empBackground = empBackground;
        this.empAttendance = empAttendance;
        this.empLeave = empLeave;
        this.leaveRequest = leaveRequest;
        this.salary = salary;
        this.tax = tax;
        this.position = position;
        this.bonus = bonus;
    }

    public Emp getEmpCompany() {
        return this.empCompany;
    }

    public PersonalInfo getEmpPersonalInformation() {
        return this.empPersonalInformation;
    }

    public List<EmpNOK> getEmpNextOfKin() {
        return this.empNextOfKin;
    }

    public EmpBackground getEmpBackground() {
        return this.empBackground;
    }

    public ArrayList<Attendance> getEmpAttendance() {return empAttendance;}

    public ArrayList<Leave> getEmpLeave() {
        return this.empLeave;
    }

    public ArrayList<LeaveRequest> getLeaveRequest() {
        return this.leaveRequest;
    }

    public ArrayList<SalaryHistory> getSalary() {
        return this.salary;
    }

    public ArrayList<Paid> getTax() {
        return this.tax;
    }

    public ArrayList<PositionHistory> getPosition() {
        return this.position;
    }

    public ArrayList<Bonus> getBonus() {
        return this.bonus;
    }


    public MyFrame getFrame() {
        return frame;
    }
}
