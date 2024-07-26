package rolemodel;

import datamodel.*;
import pages.MyFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseModel {

    protected MyFrame frame;

    abstract JPanel Home();

    private Emp empCompany;
    private PersonalInfo empPersonalInformation;
    private List<EmpNOK> empNextOfKin;
    private EmpBackground empBackground;
    private ArrayList<WorkSchedule> empWorkTime;
    private Leave empLeave;
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
            Emp empCompany,
            PersonalInfo empPersonalInformation,
            List<EmpNOK> empNextOfKin,
            EmpBackground empBackground,
            ArrayList<WorkSchedule> empWorkTime,
            Leave empLeave,
            ArrayList<LeaveRequest> leaveRequest,
            ArrayList<SalaryHistory> salary,
            ArrayList<Paid> tax,
            ArrayList<PositionHistory> position,
            ArrayList<Bonus> bonus
    ) {
        this.empCompany = empCompany;
        this.empPersonalInformation = empPersonalInformation;
        this.empNextOfKin = empNextOfKin;
        this.empBackground = empBackground;
        this.empWorkTime = empWorkTime;
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

    public ArrayList<WorkSchedule> getEmpWorkTime() {
        return this.empWorkTime;
    }

    public Leave getEmpLeave() {
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
}
