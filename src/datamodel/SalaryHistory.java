package datamodel;

import utility.TextFileModifier;
import java.util.ArrayList;

public class SalaryHistory {

    private String salaryId;
    private String empId;
    private String incrementAmount;
    private String date;
    private String description;

    public SalaryHistory() {
    }

    public SalaryHistory(String salaryId, String empId, String incrementAmount, String date, String description) {
        this.salaryId = salaryId;
        this.empId = empId;
        this.incrementAmount = incrementAmount;
        this.date = date;
        this.description = description;
    }

    // Getters and setters in one line for brevity
    public String getSalaryId() { return salaryId; }
    public void setSalaryId(String salaryId) { this.salaryId = salaryId; }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getIncrementAmount() { return incrementAmount; }
    public void setIncrementAmount(String incrementAmount) { this.incrementAmount = incrementAmount; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ArrayList<SalaryHistory> getRecords() {
        ArrayList<SalaryHistory> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("salary");
        for (String[] s : tfm.toArrayListofStringArray()) {
            SalaryHistory salaryHistory = new SalaryHistory(s[0], s[1], s[2], s[3], s[4]);
            records.add(salaryHistory);
        }
        return records;
    }

    public SalaryHistory getRecordBySalaryId(String salaryId) {
        TextFileModifier tfm = new TextFileModifier("salary");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[0].equals(salaryId)) {
                return new SalaryHistory(s[0], s[1], s[2], s[3], s[4]);
            }
        }
        return null;
    }

    public ArrayList<SalaryHistory> getRecordsByEmpId(String empId) {
        ArrayList<SalaryHistory> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("salary");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[1].equals(empId)) {
                SalaryHistory salaryHistory = new SalaryHistory(s[0], s[1], s[2], s[3], s[4]);
                records.add(salaryHistory);
            }
        }
        return records;
    }
}
