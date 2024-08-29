package datamodel;

import utility.TextFileModifier;
import java.util.ArrayList;

public class LeaveRequest {

    private String leaveId;
    private String empId;
    private String leaveType;
    private String status;
    private String leaveRequestDate;
    private String leaveStartDate;
    private String leaveEndDate;
    private String approvalManager;

    public LeaveRequest() {
    }

    public LeaveRequest(String leaveId, String empId, String leaveType, String status, String leaveRequestDate,
                        String leaveStartDate, String leaveEndDate, String approvalManager) {
        this.leaveId = leaveId;
        this.empId = empId;
        this.leaveType = leaveType;
        this.status = status;
        this.leaveRequestDate = leaveRequestDate;
        this.leaveStartDate = leaveStartDate;
        this.leaveEndDate = leaveEndDate;
        this.approvalManager = approvalManager;
    }

    // Getters and setters in one line for brevity
    public String getLeaveId() { return leaveId; }
    public void setLeaveId(String leaveId) { this.leaveId = leaveId; }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getLeaveType() { return leaveType; }
    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLeaveRequestDate() { return leaveRequestDate; }
    public void setLeaveRequestDate(String leaveRequestDate) { this.leaveRequestDate = leaveRequestDate; }

    public String getLeaveStartDate() { return leaveStartDate; }
    public void setLeaveStartDate(String leaveStartDate) { this.leaveStartDate = leaveStartDate; }

    public String getLeaveEndDate() { return leaveEndDate; }
    public void setLeaveEndDate(String leaveEndDate) { this.leaveEndDate = leaveEndDate; }

    public String getApprovalManager() { return approvalManager; }
    public void setApprovalManager(String approvalManager) { this.approvalManager = approvalManager; }

    public ArrayList<LeaveRequest> getRecords() {
        ArrayList<LeaveRequest> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("leave_request");
        for (String[] s : tfm.toArrayListofStringArray()) {
            LeaveRequest leaveRequest = new LeaveRequest(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
            records.add(leaveRequest);
        }
        return records;
    }

    public LeaveRequest getRecordByLeaveId(String leaveId) {
        TextFileModifier tfm = new TextFileModifier("leave_request");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[0].equals(leaveId)) {
                return new LeaveRequest(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
            }
        }
        return null;
    }

    public ArrayList<LeaveRequest> getRecordsByEmpId(String empId) {
        ArrayList<LeaveRequest> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("leave_request");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[1].equals(empId)) {
                LeaveRequest leaveRequest = new LeaveRequest(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
                records.add(leaveRequest);
            }
        }
        return records;
    }

    public void deleteRecordByLeaveId(String leaveId) {
        TextFileModifier tfm = new TextFileModifier("leave_request");
        tfm.deleteRecord(leaveId);
    }

}
