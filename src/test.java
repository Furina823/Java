import datamodel.LeaveRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.reflect.Field;

public class test {

    public static void main(String[] args) {

        LeaveRequest request = new LeaveRequest();
        request.setEmpId("10001");
        request.setLeaveType("Annual");
        request.setStatus("Pending");
        request.setLeaveRequestDate("2024-08-20");
        request.setLeaveStartDate("2024-08-21");
        request.setLeaveEndDate("2024-08-22");
        request.setApprovalManager("0");

    }
}
