package datamodel;

import utility.TextFileModifier;

import java.util.ArrayList;

public class Leave {

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(String annualLeave) {
        this.annualLeave = annualLeave;
    }

    public String getMedicalLeave() {
        return medicalLeave;
    }

    public void setMedicalLeave(String medicalLeave) {
        this.medicalLeave = medicalLeave;
    }

    public String getMaternityLeave() {
        return maternityLeave;
    }

    public void setMaternityLeave(String maternityLeave) {
        this.maternityLeave = maternityLeave;
    }

    public String getUnpaidLeave() {
        return unpaidLeave;
    }

    public void setUnpaidLeave(String unpaidLeave) {
        this.unpaidLeave = unpaidLeave;
    }

    public String getTotalLeave() {
        return totalLeave;
    }

    public void setTotalLeave(String totalLeave) {
        this.totalLeave = totalLeave;
    }

    private String leaveID;
    private String empID;
    private String year;
    private String annualLeave;
    private String medicalLeave;
    private String maternityLeave;
    private String unpaidLeave;
    private String totalLeave;

    public Leave() {
        // Initialize default values or leave empty if not needed
    }

    public Leave(String leaveID, String empID, String year, String annualLeave, String medicalLeave,
                 String maternityLeave, String unpaidLeave) {
        this.leaveID = leaveID;
        this.empID = empID;
        this.year = year;
        this.annualLeave = annualLeave;
        this.medicalLeave = medicalLeave;
        this.maternityLeave = maternityLeave;
        this.unpaidLeave = unpaidLeave;
        calculateTotalLeave();
    }

    private void calculateTotalLeave() {
        try {
            int annual = Integer.parseInt(annualLeave);
            int medical = Integer.parseInt(medicalLeave);
            int maternity = Integer.parseInt(maternityLeave);
            int unpaid = Integer.parseInt(unpaidLeave);
            this.totalLeave = String.valueOf(annual + medical + maternity + unpaid);
        } catch (NumberFormatException e) {

            this.totalLeave = "0";
        }
    }

    public ArrayList<Leave> getRecords() {

        ArrayList<Leave> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("leave");
        for (String[] s : tfm.toArrayListofStringArray()) {
                Leave leave = new Leave(s[0],s[1], s[2], s[3], s[4], s[5], s[6]);
                records.add(leave);
        }
        return records;
    }

    public ArrayList<Leave> getRecordByID(String id) {
        ArrayList<Leave> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("leave");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[0].equals(id)) {
                Leave leave = new Leave(s[0],s[1], s[2], s[3], s[4], s[5], s[6]);
                records.add(leave);
            }
        }
        return records;
    }


    public String getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(String leaveID) {
        this.leaveID = leaveID;
    }
}
