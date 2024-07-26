package datamodel;

import utility.TextFileModifier;
import java.util.ArrayList;
import java.util.List;

public class Attendance {

    private String empID;
    private String date;
    private String attendanceStatus;
    private String standardWorkTimeStart;
    private String standardWorkTimeEnd;
    private String clockInTime;
    private String clockOutTime;

    // Default constructor
    public Attendance() {
    }

    // Parameterized constructor
    public Attendance(String empID, String date, String attendanceStatus, String standardWorkTimeStart,
                      String standardWorkTimeEnd, String clockInTime, String clockOutTime) {
        this.empID = empID;
        this.date = date;
        this.attendanceStatus = attendanceStatus;
        this.standardWorkTimeStart = standardWorkTimeStart;
        this.standardWorkTimeEnd = standardWorkTimeEnd;
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
    }

    // Getters and setters
    public String getEmpID() { return empID; }
    public void setEmpID(String empID) { this.empID = empID; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getAttendanceStatus() { return attendanceStatus; }
    public void setAttendanceStatus(String attendanceStatus) { this.attendanceStatus = attendanceStatus; }

    public String getStandardWorkTimeStart() { return standardWorkTimeStart; }
    public void setStandardWorkTimeStart(String standardWorkTimeStart) { this.standardWorkTimeStart = standardWorkTimeStart; }

    public String getStandardWorkTimeEnd() { return standardWorkTimeEnd; }
    public void setStandardWorkTimeEnd(String standardWorkTimeEnd) { this.standardWorkTimeEnd = standardWorkTimeEnd; }

    public String getClockInTime() { return clockInTime; }
    public void setClockInTime(String clockInTime) { this.clockInTime = clockInTime; }

    public String getClockOutTime() { return clockOutTime; }
    public void setClockOutTime(String clockOutTime) { this.clockOutTime = clockOutTime; }

    // Method to retrieve attendance info based on empID and date
    public static List<Attendance> getAttendanceInfo(String empID) {
        List<Attendance> attendanceList = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("attendance");

        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[0].equals(empID)) {
                attendanceList.add(new Attendance(
                        array[0], // empID
                        array[1], // date
                        array[2], // attendance status
                        array[3], // standard work time start
                        array[4], // standard work time end
                        array[5], // clock in time
                        array[6]  // clock out time
                ));
            }
        }

        // Return list of attendance records or empty list if none found
        return attendanceList;
    }

    public ArrayList<Attendance> getAttendanceRecords() {
        ArrayList<Attendance> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("attendance");

        // Fetch records from the text file and populate Attendance objects
        for (String[] s : tfm.toArrayListofStringArray()) {
            Attendance attendance = new Attendance(
                    s[0], // empID
                    s[1], // date
                    s[2], // attendance
                    s[3], // standardWorkTimeStart
                    s[4], // standardWorkTimeEnd
                    s[5], // clockInTime
                    s[6]  // clockOutTime
            );
            records.add(attendance);
        }

        return records;
    }

}
