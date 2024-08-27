package utility;

import rolemodel.*;
import pages.MyFrame;

public class Validation {

    private String userID;
    private boolean successful;

    public Validation(MyFrame frame, String username, String password) {

        TextFileModifier tfm = new TextFileModifier("employee");
        for (String s : tfm.list) {
            String[] array = s.trim().split(",");
//            if(array[1].trim().equals(username) && array[2].trim().equals(password) && array[3].trim().equals("0")) {
                userID = array[0];

                fetchAllInformation fai = new fetchAllInformation("10020");
                successful = true;
                String role = fai.getEmp().getRole().trim();

                switch (role) {
                    case "Department Manager" -> new DepManager(
                            frame,
                            fai.getEmp(),
                            fai.getPersonalInfo(),
                            fai.getEmpNOKs(),
                            fai.getEmpBackground(),
                            fai.getAttendances(),
                            fai.getLeaves(),
                            fai.getLeaveRequests(),
                            fai.getSalaries(),
                            fai.getPaides(),
                            fai.getPositions(),
                            fai.getBonuses()
                    );
                    case "Employee" -> new Employee(
                            frame,
                            fai.getEmp(),
                            fai.getPersonalInfo(),
                            fai.getEmpNOKs(),
                            fai.getEmpBackground(),
                            fai.getAttendances(),
                            fai.getLeaves(),
                            fai.getLeaveRequests(),
                            fai.getSalaries(),
                            fai.getPaides(),
                            fai.getPositions(),
                            fai.getBonuses()
                    );
                    case "Payroll Officer" -> new PayRollOfficer(
                            frame,
                            fai.getEmp(),
                            fai.getPersonalInfo(),
                            fai.getEmpNOKs(),
                            fai.getEmpBackground(),
                            fai.getAttendances(),
                            fai.getLeaves(),
                            fai.getLeaveRequests(),
                            fai.getSalaries(),
                            fai.getPaides(),
                            fai.getPositions(),
                            fai.getBonuses()
                    );
                    case "Human Resource Officer" -> new HROfficer(
                            frame,
                            fai.getEmp(),
                            fai.getPersonalInfo(),
                            fai.getEmpNOKs(),
                            fai.getEmpBackground(),
                            fai.getAttendances(),
                            fai.getLeaves(),
                            fai.getLeaveRequests(),
                            fai.getSalaries(),
                            fai.getPaides(),
                            fai.getPositions(),
                            fai.getBonuses()
                    );
                    case "System Administrator" -> new SysAdministrator(
                            frame,
                            fai.getEmp(),
                            fai.getPersonalInfo(),
                            fai.getEmpNOKs(),
                            fai.getEmpBackground(),
                            fai.getAttendances(),
                            fai.getLeaves(),
                            fai.getLeaveRequests(),
                            fai.getSalaries(),
                            fai.getPaides(),
                            fai.getPositions(),
                            fai.getBonuses()
                    );
                    default -> successful = false;
                }
                break; // Exit loop once valid user is found
            }
//        }
    }

    public boolean isSuccessful() {
        return successful;
    }

}
