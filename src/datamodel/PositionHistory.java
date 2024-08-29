package datamodel;

import utility.TextFileModifier;
import java.util.ArrayList;

public class PositionHistory {

    private String cId;
    private String date;
    private String empId;
    private String currentPosition;
    private String newPosition;

    public PositionHistory() {
    }

    public PositionHistory(String cId, String date, String empId, String currentPosition, String newPosition) {
        this.cId = cId;
        this.date = date;
        this.empId = empId;
        this.currentPosition = currentPosition;
        this.newPosition = newPosition;
    }

    // Getters and setters in one line for brevity
    public String getCId() { return cId; }
    public void setCId(String cId) { this.cId = cId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getCurrentPosition() { return currentPosition; }
    public void setCurrentPosition(String currentPosition) { this.currentPosition = currentPosition; }

    public String getNewPosition() { return newPosition; }
    public void setNewPosition(String newPosition) { this.newPosition = newPosition; }

    public ArrayList<PositionHistory> getRecords() {
        ArrayList<PositionHistory> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("position");
        for (String[] s : tfm.toArrayListofStringArray()) {
            PositionHistory positionHistory = new PositionHistory(s[0], s[1], s[2], s[3], s[4]);
            records.add(positionHistory);
        }
        return records;
    }

    public PositionHistory getRecordByCId(String cId) {
        TextFileModifier tfm = new TextFileModifier("position_history");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[0].equals(cId)) {
                return new PositionHistory(s[0], s[1], s[2], s[3], s[4]);
            }
        }
        return null;
    }

    public ArrayList<PositionHistory> getRecordsByEmpId(String empId) {
        ArrayList<PositionHistory> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("position");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[2].equals(empId)) {
                PositionHistory positionHistory = new PositionHistory(s[0], s[1], s[2], s[3], s[4]);
                records.add(positionHistory);
            }
        }
        return records;
    }
}
