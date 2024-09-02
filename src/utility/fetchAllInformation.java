package utility;

import datamodel.*;

import java.util.ArrayList;

public class fetchAllInformation {

    private String userID;
    private Emp employee;
    private PersonalInfo personalInfo;
    private EmpBackground background;
    private ArrayList<Leave> leaves;
    private ArrayList<SalaryHistory> salaries;
    private ArrayList<EmpNOK> nextOfKins;
    private ArrayList<Attendance> attendances;
    private ArrayList<LeaveRequest> leaveRequests;
    private ArrayList<Paid> taxes;
    private ArrayList<PositionHistory> positions;
    private ArrayList<Bonus> bonuses;

    public fetchAllInformation(String userID) {
        this.userID = userID;

        // Load data from files
        loadEmp();
        loadPersonalInfo();
        loadEmpBackground();
        loadLeaves();
        loadSalaries();
        loadEmpNOKs();
        loadAttendances();
        loadLeaveRequests();
        loadPaides();
        loadPositions();
        loadBonuses();
    }

    private void loadEmp() {
        TextFileModifier tfm = new TextFileModifier("employee");
        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[0].equals(userID)) {
                employee = new Emp();
                employee.setEmpID(array[0]);
                employee.setEmpEmail(array[1]);
                employee.setEmpPassword(array[2]);
                employee.setIsBan(array[3]);
                employee.setDateJoin(array[4]);
                employee.setDateLeave(array[5]);
                employee.setSalary(array[6]);
                employee.setPosition(array[7]);
                employee.setDepartment(array[8]);
                employee.setRole(array[9]);
            }
        }
    }

    private void loadPersonalInfo() {
        TextFileModifier tfm = new TextFileModifier("personal_information");
        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[0].equals(userID)) {
                personalInfo = new PersonalInfo();
                personalInfo.setEmpid(array[0]);
                personalInfo.setFirstname(array[1]);
                personalInfo.setLastname(array[2]);
                personalInfo.setNric(array[3]);
                personalInfo.setPassport(array[4]);
                personalInfo.setGender(array[5]);
                personalInfo.setDate_of_birth(array[6]);
                personalInfo.setPlace_of_birth(array[7]);
                personalInfo.setRace(array[8]);
                personalInfo.setReligious(array[9]);
                personalInfo.setContact_number(array[10]);
                personalInfo.setMailing_address(array[11]);
                personalInfo.setEmail(array[12]);
                personalInfo.setMarital_status(array[13]);
            }
        }
    }

    private void loadEmpBackground() {
        TextFileModifier tfm = new TextFileModifier("background");
        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[0].equals(userID)) {
                background = new EmpBackground();
                background.setEmpID(array[0]);
                background.setWorkingExperience(array[1]);
                background.setEducationLevel(array[2]);
                background.setProfessionalCertification(array[3]);
                background.setProfessionalMembership(array[4]);
                background.setSkills(array[5]);
                background.setLanguageProficiency(array[6]);
            }
        }
    }

    private void loadLeaves() {
        leaves = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("leave");
        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[1].equals(userID)) {
                Leave leave = new Leave();
                leave = new Leave();
                leave.setLeaveID(array[0]);
                leave.setEmpID(array[1]);
                leave.setYear(array[2]);
                leave.setAnnualLeave(array[3]);
                leave.setMedicalLeave(array[4]);
                leave.setMaternityLeave(array[5]);
                leave.setUnpaidLeave(array[6]);
                leave.setTotalLeave(array[7]);
                leaves.add(leave);
            }
        }
    }

    private void loadSalaries() {
        salaries = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("salary");
        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[1].equals(userID)) {
                SalaryHistory salary = new SalaryHistory();
                salary.setSalaryId(array[0]);
                salary.setEmpId(array[1]);
                salary.setIncrementAmount(array[2]);
                salary.setDate(array[3]);
                salary.setDescription(array[4]);
                salaries.add(salary);
            }
        }
    }

    private void loadEmpNOKs() {
        nextOfKins = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("next_of_kin");
        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[1].equals(userID)) {
                EmpNOK nextOfKin = new EmpNOK();
                nextOfKin.setKinID(array[0]);
                nextOfKin.setEmpID(array[1]);
                nextOfKin.setEmpNextOfKinName(array[2]);
                nextOfKin.setRelationship(array[3]);
                nextOfKin.setContactNumber(array[4]);
                nextOfKins.add(nextOfKin);
            }
        }
    }

    private void loadAttendances() {
        attendances = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("attendance");
        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[1].equals(userID)) {
                Attendance attendance = new Attendance();
                attendance.setAttendanceID(array[0]);
                attendance.setEmpID(array[1]);
                attendance.setDate(array[2]);
                attendance.setAttendanceStatus(array[3]);
                attendance.setStandardWorkTimeStart(array[4]);
                attendance.setStandardWorkTimeEnd(array[5]);
                attendance.setClockInTime(array[6]);
                attendance.setClockOutTime(array[7]);
                attendances.add(attendance);
            }
        }
    }

    private void loadLeaveRequests() {
        leaveRequests = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("leave_request");
        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[1].equals(userID)) {
                LeaveRequest leaveRequest = new LeaveRequest();
                leaveRequest.setLeaveId(array[0]);
                leaveRequest.setEmpId(array[1]);
                leaveRequest.setLeaveType(array[2]);
                leaveRequest.setStatus(array[3]);
                leaveRequest.setLeaveRequestDate(array[4]);
                leaveRequest.setLeaveStartDate(array[5]);
                leaveRequest.setLeaveEndDate(array[6]);
                leaveRequests.add(leaveRequest);
            }
        }
    }

    private void loadPaides() {
        taxes = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("paid");
        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[1].equals(userID)) {
                Paid tax = new Paid();
                tax.setPaidId(array[0]);
                tax.setEmpId(array[1]);
                tax.setDate(array[2]);
                tax.setBasicGross(array[3]);
                tax.setBonus(array[4]);
                tax.setAllowance(array[5]);
                tax.setLateAttendance(array[6]);
                tax.setUnpaidLeave(array[7]);
                tax.setEpfEmpe(array[8]);
                tax.setSocsoEmpe(array[9]);
                tax.setEisEmpe(array[10]);
                tax.setPcbEmpe(array[11]);
                tax.setEpfEmpy(array[12]);
                tax.setSocsoEmpy(array[13]);
                tax.setEisEmpy(array[14]);
                tax.setPcbEmpy(array[15]);
                taxes.add(tax);
            }
        }
    }

    private void loadPositions() {
        positions = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("position");
        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[1].equals(userID)) {
                PositionHistory position = new PositionHistory();
                position.setCId(array[0]);
                position.setDate(array[1]);
                position.setEmpId(array[2]);
                position.setCurrentPosition(array[3]);
                position.setNewPosition(array[4]);
                positions.add(position);
            }
        }
    }

    private void loadBonuses() {
        bonuses = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("bonus");
        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[1].equals(userID)) {
                Bonus bonus = new Bonus();
                bonus.setBId(array[0]);
                bonus.setEmpId(array[1]);
                bonus.setDate(array[2]);
                bonus.setAmount(array[3]);
                bonus.setDescription(array[4]);
                bonuses.add(bonus);
            }
        }
    }

    // Getter methods
    public Emp getEmp() { return employee; }
    public PersonalInfo getPersonalInfo() { return personalInfo; }
    public EmpBackground getEmpBackground() { return background; }
    public ArrayList<Leave> getLeaves() { return leaves; }
    public ArrayList<SalaryHistory> getSalaries() { return salaries; }
    public ArrayList<EmpNOK> getEmpNOKs() { return nextOfKins; }
    public ArrayList<Attendance> getAttendances() { return attendances; }
    public ArrayList<LeaveRequest> getLeaveRequests() { return leaveRequests; }
    public ArrayList<Paid> getPaides() { return taxes; }
    public ArrayList<PositionHistory> getPositions() { return positions; }
    public ArrayList<Bonus> getBonuses() { return bonuses; }

}
