package datamodel;

import utility.TextFileModifier;
import java.util.ArrayList;

public class WorkSchedule {

    private String date;
    private String isHoliday;
    private String standardizedStartWorkTime;
    private String standardizedEndWorkTime;

    public WorkSchedule() {
    }

    public WorkSchedule(String date, String isHoliday, String standardizedStartWorkTime, String standardizedEndWorkTime) {
        this.date = date;
        this.isHoliday = isHoliday;
        this.standardizedStartWorkTime = standardizedStartWorkTime;
        this.standardizedEndWorkTime = standardizedEndWorkTime;
    }

    // Getters and setters in one line for brevity
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getIsHoliday() { return isHoliday; }
    public void setIsHoliday(String isHoliday) { this.isHoliday = isHoliday; }

    public String getStandardizedStartWorkTime() { return standardizedStartWorkTime; }
    public void setStandardizedStartWorkTime(String standardizedStartWorkTime) { this.standardizedStartWorkTime = standardizedStartWorkTime; }

    public String getStandardizedEndWorkTime() { return standardizedEndWorkTime; }
    public void setStandardizedEndWorkTime(String standardizedEndWorkTime) { this.standardizedEndWorkTime = standardizedEndWorkTime; }

    public ArrayList<WorkSchedule> getRecords() {
        ArrayList<WorkSchedule> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("work_schedule");
        for (String[] s : tfm.toArrayListofStringArray()) {
            WorkSchedule workSchedule = new WorkSchedule(s[0], s[1], s[2], s[3]);
            records.add(workSchedule);
        }
        return records;
    }

    public WorkSchedule getRecordByDate(String date) {
        TextFileModifier tfm = new TextFileModifier("work_schedule");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[0].equals(date)) {
                return new WorkSchedule(s[0], s[1], s[2], s[3]);
            }
        }
        return null;
    }
}
