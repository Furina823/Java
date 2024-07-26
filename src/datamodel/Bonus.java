package datamodel;

import utility.TextFileModifier;
import java.util.ArrayList;

public class Bonus {

    private String bId;
    private String empId;
    private String date;
    private String amount;
    private String description;

    public Bonus() {
    }

    public Bonus(String bId, String empId, String date, String amount, String description) {
        this.bId = bId;
        this.empId = empId;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    // Getters and setters in one line for brevity
    public String getBId() { return bId; }
    public void setBId(String bId) { this.bId = bId; }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ArrayList<Bonus> getRecords() {
        ArrayList<Bonus> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("bonus");
        for (String[] s : tfm.toArrayListofStringArray()) {
            Bonus bonus = new Bonus(s[0], s[1], s[2], s[3], s[4]);
            records.add(bonus);
        }
        return records;
    }

    public Bonus getRecordByBId(String bId) {
        TextFileModifier tfm = new TextFileModifier("bonus");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[0].equals(bId)) {
                return new Bonus(s[0], s[1], s[2], s[3], s[4]);
            }
        }
        return null;
    }

    public ArrayList<Bonus> getRecordsByEmpId(String empId) {
        ArrayList<Bonus> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("bonus");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[1].equals(empId)) {
                Bonus bonus = new Bonus(s[0], s[1], s[2], s[3], s[4]);
                records.add(bonus);
            }
        }
        return records;
    }
}
